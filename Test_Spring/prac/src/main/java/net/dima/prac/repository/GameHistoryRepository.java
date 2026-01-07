package net.dima.prac.repository;

import net.dima.prac.entity.GameHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameHistoryRepository extends JpaRepository<GameHistory, Long> {
}
