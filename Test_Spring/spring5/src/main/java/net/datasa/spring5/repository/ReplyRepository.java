package net.datasa.spring5.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import net.datasa.spring5.domain.entity.ReplyEntity;

/**
 * 리플 관련 Repository
 */
@Repository
public interface ReplyRepository extends JpaRepository<ReplyEntity, Integer> {

    List<ReplyEntity> findAllByBoard_BoardNum(Integer boardNum);

    // JPQL 방식
    @Query("""
            select r
            from ReplyEntity r
            join fetch r.member
            where r.board.boardNum = :boardNum
            order by r.createDate desc
            """)
    List<ReplyEntity> findByBoardNumWithMemberOrderByCreateDateDesc(@Param("boardNum") Integer boardNum);

    // 쿼리 메서드 방식 : 메서드 이름 규칙에 맞춰 작성...
    List<ReplyEntity> findAllByMember_MemberId(String memberId, Sort sort);

}
