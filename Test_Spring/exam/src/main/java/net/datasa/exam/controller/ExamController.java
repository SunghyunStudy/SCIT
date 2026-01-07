package net.datasa.exam.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datasa.exam.domain.dto.StudentDTO;
import net.datasa.exam.domain.entity.StudentEntity;
import net.datasa.exam.service.ExamService;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ExamController {

    private final ExamService es;

    @GetMapping("exam")
    public String exam() {
        return "exam";
    }

    @PostMapping("exam")
    public String exam(StudentDTO studentDTO,
            RedirectAttributes ra,
            Model model) {
        log.debug("studentDTO 출력 {}", studentDTO);
        try {
            es.write(studentDTO);
            return "redirect:/";

        } catch (Exception e) {
            ra.addFlashAttribute("msg", e.getMessage());
            model.addAttribute("dto", studentDTO);

            return "redirect:exam";
        }
    }

    @GetMapping("list")
    public String list(Model model, @RequestParam(value = "sort", defaultValue = "seq") String sort) {
        try {
            List<StudentDTO> dtoList = es.selectAll(sort);
            model.addAttribute("studentList", dtoList);
        } catch (Exception e) {
            log.debug(e.getMessage());
        }
        return "list";
    }

    @GetMapping("deleteForm")
    public String delete(@RequestParam("seq") Integer seq, Model model) {
        try {
            StudentDTO studentDTO = es.selectOne(seq);
            model.addAttribute("student", studentDTO);
            return "deleteForm";
        } catch (Exception e) {
            log.debug(e.getMessage());

            return "redirect:/list";
        }
    }

    @PostMapping("delete")
    public String delete(@RequestParam("seq") Integer seq,
            @RequestParam("password") String password,
            RedirectAttributes ra) {
        try {
            es.delete(seq, password);
            return "redirect:/list";
        } catch (Exception e) {
            ra.addFlashAttribute("msg", e.getMessage());
            return "redirect:/deleteForm?seq=" + seq;
        }
    }
}
