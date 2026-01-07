package net.datasa.spring5.domain.entity;

import java.lang.reflect.Member;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 리플 정보 엔티티
 */
@Builder
@Getter
@Setter
// 무한 순환 참조 문제 해결법
@ToString(exclude = "board")
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "spring5_reply")
public class ReplyEntity {

    // 리플 번호
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reply_num")
    private Integer replyNum;

    // 작성자 정보
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", referencedColumnName = "member_id")
    private MemberEntity member;

    // 리플 내용
    @Column(name = "contents", nullable = false, length = 2000)
    private String contents;

    // 작성 시간
    @CreatedDate
    @Column(name = "create_date", columnDefinition = "timestamp default current_timestamp")
    private LocalDateTime createDate;

    // 게시글 정보
    @ManyToOne(fetch = FetchType.LAZY) //
    @JoinColumn(name = "board_num", referencedColumnName = "board_num")
    private BoardEntity board;
}
