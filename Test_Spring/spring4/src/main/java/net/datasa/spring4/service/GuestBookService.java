package net.datasa.spring4.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datasa.spring4.domain.dto.GuestBookDTO;
import net.datasa.spring4.domain.entity.GuestBookEntity;
import net.datasa.spring4.domain.entity.GuestBookRecommendEntity;
import net.datasa.spring4.domain.entity.GuestBookRecommendKey;
import net.datasa.spring4.repository.GuestBookRecommendRepository;
import net.datasa.spring4.repository.GuestBookRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class GuestBookService {
	private final GuestBookRepository gr;
	private final GuestBookRecommendRepository grr;
	
	public void test() {
		GuestBookEntity entity = GuestBookEntity.builder()
												.name("작성자")
												.password("111")
												.message("글 저장 테스트 중")
												.build();
		gr.save(entity);
	}
	
	public void write(GuestBookDTO dto) {
		GuestBookEntity entity = GuestBookEntity.builder()
												.name(dto.getName())
												.password(dto.getPassword())
												.message(dto.getMessage())
												.build();
		gr.save(entity);
	}
	
	/**
	 * 방명록 글 목록 가져오기
	 * @return 방명록 글 목록
	 */
	public List<GuestBookDTO> getList() {
		
		// 아래 record클래스와 맵핑되는 코드임. 그냥 이론 설명용 ****************
		List<Person> list = new ArrayList<>();
		list.add(new Person("홍길동", 30));
		list.add(new Person("강감찬", 33));
		list.add(new Person("이순신", 11));
		
		Collections.sort(list, new PersonNameComparator());
		list.sort((p1, p2) -> p1.name().compareTo(p2.name()) );
		list.stream().forEach(l -> log.debug("> {}", l)); // list 안의 요소 하나를 각각 l에 저장하고 log를 찍겠다.
		// ****************************************************************************
		
		/**
		 * Spring Data JPA - Sort 객체
		 * 데이터 조회시 정렬을 손쉽게 적용할 수 있도록 해주는 유틸리티 클래스
		 * Sort.Order : "정렬 조건 하나"를 객체로 표현한 것.
		 * 				Java 객체로 사용하므로써 IDE 자동완성 및 컴파일 체크 가능.
		 *
		 * 	enum
		 * 		- 미리 정의된 고정된 값들의 목록을 나타내는 자료형(상수 집합)
		 * 		- enum은 암묵적으로 public static final로 선언
		 * 		- 클래스처럼 사용 가능
		 * 		- 안정성, 코드 가독성을 높임.
		 */
		//Direction은 enum 객체임...
		Sort sort = Sort.by(Sort.Direction.DESC, "inputdate"); // JPA에서 제공하는 sort 객체
		
		// 정렬 기준을 여러개 만들어서 저장가능. 그리고 정렬 메소드가 존재함 .ignoreCase(): 대소문자 무시
		Sort sort2 = Sort.by(
				Sort.Order.desc("name"),
				Sort.Order.asc("num"),
				Sort.Order.desc("inputdate")
		);
		
		List<GuestBookEntity> entityList = gr.findAll(sort);
		List<GuestBookDTO> dtoList = new ArrayList<>();
		
		for(GuestBookEntity entity : entityList){
			GuestBookDTO dto = GuestBookDTO.builder()
					.num(entity.getNum())
					.name(entity.getName())
					.message(entity.getMessage())
					.inputdate(entity.getInputdate())
					.recommendCnt(entity.getRecommendCnt())
					.build();
			dtoList.add(dto);
		}
		return dtoList;
	}
	
	
	public void delete(Integer num, String password) {
		GuestBookEntity entity = gr.findById(num)
				.orElseThrow(() -> new EntityNotFoundException(num + "번 글이 존재하지 않습니다."));
		
		if(!entity.getPassword().equals(password)){
			throw new RuntimeException("비밀번호가 틀립니다.");
		}
		gr.delete(entity);
	}
	
	public GuestBookDTO selectOne(Integer num, String password) {
		GuestBookEntity entity = gr.findById(num)
				.orElseThrow(() -> new EntityNotFoundException(num + "번 글이 존재하지 않습니다."));
		GuestBookDTO dto = new GuestBookDTO();
		if(!entity.getPassword().equals(password)){
			throw new RuntimeException("비밀번호가 틀립니다.");
		}else {
			dto.setNum(entity.getNum());
			dto.setName(entity.getName());
			dto.setPassword(entity.getPassword());
			dto.setMessage(entity.getMessage());
			dto.setInputdate(entity.getInputdate());
			return dto;
		}
	}
	
	public void update(GuestBookDTO dto) {
		GuestBookEntity entity = gr.findById(dto.getNum()).orElseThrow( () -> new EntityNotFoundException(dto.getNum() + "번 글이 존재하지 않습니다."));
		
		entity.setMessage(dto.getMessage());
		
		gr.save(entity);
		
	}
	
	/**
	 * 추천처리
	 * @param num
	 * @param ip
	 */
	public void recommend(Integer num, String ip) {
		GuestBookRecommendKey key = new GuestBookRecommendKey(num, ip);
		
		// 1. 이미 이 IP로 이 글을 추천했는지 확인
		boolean exists = grr.existsById(key);
		if(exists){ // 이미 추천이 된거임. 그래서 예외를 발생
			throw new RuntimeException("이미 추천한 글입니다.");
		}
		
		// 2. 추천 이력 저장
		GuestBookRecommendEntity recommend =
				GuestBookRecommendEntity.builder()
						.id(key)
						.build();
		grr.save(recommend);
		
		// 3. 글 추천수 증가
		GuestBookEntity guest = gr.findById(num)
				.orElseThrow(
						() -> new RuntimeException("글을 찾을 수 없습니다.")
				);
		
		guest.increaseRecommend();
		
	}
}

record Person(String name, int age){} // record는 생성자를 만들지 않아도 되는 클래스임. 대신 수정 불가

class PersonNameComparator implements Comparator<Person>{
	@Override
	public int compare(Person o1, Person o2){
		return o1.name().compareTo(o2.name());
	}
}

