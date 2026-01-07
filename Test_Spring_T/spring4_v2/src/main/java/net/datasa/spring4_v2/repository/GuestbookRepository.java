package net.datasa.spring4_v2.repository;

import net.datasa.spring4_v2.domain.entity.GuestbookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * guestbook 테이블 관련 repository
 */
@Repository
public interface GuestbookRepository extends JpaRepository<GuestbookEntity, Integer> {

}
