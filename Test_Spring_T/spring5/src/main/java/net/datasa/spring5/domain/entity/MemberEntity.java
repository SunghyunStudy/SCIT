package net.datasa.spring5.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 회원 정보 Entity
 */
@Entity
@Table(name = "spring5_member")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MemberEntity {
	@Id
	@Column(name = "member_id", length = 30)
	String memberId;
	
	@Column(name = "member_password", nullable = false, length = 100)
	String memberPassword;
	
	@Column(name = "member_name", nullable = false, length = 30)
	String memberName;
	
	@Column(name = "email", length = 50)
	String email;
	
	@Column(name = "phone", length = 30)
	String phone;
	
	@Column(name = "address", length = 200)
	String address;
	
	@Column(name = "enabled"
			, columnDefinition = "default 1 check(enabled in (0, 1))")
	Boolean enabled;
	
	@Column(name = "rolename"
	, columnDefinition = "varchar(30) default 'ROLE_USER'" +
			" check (ROLENAME in ('ROLE_USER', 'ROLE_ADMIN'))")
	String rolename;
	
	/**
	 * @PrePersist 는 insert 시점에 작동
	 * DB에 insert 되기 전에 실행되는 콜백 메서드를 지정하는 Annotation
	 */
	@PrePersist
	public void prePersist() {
		if (enabled  == null) this.enabled = true;
		if (rolename == null) this.rolename = "ROLE_USER";
	}
}
