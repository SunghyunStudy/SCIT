package net.datasa.spring4.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 여러개의 멤버변수를 하나의 객체로 묶어 엔티티 안에서 멤버변수처럼 사용하게 해주는 기능
// Entity가 아님.
@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GuestBookRecommendKey {

	@Column(name = "guestbook_num")
	private Integer guestbookNum;

	@Column(name = "ip", length = 50)
	private String ip;

}
