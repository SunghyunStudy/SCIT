package net.datasa.ex2.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datasa.ex2.domain.dto.StudentDTO;
import net.datasa.ex2.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controller
 * 요청을 받고 응답을 처리하는 요청 담당 계층
 * Student 전용 요청 담당
 */
@Controller
@Slf4j
@RequestMapping("/student")		// StudentController 에 정의된 모든 메서드들의 공통 경로 추가(ex. /student/new , /student/save ..)
@RequiredArgsConstructor        // final로 선언된 멤버변수를 매개변수로 받는 생성자를 자동생성.
public class StudentController {
	
	// StudentController에서 StudentService를 사용하기 위해, 생성자 기반 의존성 주입(Dependency Injection)
	private final StudentService ss;
	
	/**********************************************************************
	 * 학생정보 등록 페이지
	 * @return	enroll.html
	 */
	@GetMapping("/new")
	public String newStudent() {
		// enroll.html 이동
		return "student/enroll";
	}
	
	
	/**********************************************************************
	 * 학생정보 등록
	 * @param	student 등록할 학생정보
	 * @return	메인페이지
	 */
	@PostMapping("/save")
	public String save(
			StudentDTO student        // enroll.html 로부터 받은 학생정보 입력값
	) {
		
		log.debug("[StudentController-/save] 학생정보 입력값: {}", student);
		
		// StudentService 에서 발생한 예외가 있다면 예외처리
		try {
			// StudentService 에게 학생정보를 등록처리하기 위한 메서드 호출
			ss.save(student);
			log.debug("[StudentController-/save] 학생정보 등록 성공!");
		} catch (Exception e) {		// 구체적인 xxxException 타입으로 처리하는게 좋음
			log.debug("[StudentController-/save] [예외발생] 등록되지 않았습니다.");
			return "redirect:/";
		}
		
		return "redirect:/";
	}
	
	
	/**********************************************************************
	 * 학생정보 수정
	 * @param	studentId 수정할 학생의 학번
	 * @param	model Model
	 * @return	updateForm.html  or  메인페이지
	 */
	@GetMapping("update")
	public String updateForm(@RequestParam("sid") int studentId, Model model) {
		
		log.debug("[StudentController-/update] 수정할 학생의 학번: {}", studentId);
		try {
			StudentDTO student = ss.find(studentId);
			log.debug("[StudentController-/update] 학생정보: {}", student);
			model.addAttribute("student", student);
			return "student/updateForm";
		} catch (Exception e) {
			log.debug("[StudentController-/update] [예외 발생] 존재하지 않는 학생입니다.");
			return "redirect:/";
		}
	}
	
	
	/**********************************************************************
	 * 학생정보 수정 (처리)
	 * @param	student 수정할 학생정보
	 * @return	home.html
	 */
	@PostMapping("update")
	public String update(StudentDTO student) {
		
		log.debug("[StudentController-/update] 수정할 학생정보: {}", student);
		try {
			ss.update(student);
			log.debug("[StudentController-/update] 수정 성공!");
		} catch (Exception e) {
			log.debug("[StudentController-/update] [예외 발생] 존재하지 않는 학생입니다.");
		}
		
		return "redirect:/";
	}
	
	
	/**********************************************************************
	 * 학생정보 삭제
	 * @param	studentId 삭제할 학생의 학번
	 * @return	home.html
	 */
	@GetMapping("delete")
	public String delete(@RequestParam("sid") int studentId) {
		
		log.debug("[StudentController-/delete] 학번: {}", studentId);
		try {
			boolean result = ss.delete(studentId);
			if (result) {
				log.debug("[StudentController-/delete] 삭제되었습니다.");
			} else {
				log.debug("[StudentController-/delete] 해당 학생이 존재하지 않습니다.");
			}
			return "redirect:/";
		} catch (Exception e) {
			log.debug("[StudentController-/delete] [예외발생] 삭제되지 않았습니다.");
			return "redirect:/";
		}
	}
	
	
	/**********************************************************************
	 * 학생정보 조회
	 * @param	studentId 조회할 학생의 학번
	 * @param	model Model
	 * @return	select.html  or  메인페이지
	 */
	@GetMapping("select")
	public String select(@RequestParam("sid") int studentId, Model model) {
		
		log.debug("[StudentController-/select] 학번: {}", studentId);
		try {
			StudentDTO student = ss.find(studentId);
			log.debug("[StudentController-/select] 학생정보: {}", student);
			model.addAttribute("student", student);
			return "student/select";
		} catch (Exception e) {
			log.debug("[StudentController-/select] [예외발생] 존재하지 않는 학생입니다.");
			return "redirect:/";
		}
	}
}
