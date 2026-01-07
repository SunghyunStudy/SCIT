package net.datasa.spring5.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datasa.spring5.domain.entity.MemberEntity;
import net.datasa.spring5.repository.MemberRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 사용자 인증 처리
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class AuthenticatedUserDetailsService implements UserDetailsService {

	// 회원 관련 Repository
	private final MemberRepository mr;

	// 이거 오버라이딩 해야됨
	// 입력 받은 id 값이 loadUserByUsername로 들어감
	// 인증정보가 이 메서드로 들어감...?
	@Override
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
		log.debug("로그인 시도: {}", id);

		MemberEntity memberEntity = mr.findById(id)
				.orElseThrow(() -> {
					return new UsernameNotFoundException(id + " : 없는 ID 입니다.");
				});

		log.debug("조회 정보: {}", memberEntity);

		// 인증정보 생성
		AuthenticatedUser user = AuthenticatedUser.builder()
				.id(memberEntity.getMemberId())
				.password(memberEntity.getMemberPassword())
				.name(memberEntity.getMemberName())
				.enabled(memberEntity.getEnabled())
				.rolename(memberEntity.getRolename())
				.build();

		log.debug("인증 정보: {}", user);

		return user;
	}
}
