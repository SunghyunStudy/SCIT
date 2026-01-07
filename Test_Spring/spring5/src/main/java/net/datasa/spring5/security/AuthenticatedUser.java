package net.datasa.spring5.security;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Slf4j
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class AuthenticatedUser implements UserDetails {

	private String id;
	private String password;
	private String name;
	private String rolename;
	private boolean enabled;

	// 페일러를 만들어서 구체적으로 메서드 하나하나 마다 오류 메시지를 만들면
	// login.Form.html에서 구체적으로 설정해서 메시지를 띄울 수 있을 듯
	// 지금은 로그인 실패, enabled의 값 외에도 다른 메서드에서 false가 떠도 로그인 실패가 됨
	// param.error 때문에 ㅇㅇ

	// enabled 외에 다른 조건들도 쓰고 싶으면 직접 db 칼럼과 변수를 만들어야 됨.

	// ctrl + o 눌러서 오버라이딩 해야 오류없이 사용 가능

	// 계정의 유효기간이 만료되지 않았는지를 확인
	@Override
	public boolean isAccountNonExpired() {
		// return UserDetails.super.isAccountNonExpired();
		return true; // 계정이 활성화 되어있는 지 여부를 사용자가 바꿔서 만들면 됨..? 이거 좀 더 검색해보자
	}

	// 계정이 잠겨 있는지 여부를 확인
	@Override
	public boolean isAccountNonLocked() {
		// return UserDetails.super.isAccountNonLocked();
		return true; // true 하면 뭐인거지
	}

	// 비밀번호가 만료되지 않았는지를 확인 (90일마다 변경하라고 띄우는거)
	@Override
	public boolean isCredentialsNonExpired() {
		// return UserDetails.super.isCredentialsNonExpired();
		return true;
	}

	// 계쩡이 활성화되어쓰지 여부를 확인(정상 아이디인지 체크)
	@Override
	public boolean isEnabled() {
		// return UserDetails.super.isEnabled();
		//
		return this.enabled;
	}

	// 사용자가 어떤 권한을 갖고 있는지 Spring Security에게 알려주는 메서드
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// return List.of();
		return Collections.singletonList(
				new SimpleGrantedAuthority(this.rolename));
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.id;
	}
}
