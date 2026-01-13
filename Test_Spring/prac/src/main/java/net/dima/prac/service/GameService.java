package net.dima.prac.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.dima.prac.entity.GameSession;
import net.dima.prac.repository.GameSessionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class GameService {

    private final GameSessionRepository gameSessionRepository;

<<<<<<< HEAD
    public GameSession getOrCreateSession(String userId) {
        return gameSessionRepository.findById(userId)
                .orElseGet(() -> gameSessionRepository.save(GameSession.builder()
                        .userId(userId)
                        .stage(1)
                        .mental(100)
                        .build()));
=======
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
>>>>>>> parent of f417b7e (0109)
    }

    @Transactional
    public Map<String, Object> checkEvent(String userId, int x, int y) {
        Map<String, Object> response = new HashMap<>();
        response.put("event", "none");

        // Example Logic: Check specific coordinates (e.g., Tiled Map pixel coordinates)
        // Adjust these ranges based on your actual map (scit_map.json)
        
        // Event 1: Quiz Zone (Visual Box: 200,200 ~ 300,300)
        // Center at 250, 250 with larger radius
        if (isWithinRange(x, y, 250, 250, 75)) {
            response.put("event", "quiz");
            response.put("message", "Pop Quiz! What is the default scope of a Spring Bean?");
        }
        
        // Event 2: Boss Room (Visual Box: 1000,800 ~ 1200,1000)
        // Center at 1100, 900
        else if (isWithinRange(x, y, 1100, 900, 150)) {
            response.put("event", "boss");
            response.put("message", "You have encountered the Final Project Boss!");
        }

        // Auto-save position
        GameSession session = getOrCreateSession(userId);
        // session.setLastX(x); // Uncomment if fields exist in Entity
        // session.setLastY(y);
        gameSessionRepository.save(session);

        return response;
    }

    private boolean isWithinRange(int playerX, int playerY, int targetX, int targetY, int radius) {
        double distance = Math.sqrt(Math.pow(playerX - targetX, 2) + Math.pow(playerY - targetY, 2));
        return distance <= radius;
    }
}
