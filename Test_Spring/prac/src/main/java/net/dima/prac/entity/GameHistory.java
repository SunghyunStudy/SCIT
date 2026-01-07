package net.dima.prac.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "game_history")
public class GameHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "winner_id")
    private Member winner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "loser_id")
    private Member loser;

    private LocalDateTime playedAt;

    @PrePersist
    public void prePersist() {
        this.playedAt = LocalDateTime.now();
    }
}
