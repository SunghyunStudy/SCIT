package net.datasa.spring4_v2.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/**
 * guestbook 테이블과 매핑되는 엔티티
 * JPA Auditing(@CreatedDate)으로 작성시간 자동 관리
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
/*
	@EntityListeners(AuditingEntityListener.class)
	Spirng Data JPA의 Auditing 기능을 사용하기 위해 엔티티 클래스에 추가하는 Annotation
	 - Entity의 생성 및 수정 시점에 자동으로 특정 필드(생성일, 수정일 ..)를 업데이트
	 - JPA의 Auditing 기능 : Entity의 생성 및 수정 시점에 자동으로 특정필드를 기록할 수 있도록 도와주는 기능.
*/
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "guestbook")
public class GuestbookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "num")
	private Integer num;                    //글번호. 기본키

    @Column(name = "name", nullable = false, length = 100)
	private String name;               //작성자 이름

    @Column(name = "password", nullable = false, length = 100)
	private String password;            //비밀번호

    @Column(name = "message", nullable = false, columnDefinition = "text")
	private String message;             //게시글 내용

	// @CreatedDate 어노테이션은 엔티티의 최초 생성 시간을 자동으로 넣어주는 어노테이션
    @CreatedDate
    @Column(name = "inputdate", updatable = false)
    private LocalDateTime inputdate;
	
	// 추천 수 컬럼 추가
	// @Builder.Default 안 붙이면 빌더로 만들 때 null 들어감
	@Builder.Default
	@Column(name = "recommend_cnt", nullable = false)
	private Integer recommendCnt = 0;
	
	// 추천 증가 메서드(비즈니스 메서드)
	public void increaseRecommend() {
		this.recommendCnt++;
	}
	
}