package net.datasa.spring6.repository;

import net.datasa.spring6.domain.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Integer> {
    // 인터페이스이기 때문에 가능한 마법이 또 있습니다. 님이 인터페이스에 메서드 이름만 규칙에 맞춰 지으면 SQL도 자동으로 짜줍니다.
}