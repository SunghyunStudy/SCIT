package net.dima.prac.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.dima.prac.dto.GameMessage;
import net.dima.prac.dto.GameRoom;
import net.dima.prac.dto.RoomDto;
import net.dima.prac.entity.GameHistory;
import net.dima.prac.entity.Member;
import net.dima.prac.repository.GameHistoryRepository;
import net.dima.prac.repository.MemberRepository;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class GameService {

    private final GameHistoryRepository gameHistoryRepository;
    private final MemberRepository memberRepository;
    private final SimpMessagingTemplate messagingTemplate;

    // In-Memory Storage
    private final Map<String, GameRoom> gameRooms = new ConcurrentHashMap<>();
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public List<RoomDto> findAllRooms() {
        return gameRooms.values().stream()
                .map(room -> RoomDto.builder()
                        .roomId(room.getRoomId())
                        .title(room.getTitle())
                        .hasPassword(room.getPassword() != null && !room.getPassword().isEmpty())
                        .userCount(room.getPlayers().size())
                        .status(room.getStatus())
                        .build())
                .collect(Collectors.toList());
    }

    public GameRoom createRoom(String title, String password) {
        GameRoom room = GameRoom.create(title, password);
        gameRooms.put(room.getRoomId(), room);
        return room;
    }

    public GameRoom findRoomById(String roomId) {
        return gameRooms.get(roomId);
    }

    public boolean joinRoom(String roomId, String username, String password) {
        GameRoom room = gameRooms.get(roomId);
        if (room == null) return false;
        
        // If already joined, just return true
        if (room.getPlayers().containsKey(username)) {
            return true;
        }

        if (room.getPlayers().size() >= 2) return false;
        if (room.getPassword() != null && !room.getPassword().isEmpty() && !room.getPassword().equals(password)) {
            return false;
        }

        room.getPlayers().put(username, username);
        room.getProgress().put(username, 0);
        return true;
    }

    public void startGame(String roomId) {
        GameRoom room = gameRooms.get(roomId);
        if (room != null && room.getPlayers().size() == 2) {
            room.setStatus("PLAYING");
            messagingTemplate.convertAndSend("/topic/game/room/" + roomId, 
                GameMessage.builder().type(GameMessage.MessageType.START).roomId(roomId).content("START").build());
        }
    }

    public void updateProgress(String roomId, String username, int progress) {
        GameRoom room = gameRooms.get(roomId);
        if (room == null || !"PLAYING".equals(room.getStatus())) return;

        room.getProgress().put(username, progress);

        // Broadcast progress
        messagingTemplate.convertAndSend("/topic/game/room/" + roomId, 
            GameMessage.builder()
                .type(GameMessage.MessageType.PROGRESS)
                .roomId(roomId)
                .sender(username)
                .content(progress)
                .build());

        if (progress >= 100) {
            triggerFinish(room, username);
        }
    }

    private synchronized void triggerFinish(GameRoom room, String winnerName) {
        if ("FINISHED".equals(room.getStatus())) return;
        
        room.setStatus("FINISHED");
        // Broadcast Countdown
        messagingTemplate.convertAndSend("/topic/game/room/" + room.getRoomId(), 
            GameMessage.builder()
                .type(GameMessage.MessageType.COUNTDOWN)
                .roomId(room.getRoomId())
                .sender(winnerName) // This user finished first
                .content(10) // 10 seconds
                .build());

        // Schedule finalization
        scheduler.schedule(() -> finalizeGame(room.getRoomId(), winnerName), 10, TimeUnit.SECONDS);
    }

    @Transactional
    public void finalizeGame(String roomId, String firstFinisher) {
        GameRoom room = gameRooms.get(roomId);
        if (room == null) return;

        // Logic to determine winner (The one who triggered finish is usually the winner, 
        // unless specific rules apply. Here we assume first to 100 wins).
        // The loser is the other player.
        
        String loserName = room.getPlayers().keySet().stream()
                .filter(p -> !p.equals(firstFinisher))
                .findFirst()
                .orElse("Unknown");

        // Save to DB
        Member winner = memberRepository.findByUsername(firstFinisher).orElse(null);
        Member loser = memberRepository.findByUsername(loserName).orElse(null);

        if (winner != null && loser != null) {
            GameHistory history = GameHistory.builder()
                    .winner(winner)
                    .loser(loser)
                    .build();
            gameHistoryRepository.save(history);
        }

        // Broadcast END
        messagingTemplate.convertAndSend("/topic/game/room/" + roomId, 
            GameMessage.builder()
                .type(GameMessage.MessageType.END)
                .roomId(roomId)
                .content("Winner: " + firstFinisher)
                .build());
        
        // Cleanup room (optional, or keep it for chat)
        gameRooms.remove(roomId); 
    }
}
