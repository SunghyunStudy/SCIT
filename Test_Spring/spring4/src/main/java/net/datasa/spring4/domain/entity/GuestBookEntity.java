package net.datasa.spring4.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="guestbook")
/*
	Spring Data JPA의 Auditing 기능을 사용하기 위해 Entity 클래스에 추가
	- Entity의 생성 및 수정 시점에 자동으로 특정 필드(생성일, 수정일 ..)를 업데이트
	- ex.
			@CreateDate	최초 저장시 시간 자동 저장
			@LastModifiedDate 수정될때마다 시간 자동 갱신
			@CreatedBy 최초 저장시 작성자 저장
			@LastModifiedBy 수정 시 작성자 저장
 */
@EntityListeners(AuditingEntityListener.class)
public class GuestBookEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "num")
	private Integer num; 		// 글번호, PK
	
	@Column(name = "name", nullable = false, length = 100)
	private String name;
	
	@Column(name = "password", nullable = false, length = 100)
	private String password;
	
	@Column(name = "message", nullable = false, columnDefinition = "text")
	private String message;
	
	@CreatedDate
	@Column(name = "inputdate", updatable = false)
	private LocalDateTime inputdate;
	
	// 추천수 칼럼 추가
	@Builder.Default
	@Column(name = "recommend_cnt", nullable = false)
	private Integer recommendCnt = 0;
	
	// 추천 증가 메서드
	// 서비스 단에서 그냥 1더해서 저장해도 되지만 여기서 숫자 증가 로직을 만들어놓는게 더 안정적임.
	public void increaseRecommend(){
		this.recommendCnt++;
	}
	

}
