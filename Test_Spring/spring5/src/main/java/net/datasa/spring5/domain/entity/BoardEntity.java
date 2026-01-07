package net.datasa.spring5.domain.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.datasa.spring5.domain.dto.BoardDTO;

/**
 * 게시글 정보 엔티티
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "spring5_board")
public class BoardEntity {

    // 게시글 번호
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_num")
    private Integer boardNum;

    // 작석자 정보(외래키)
    // 지연로딩
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", referencedColumnName = "member_id")
    private MemberEntity member;

    // 글 제목
    @Column(name = "title", nullable = false, length = 1000)
    private String title;

    // 글 내용
    @Column(name = "contents", nullable = false, columnDefinition = "text")
    private String contents;

    // 조회수
    @Column(name = "view_count", columnDefinition = "integer default 0")
    private Integer viewCount;

    // 추천수
    @Column(name = "like_count", columnDefinition = "integer default 0")
    private Integer likeCount = 0;

    // 첨부파일의 원래 이름
    @Column(name = "original_name")
    private String originalName;

    // 첨부파일의 저장된 이름
    @Column(name = "file_name")
    private String fileName;

    // 작성시간
    @CreatedDate
    @Column(name = "create_date", columnDefinition = "timestamp default current_timestamp")
    private LocalDateTime createDate;

    // 수정시간
    @LastModifiedDate
    @Column(name = "update_date", columnDefinition = "timestamp default current_timestamp")
    private LocalDateTime updateDate;

    // 게시글의 리플정보
    // mapped : board가 ReplyEntity의 board 필드에 의해 매핑됨을 나타냄
    // cascade : BoardEntity가 변경될 때 ReplyEntity도 자동으로 처리되도록 지원
    // orphaRemoval : true일 경우 BoardEntity에서 삭제된 ReplyEntity는 자동으로 삭제
    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReplyEntity> replyList;

    @PrePersist
    public void prePersist() {
        if (viewCount == null)
            this.viewCount = 0;
        if (likeCount == null)
            this.likeCount = 0;
    }

    public static BoardEntity convertToBoardDTO(BoardDTO boardDTO) {
        throw new UnsupportedOperationException("Unimplemented method 'convertToBoardDTO'");
    }
}
