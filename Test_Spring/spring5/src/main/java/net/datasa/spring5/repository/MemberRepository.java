package net.datasa.spring5.repository;

import net.datasa.spring5.domain.entity.MemberEntity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 회원 정보 Repository
 */
@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, String> {

    List<MemberEntity> findByMemberIdContaining(String keyword);

}
