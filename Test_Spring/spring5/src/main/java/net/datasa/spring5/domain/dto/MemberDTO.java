package net.datasa.spring5.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MemberDTO {
	String memberId;
	String memberPassword;
	String memberName;
	String email;
	String phone;
	String address;
	Boolean enabled;
	String rolename;
}
