package net.dima.prac.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GameSession {
    @Id
    private String userId; // Using username/ID as PK for simplicity in 1:1 session

    private int stage;
    private int mental; // HP

    // Coordinates can be saved here too if needed for persistence
    // private int lastX;
    // private int lastY;
}
