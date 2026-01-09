package net.datasa.spring6.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datasa.spring6.domain.dto.CommentDTO;
import net.datasa.spring6.repository.CommentRepository;
import net.datasa.spring6.service.CommentService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/board/comment")
public class CommentRestController {
    private final CommentService cs;

    // 댓글 목록 가져오기
    @GetMapping("/list")
    public ResponseEntity<?> getComment() {
        List<CommentDTO> list = cs.getAllList();
        return ResponseEntity.ok(list); // 이걸 쓰는 이유는 잘 모르겠음 그냥 List<CommentDTO>로 리턴해도 됨
    }

    // 댓글 저장하기
    @PostMapping("/write")
    public void setComment(CommentDTO commentDTO) {
        log.debug("commentDTO 확인 {}", commentDTO);
        cs.setComment(commentDTO);
    }

    // 댓글 삭제하기
    @DeleteMapping("/{num}")
    public void delComment(@PathVariable("num") Integer num) {
        log.debug("삭제 번호 컨트롤러{}", num);
        String msg;
        try {
            cs.deletComment(num);
        } catch (Exception e) {
        }
    }

    // 댓글 수정하기
    @PatchMapping("/{num}")
    public void upComment(@PathVariable("num") Integer num, @RequestBody CommentDTO commentDTO) {
        log.debug("수정 컨트롤러 값 확인 {}", commentDTO);
        commentDTO.setNum(num);
        try {
            cs.upComment(commentDTO);
        } catch (Exception e) {

        }
    }
}
