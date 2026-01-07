package net.dima.prac.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GameRoom {
    private String roomId;
    private String title;
    private String password; // Optional
    
    @Builder.Default
    private ConcurrentHashMap<String, String> players = new ConcurrentHashMap<>(); // SessionId -> Username
    
    @Builder.Default
    private ConcurrentHashMap<String, Integer> progress = new ConcurrentHashMap<>(); // Username -> Progress %

    @Builder.Default
    private String status = "WAITING"; // WAITING, PLAYING, FINISHED

    public static GameRoom create(String title, String password) {
        return GameRoom.builder()
                .roomId(UUID.randomUUID().toString())
                .title(title)
                .password(password)
                .players(new ConcurrentHashMap<>())
                .progress(new ConcurrentHashMap<>())
                .status("WAITING")
                .build();
    }
}
