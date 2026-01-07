package net.datasa.spring4_v2.repository;

import net.datasa.spring4_v2.domain.entity.GuestbookRecommendEntity;
import net.datasa.spring4_v2.domain.entity.GuestbookRecommendKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestbookRecommendRepository extends JpaRepository<GuestbookRecommendEntity, GuestbookRecommendKey> {
	
	// JPA 메서드 네이밍 방식, id 안에 있는 필드들 접근: IdGuestbookNum, IdIp
	boolean existsByIdGuestbookNumAndIdIp(Integer guestbookNum, String ip);
}
