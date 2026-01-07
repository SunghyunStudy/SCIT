package net.datasa.spring4_v2.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

// 여러 개의 필드를 하나의 객체로 묶어서 엔티티 안에서 필드처럼 사용하게 해주는 기능
// Entity가 아님.
@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
// Serializable 은 JPA에서 @EmbeddedId 같은 복합키 클래스에 반드시 써야 하는 규칙
public class GuestbookRecommendKey implements Serializable {
	
	@Column(name = "guestbook_num")
	private Integer guestbookNum;
	
	@Column(name = "ip", length = 50)
	private String ip;
	
}
