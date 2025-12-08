package net.datasa.spring4.repository;

import net.datasa.spring4.domain.entity.GuestBookRecommendEntity;
import net.datasa.spring4.domain.entity.GuestBookRecommendKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestBookRecommendRepository extends JpaRepository<GuestBookRecommendEntity, GuestBookRecommendKey> {
}
