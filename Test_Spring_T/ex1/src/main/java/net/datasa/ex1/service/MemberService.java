package net.datasa.ex1.service;

import lombok.extern.slf4j.Slf4j;
import net.datasa.ex1.domain.Member;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class MemberService {

	// 회원목록(DB 대체)
	private List<Member> memberList = new ArrayList<>();
	
	// 회원가입
	public boolean save(Member member) {
		
		// 회원목록에 저장되지 않는 ID만 회원가입처리
		for( Member m : memberList){
			if(m.getId().equals(member.getId())){
				return false;
			}
		}
		memberList.add(member);
		return true;
	}
	
	// 회원확인
	public boolean loginCheck(String id, String pw) {
		
		// 회원목록에 저장된 ID, PW의 일치여부 체크
		for(Member m : memberList){
			if(m.getId().equals(id) && m.getPw().equals(pw)){
				return true;
			}
		}
		
		return false;
	}
	
	// 회원목록 조회
	public List<Member> selectAll() {
		return this.memberList;
	}
	
}
