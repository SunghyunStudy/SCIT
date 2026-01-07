package net.dima.prac.controller;

import lombok.RequiredArgsConstructor;
import net.dima.prac.dto.GameMessage;
import net.dima.prac.dto.GameRoom;
import net.dima.prac.dto.RoomDto;
import net.dima.prac.service.GameService;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;

    // --- View & HTTP API ---

    @GetMapping("/")
    public String index() {
        return "redirect:/game/lobby";
    }

    @GetMapping("/game/lobby")
    public String lobby(Model model) {
        List<RoomDto> rooms = gameService.findAllRooms();
        model.addAttribute("rooms", rooms);
        return "game/lobby";
    }

    @PostMapping("/game/room")
    public String createRoom(@RequestParam String title, @RequestParam(required = false) String password) {
        GameRoom room = gameService.createRoom(title, password);
        return "redirect:/game/room/" + room.getRoomId();
    }

    @PostMapping("/game/room/join")
    public String joinRoom(@RequestParam String roomId, @RequestParam(required = false) String password, Principal principal) {
        boolean joined = gameService.joinRoom(roomId, principal.getName(), password);
        if (joined) {
            return "redirect:/game/room/" + roomId;
        } else {
            return "redirect:/game/lobby?error=joinfailed";
        }
    }

    @GetMapping("/game/room/{roomId}")
    public String room(@PathVariable String roomId, Model model, Principal principal) {
        GameRoom room = gameService.findRoomById(roomId);
        if (room == null) {
            return "redirect:/game/lobby?error=notfound";
        }
        
        // Ensure user is formally joined (if they accessed URL directly without POST, and no password required)
        // If password is required and they are not in list, they might be kicked by WS later or here.
        // For simplicity: If no password, auto-join here.
        if ((room.getPassword() == null || room.getPassword().isEmpty()) && !room.getPlayers().containsKey(principal.getName())) {
             gameService.joinRoom(roomId, principal.getName(), "");
        }

        // If private and user not in list, redirect back (unless they just POSTed, which added them)
        if (room.getPassword() != null && !room.getPassword().isEmpty() && !room.getPlayers().containsKey(principal.getName())) {
             return "redirect:/game/lobby?error=needpassword";
        }
        
        model.addAttribute("roomId", roomId);
        model.addAttribute("username", principal.getName());
        return "game/room";
    }
    
    // --- WebSocket Handlers ---

    @MessageMapping("/game/enter")
    public void enter(@Payload GameMessage message, Principal principal) {
        // Re-confirm join (idempotent)
        gameService.joinRoom(message.getRoomId(), principal.getName(), ""); 
    }

    @MessageMapping("/game/start")
    public void start(@Payload GameMessage message, Principal principal) {
         // Check if sender is owner or both are ready. 
         // For simplicity: anyone in the room can trigger start if 2 players exist.
         gameService.startGame(message.getRoomId());
    }

    @MessageMapping("/game/progress")
    public void progress(@Payload GameMessage message, Principal principal) {
        int progress = 0;
        if (message.getContent() instanceof Number) {
            progress = ((Number) message.getContent()).intValue();
        } else if (message.getContent() instanceof String) {
            progress = Integer.parseInt((String) message.getContent());
        }
        gameService.updateProgress(message.getRoomId(), principal.getName(), progress);
    }
}
