package net.datasa.spring5.security;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

/**
 * 회원 인증 정보 객체
 */
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
	
	// 계정의 유효기간이 만료되지 않았는지를 확인
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	
	// 계정이 잠겨 있는지 여부를 확인
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	
	// 비밀번호가 만료되지 않았는지를 확인
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	// 계정이 활성화되었는지 여부를 확인(정상 아이디인지 체크)
	@Override
	public boolean isEnabled() {
		return this.enabled;
	}
	
	// 사용자가 어떤 권한을 가지고 있는지 Spring Security에게 알려주는 메서드
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singletonList(
				new SimpleGrantedAuthority(this.rolename)
		);
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
