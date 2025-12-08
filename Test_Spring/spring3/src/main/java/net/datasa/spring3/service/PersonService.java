package net.datasa.spring3.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datasa.spring3.domain.dto.PersonDTO;
import net.datasa.spring3.domain.entity.PersonEntity;
import net.datasa.spring3.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
// 메서드나 클래스에 적용하면, 해당 메서드가 호출될 떄 트랜잭션이 시작되고,
// 정상적으로 메서드의 실행이 완료되면 트랜잭션 Commit,
// 예외가 발생하면 트랜잭션 Rollback
@Transactional
// homeController에서 다른 클래스의 인스턴스를 사용하고 싶으면 Autowired를 붙임으로서 변수의 인스턴스를 사용할 수 있었는데
// RequiredArgsConstructor도 비슷한건가봄
// 클래스 안의 final 필드를 자동으로 생성자에 넣어줌
@RequiredArgsConstructor
public class PersonService {
	
	private final PersonRepository pr;
	
	/*
		JpaRepository 기본 제공하는 메서드
			메서드			설명
			save(entity)	Insert or Update
			findById(id)	ID 기준 조회
			findAll()		전체조회
			delete(entity)	엔티티 삭제
			deleteById(id)	ID 기준 삭제
			count()			전체 개수 조회
	 */
	
	public void test() {
		// 임의의 데이터 생성
		PersonEntity entity = new PersonEntity();
		entity.setId("aaaa");
		entity.setName("김아무개");
		entity.setAge(29);
	
		// save는 jpa의 기본 메서드임
		// repository에 아무런 메서드를 생성하지 않았는데도 JpaRepository에 선언되어 있음.
		// 이거로 저장하면 db에 들어가있을거임.
		pr.save(entity);
	}
	
	public void insert(PersonDTO dto) {
//		pr.save(dto);  // 이거 안됨 dto를 entity객체로 바꿔줘야함
		
		// Controller로부터 받아오기는 PersonDTO 타입으로 받아왔지만
		// JPA가 요구하는 타입은 PersonEntity 타입이라서
		// 옮겨 담자
		PersonEntity entity = new PersonEntity();
		entity.setId(dto.getId());
		entity.setName(dto.getName());
		entity.setAge(dto.getAge());
		
		pr.save(entity);
	}
	
	public PersonDTO select(String id) {
		
		/*
			Optional<T>는
			null 값으로 인한 NullPointerException을 방지하기 위한 자바 클래스
			" 값이 있을 수도, 없을 수도 있다."는 것으로 명시적으로 표현하며,
			값이 없을 경우에 처리하는 방식을 메서드로 제공.
			
			Optional<T> findById(ID id); -> CrudRepository에 이렇게 생성되어 있음
		 */
		
		PersonEntity entity = pr.findById(id)
								.orElseThrow(() -> new EntityNotFoundException("회원이 존재하지 않습니다."));
		// id값으로 db를 조회에서 데이터를 가져와서 entity객체에 담음.
		// repository로 부터 findByid(id)로 id값과 일치하는 정보를 entity에 담겠다
		// 만약에 없다면 null값을 변수에 저장하겠다.
		
		
		// orElse(null)을 쓸경우 아래 코드 사용
//		if(entity == null){
//			return null;
//		}
		
		PersonDTO dto = new PersonDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setAge(entity.getAge());
		
		return dto;
	}
	
	/**
	 * ID에 일치하는 회원 1명 삭제
	 * @param id	삭제할 아이디
	 */
	public void delete(String id) {
		// id를 기준으로 삭제처리해주는 jpa 기본 메서드.
//		pr.deleteById(id);
		
		// orElseThrow : optional임. null일 경우 if문을 굳이 쓰지 않아도 처리할 수 있게끔 해줌.
		// 위의 orElse에서는 null일 경우 그냥 null을 넣어줬는데
		// orElseThrow는 예외를 발생 시킴.
		PersonEntity entity = pr.findById(id)
				.orElseThrow( () -> new EntityNotFoundException("회원이 존재하지 않습니다."));
		
		pr.delete(entity);
	}
	
	/**
	 * 모든 사용자 정보 조회
	 * @return 사용자 정보가 담긴 객체들의 리스트
	 */
	public List<PersonDTO> selectAll() {
		List<PersonEntity> entityList = pr.findAll();
		List<PersonDTO> dtoList = new ArrayList<>();
		
		for(PersonEntity entity : entityList){
			PersonDTO dto = new PersonDTO();
			dto.setId(entity.getId());
			dto.setName(entity.getName());
			dto.setAge(entity.getAge());
			dtoList.add(dto);
		}
		
		return dtoList;
	}
	
	/**
	 * 사용자 정보 수정
	 * @param dto
	 */
	public void update(PersonDTO dto) {
		// entity를 찾아서 가져옴
		PersonEntity entity = pr.findById(dto.getId())
		.orElseThrow(
				() -> new EntityNotFoundException("회원이 존재하지 않습니다.")
		);
		log.debug("수정 전: {}", entity);
		
		entity.setName(dto.getName());
		entity.setAge(dto.getAge());
		
		log.debug("수정 후: {}", entity);
		pr.save(entity);
		
	}
}
