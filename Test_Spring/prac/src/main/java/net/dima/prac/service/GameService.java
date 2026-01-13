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

    public GameSession getOrCreateSession(String userId) {
        return gameSessionRepository.findById(userId)
                .orElseGet(() -> gameSessionRepository.save(GameSession.builder()
                        .userId(userId)
                        .stage(1)
                        .mental(100)
                        .build()));
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
