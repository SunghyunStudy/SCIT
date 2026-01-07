package net.datasa.spring5.repository;

import net.datasa.spring5.domain.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 회원 정보 Repository
 */
@Repository
public interface MemberRepository
	extends JpaRepository<MemberEntity, String> {
	
	// SELECT * FROM member WHERE member_id LIKE '%keyword%'
	List<MemberEntity> findByMemberIdContaining(String keyword);
}
