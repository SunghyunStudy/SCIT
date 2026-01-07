package net.datasa.spring5.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datasa.spring5.domain.dto.MemberDTO;
import net.datasa.spring5.domain.entity.MemberEntity;
import net.datasa.spring5.repository.MemberRepository;
import net.datasa.spring5.security.AuthenticatedUserDetailsService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Service;

/**
 * 회원정보 서비스
 */
@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MemberService {

	// 암호화 인코더
	private final BCryptPasswordEncoder passwordEncoder;

	// 회원 관련 Repository
	private final MemberRepository mr;

	// 사용자 정보 가져오기
	private final AuthenticatedUserDetailsService aud;

	// 저장테스트
	public void test() {
		MemberEntity entity = MemberEntity.builder()
				.memberId("aaa")
				.memberPassword(passwordEncoder.encode("111"))
				.memberName("홍길동")
				.email("aaa@aaa.vc")
				.phone("010-0000-0000")
				.address("경기도 고양시 일산서구")
				.enabled(true)
				.rolename("ROLE_USER")
				.build();
		mr.save(entity); // 이 시점에 @PrePersist로 감.

	}

	public void join(MemberDTO memberDTO) {
		MemberEntity entity = MemberEntity.builder()
				.memberId(memberDTO.getMemberId())
				.memberPassword(passwordEncoder.encode(memberDTO.getMemberPassword()))
				.memberName(memberDTO.getMemberName())
				.email(memberDTO.getEmail())
				.phone(memberDTO.getPhone())
				.address(memberDTO.getAddress())
				.enabled(true)
				.rolename("ROLE_USER")
				.build();
		mr.save(entity);
	}

	/**
	 * 가입시 아이디 중복 확인
	 * 
	 * @param searchId
	 * @return 해당 아이디로 가입 가능 여부
	 */
	public boolean idCheck(String searchId) {
		return !mr.existsById(searchId);
	}

	public List<MemberDTO> selectAll() {

		Sort sort = Sort.by(
				Sort.Order.asc("rolename"),
				Sort.Order.desc("memberName"));

		List<MemberEntity> entityList = mr.findAll(sort);
		List<MemberDTO> dtoList = new ArrayList<>();

		for (MemberEntity entity : entityList) {
			MemberDTO dto = MemberDTO.builder()
					.memberId(entity.getMemberId())
					.memberName(entity.getMemberName())
					.email(entity.getEmail())
					.phone(entity.getPhone())
					.address(entity.getAddress())
					.enabled(entity.getEnabled())
					.rolename(entity.getRolename())
					.build();
			dtoList.add(dto);
		}

		return dtoList;
	}

	/**
	 * 권한 변경
	 * 
	 * @param memberId
	 */
	@PreAuthorize("hasRole('ADMIN')")
	public void update(String memberId) {
		MemberEntity entity = mr.findById(memberId)
				.orElseThrow(() -> new EntityNotFoundException("회원이 존재하지 않습니다."));
		String updateRolename = entity.getRolename().equals("ROLE_USER") ? "ROLE_ADMIN" : "ROLE_USER";
		entity.setRolename(updateRolename);
	}

	/**
	 * 계정 활성화/비활성화 처리
	 * 
	 * @param enabled
	 * @param memberId 회원 아이디
	 */
	@PreAuthorize("hasRole('ADMIN')")
	public void updateEnabled(String memberId, boolean enabled) {
		// 1. DB에서 회원 정보 조회 (없으면 에러 발생)
		MemberEntity entity = mr.findById(
				memberId)
				.orElseThrow(() -> new RuntimeException("해당 아이디의 회원이 없습니다."));

		entity.setEnabled(enabled);

	}

	/**
	 * 휴면 계정 해제 처리
	 * 
	 * @param memberId
	 * @param memberPassword
	 */
	public void inactive(String memberId, String memberPassword) {
		MemberEntity entity = mr.findById(memberId)
				.orElseThrow(() -> new EntityNotFoundException("회원이 존재하지 않습니다."));

		// 비밀번호 체크
		if (!passwordEncoder.matches(memberPassword, entity.getMemberPassword())) {
			throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
		}

		entity.setEnabled(true);
	}

	public void autoLogin(String memberId, String memberPassword, HttpServletRequest request) {
		// UserDetails 회원 정보 가져오기
		UserDetails userDetails = aud.loadUserByUsername(memberId);

		// 인증 객체 만든거임...
		// 로그인 폼으로 로그인할때는 자동으로 호출이 되기 때문에 아마 처음볼거임.
		// 생성자가 3개 (Principal, Credentials, Authorities) 를 썼다는 건 이미 인증이 끝난 상태라는 거임.
		// 생성자 2개짜리는 왜 안쓰냐? -> inactive에서 비밀번호 체크를 했기 때문.
		// 이 아래 3개를 묶어서 인증 토큰(주민등록증)을 만드는거임.
		UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
				userDetails,
				userDetails.getPassword(),
				userDetails.getAuthorities());

		// SecurityContext 생성 후 주입
		// 지갑 생성
		SecurityContext context = SecurityContextHolder.createEmptyContext();
		// 지갑에 토큰(민증) 삽입
		context.setAuthentication(auth);
		// SecurityContextHolder 라는 글로벌 저장소에 set 해두는거임. 그러면 스프링 시큐리티가 알아서 토큰을 갖다 쓸거임.
		SecurityContextHolder.setContext(context);

		// 세션에 SecurityContext 저장
		// 새로고침하면 로그인 풀리는걸 방지
		HttpSession session = request.getSession(true);
		session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, context);
	}

	/**
	 * keyword(검색)에 일치하는 회원목록 조회
	 * 
	 * @param keyword
	 * @return
	 */
	public List<MemberDTO> searchById(String keyword) {
		List<MemberEntity> resultEntityList = mr.findByMemberIdContaining(keyword); // repository의 CRUD를 제외한 기능임. 키워드가
																					// 포함된 id를 가져옴

		List<MemberDTO> resultDtoList = new ArrayList<>();
		for (MemberEntity entity : resultEntityList) {
			MemberDTO dto = MemberDTO.builder()
					.memberId(entity.getMemberId())
					.memberName(entity.getMemberName())
					.email(entity.getEmail())
					.phone(entity.getPhone())
					.address(entity.getAddress())
					.rolename(entity.getRolename())
					.enabled(entity.getEnabled())
					.build();
			resultDtoList.add(dto);
		}
		return resultDtoList;
	}

	/**
	 * 회원정보 조회
	 * 
	 * @param memberId
	 * @return 사용자 한명의 정보
	 */
	public MemberDTO getMember(String memberId) {
		MemberEntity entity = mr.findById(memberId)
				.orElseThrow(() -> new EntityNotFoundException("회원이 존재하지 않습니다."));

		MemberDTO dto = MemberDTO.builder()
				.memberId(entity.getMemberId())
				.memberName(entity.getMemberName())
				.email(entity.getEmail())
				.phone(entity.getPhone())
				.address(entity.getAddress())
				.enabled(entity.getEnabled())
				.rolename(entity.getRolename())
				.build();

		return dto;
	}

	public void updateMember(MemberDTO dto) {
		MemberEntity entity = mr.findById(dto.getMemberId())
				.orElseThrow(() -> new EntityNotFoundException("회원이 존재하지 않습니다."));

		if (!dto.getMemberPassword().isEmpty()) {
			entity.setMemberPassword(passwordEncoder.encode(dto.getMemberPassword()));
		}

		entity.setAddress(dto.getAddress());
		entity.setEmail(dto.getEmail());
		entity.setPhone(dto.getPhone());
		entity.setMemberName(dto.getMemberName());

		mr.save(entity);
	}
}
