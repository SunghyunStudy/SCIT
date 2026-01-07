package net.datasa.spring4.repository;

import net.datasa.spring4.domain.entity.GuestBookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestBookRepository extends JpaRepository<GuestBookEntity, Integer> {

}
