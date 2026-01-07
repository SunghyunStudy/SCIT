package net.datasa.spring5.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import net.datasa.spring5.domain.entity.BoardEntity;

/**
 * 게시판 관련 Repository
 */
@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Integer> {

    // JPQL : 직접 쿼리문을 입력
    // join fetch를 쓰면 N+1 문제를 방지할 수 있음.
    @Query("""
            select b
            from BoardEntity b
            join fetch b.member
            order by b.boardNum desc
            """)
    List<BoardEntity> findAllWithMemberOrderByBoardNumDesc();

    // list 메서드
    // 제목에 searchWord가 포함된 데이터 조회
    Page<BoardEntity> findByTitleContaining(String searchWord, Pageable pageable);

    // 내용에 searchWord가 포함된 데이터 조회
    Page<BoardEntity> findByContentsContaining(String searchWord, Pageable pageable);

    // 작성자ID가 searchWord와 일치하는 데이터 조회
    Page<BoardEntity> findByMember_MemberId(String searchWord, Pageable pageable);

    // 제목, 내용, 작성자ID 모두 포함하는 통합 검색
    // Query의 어노테이션의 쿼리문이 우선이기 때문에 메서드명(searchAll)은 여기서 큰 의미 없음
    @Query("SELECT board " +
            "FROM BoardEntity board " +
            "WHERE board.title      LIKE %:searchWord% " +
            "OR board.contents     LIKE %:searchWord% " +
            "OR board.member.memberId LIKE %:searchWord% " +
            "ORDER BY board.boardNum DESC")
    Page<BoardEntity> searchAll(@Param("searchWord") String searchWord, Pageable pageable);

}
