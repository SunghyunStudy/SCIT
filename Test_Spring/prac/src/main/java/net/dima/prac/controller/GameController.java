package net.dima.prac.controller;

import lombok.RequiredArgsConstructor;
import net.dima.prac.service.GameService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/game")
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;

    @PostMapping("/check-event")
    public ResponseEntity<Map<String, Object>> checkEvent(@RequestBody Map<String, Object> payload) {
        String userId = (String) payload.getOrDefault("userId", "guest_user");
        int x = (Integer) payload.get("x");
        int y = (Integer) payload.get("y");

        Map<String, Object> result = gameService.checkEvent(userId, x, y);
        return ResponseEntity.ok(result);
    }
}
