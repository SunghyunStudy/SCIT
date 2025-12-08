package net.datasa.ex2.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datasa.ex2.domain.dto.StudentDTO;
import net.datasa.ex2.domain.entity.StudentEntity;
import net.datasa.ex2.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service
 * 기능 구현을 위한 비지니스 로직 담당 계층
 */
@Service
@Slf4j
@Transactional                  // StudentService 에 정의된 public 메서드들의 트랜잭션 자동 관리(예외 발생 시 rollback!)
@RequiredArgsConstructor        // final로 선언된 멤버변수를 매개변수로 받는 생성자를 자동생성.
public class StudentService {
	
	// StudentService에서 StudentRepository를 사용하기 위해, 생성자 기반 의존성 주입(Dependency Injection)
	// StudentRepository는 JpaRepository를 상속받고 있기 때문에 JPA에서 제공하는 CRUD 메서드를 사용할 수 있게 됨.
	private final StudentRepository sr;
	
	
	/*
	 * JPA는 Java의 클래스와 DB의 테이블을 매핑함.
	 * StudentEntity(클래스) ↔ student3(테이블)
	 * 즉, JPA는 DB로부터의 데이터를 Entity 클래스로 담아 옴.
	 */
	
	
	/**********************************************************************
	 * 학생정보 등록
	 * @param student	학생정보
	 */
	public void save(StudentDTO student) {	// Controller에서 받아온 등록할 학생정보
		
		// StudentEntity 타입의 빈 객체 생성( new StudentEntity() 와 동일 )
		StudentEntity entity = StudentEntity.builder().build();
		
		// DTO 에서 Entity 로 데이터 옮겨 담기
		StudentDTO.Student_DTOtoEntity(student, entity);
		
		// JPA의 Insert 기능의 메서드
		sr.save(entity);
	}
	
	
	/**********************************************************************
	 * 학생목록
	 * @return	학생목록
	 */
	public List<StudentDTO> selectAll() {
		
		// JPA의 Select * 기능의 메서드로부터 List Collection에 학생정보 담기
		List<StudentEntity> entityList = sr.findAll();
		// Controller로 return 해주기 위한 DTO 타입의 List 객체 생성
		List<StudentDTO> dtoList       = new ArrayList<>();
		
		// Entity 에서 DTO 로 데이터 옮겨 담기
		for (StudentEntity entity : entityList) {
			StudentDTO dto = StudentDTO.builder().build();
			StudentDTO.Student_EntityToDTO(entity, dto);
			dtoList.add(dto);
		}
		
		// Controller로 학생목록 return
		return dtoList;
	}
	
	
	/**********************************************************************
	 * 학생정보 조회
	 * @param studentId	학번
	 * @return	학생정보
	 */
	public StudentDTO find(int studentId) {	// Controller에서 받아온 학생정보를 조회하기 위한 학번
		
		// 학번에 일치하는 학생정보를 가져오는 메서드(예외 발생 시 rollback!)
		StudentEntity entity = sr.findById(studentId)
				.orElseThrow(EntityNotFoundException::new);
		
		// StudentDTO 타입의 빈 객체 생성( new StudentDTO() 와 동일 )
		StudentDTO dto = StudentDTO.builder().build();
		
		// Entity 에서 DTO 로 데이터 옮겨 담기
		StudentDTO.Student_EntityToDTO(entity, dto);
		
		// Controller로 학생정보 return
		return dto;
	}
	
	
	/**********************************************************************
	 * 학생정보 삭제
	 * @param studentId	학번
	 * @return	삭제 여부
	 */
	public boolean delete(int studentId) { // Controller에서 받아온 학생정보를 조회하기 위한 학번
		
		// 학번에 일치하는 학생정보의 유/무를 가져오는 메서드
		boolean result = sr.existsById(studentId);
		
		if(result) {
			// 학번에 일치하는 학생정보를 삭제하는 메서드
			sr.deleteById(studentId);
			return true;
		}
		
		return false;
	}
	
	
	/**********************************************************************
	 * 학생정보 수정
	 * @param student	학생정보
	 */
	public void update(StudentDTO student) { // Controller에서 받아온 수정할 학생정보
		
		// 학번에 일치하는 학생정보를 가져오는 메서드(예외 발생 시 rollback!)
		StudentEntity entity = sr.findById(student.getStudentId())
				.orElseThrow(EntityNotFoundException::new);
		
		// 학번은 PK라 수정 불가, 나머지 데이터를 수정하기 위해 DTO 에서 Entity 로 데이터 옮겨담기
		entity.setName(student.getName());
		entity.setMajor(student.getMajor());
		entity.setJava(student.getJava());
		entity.setDb(student.getDb());
		entity.setWeb(student.getWeb());
		
		// JPA의 Update 기능의 메서드
		sr.save(entity);
	}
}
