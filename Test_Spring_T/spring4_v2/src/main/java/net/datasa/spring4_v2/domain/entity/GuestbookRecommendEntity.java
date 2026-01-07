package net.datasa.spring4_v2.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "guestbook_recommend")
@EntityListeners(AuditingEntityListener.class)
// 엔티티 객체가 아무렇게나 생성되지 못하도록 막음
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class GuestbookRecommendEntity {
	
	// 복합 키
	@EmbeddedId
	private GuestbookRecommendKey id;
	
	@CreatedDate
	@Column(name = "create_at", nullable = false)
	private LocalDateTime createdAt;
}
