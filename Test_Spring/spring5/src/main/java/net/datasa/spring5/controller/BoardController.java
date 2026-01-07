package net.datasa.spring5.controller;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datasa.spring5.domain.dto.BoardDTO;
import net.datasa.spring5.domain.dto.MemberDTO;
import net.datasa.spring5.domain.dto.ReplyDTO;
import net.datasa.spring5.domain.entity.ReplyEntity;
import net.datasa.spring5.service.BoardService;
import net.datasa.spring5.service.MemberService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.ui.Model;
import jakarta.persistence.PrePersist;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 게시판 관련 컨트롤러
 */
@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("board")
public class BoardController {
    private final BoardService bs;
    private final MemberService ms;

    // application.properties 파일의 설정값.
    @Value("${board.pageSize}")
    int pageSize;

    @Value("${board.linkSize}")
    int linkSize;

    @Value("${board.uploadPath}")
    String uploadPath;

    /**
     * 글쓰기 페이지로 이동
     * 
     * @return writeForm.html
     */

    @GetMapping("write")
    public String write() {
        return "boardView/writeForm";
    }

    /**
     * 글 저장
     * 
     * @param boardDTO 작성한 글 정보
     * @param user     로그인한 사용자 정보
     * @param upload   첨부파일
     * @return 게시판 글 목록 경로
     */
    @PreAuthorize("isAuthenticated()")
    @PostMapping("write")
    public String write(BoardDTO boardDTO,
            @AuthenticationPrincipal UserDetails user,
            @RequestParam(name = "upload", required = false) MultipartFile upload) {

        // 작성한 글 정보에 사용자 아이디 추가
        boardDTO.setMemberId(user.getUsername());
        log.debug("저장할 글 정보 : {}", boardDTO);

        // 업로드된 첨부파일
        if (upload != null) {
            log.debug("Empty : {}", upload.isEmpty());
            log.debug("파라미터 이름 : {}", upload.getName());
            log.debug("파일명 : {}", upload.getOriginalFilename());
            log.debug("파일크기 : {}", upload.getSize());
            log.debug("파일종류 : {}", upload.getContentType());
        }

        try {
            bs.write(boardDTO, uploadPath, upload);
        } catch (Exception e) {
            log.debug("예외 발생 {}", e.getMessage());
            return "boardView/writeForm";
        }

        return "redirect:/"; // 임시
    }

    @GetMapping("listAll")
    public String listAll(Model model) {
        List<BoardDTO> dtoList = bs.getListAll();

        model.addAttribute("listAll", dtoList);

        return "boardView/listAll";
    }

    @GetMapping("read")
    public String read(@RequestParam("boardNum") Integer boardNum,
            Model model) {
        log.debug("게시글 번호 출력 : {}", boardNum);

        try {
            BoardDTO boardDTO = bs.getBoard(boardNum);
            MemberDTO memberDTO = ms.getMember(boardDTO.getMemberId());

            // 댓글 가져오기
            List<ReplyDTO> replyDTOList = bs.getListReplyAll(boardNum);
            boardDTO.setReplyList(replyDTOList);

            model.addAttribute("board", boardDTO);
            model.addAttribute("member", memberDTO);

            // 댓글 정보 read.html에 보내기
            // model.addAttribute("replyList", replyDTOList);
            return "boardView/read";
        } catch (Exception e) {
            return "redirect:/board/listAll";
        }

    }

    /**
     * 첨부 파일 다운로드
     * 
     * @param boardNum
     * @param response 응답 정보
     */
    @GetMapping("download")
    public void download(@RequestParam("boardNum") int boardNum,
            HttpServletResponse response) {
        // HttpServletResponse 이거 쿠키를 담아서 보낼때도 썼음.
        bs.download(boardNum, response, uploadPath);
    }

    /**
     * 이미지 미리보기
     * 
     * @param baordNum
     * @return ResponseEntity<Resource>
     */
    @GetMapping("preview")
    public ResponseEntity<Resource> preview(@RequestParam("boardNum") int boardNum) throws Exception {
        /*
         * ResponseEntity<T> : HTTP 응답 전체(상태 + 헤더 + 바디)를 자바 객체로 표현
         * ResponseEntity<Resource> : HTTP 응답의 바디로 "읽을 수 있는 데이터"를 보내겠다는 의미
         * MIME(Multipurpose Internet Mail Extensions)
         * - 이 데이터가 어떤 종류의 데이터인지 설명하는 표준 문자열
         * MIME 타입 의미
         * text/html HTML 문서
         * text/plain 일반 텍스트
         * application/json JSON 데이터
         * image/jpeg JPEG 이미지
         * application/octet-stream 알 수 없는 바이너리 데이터
         */

        BoardDTO board = bs.getBoard(boardNum);

        Path path = Paths.get(uploadPath, board.getFileName());
        if (!Files.exists(path)) {
            return ResponseEntity.notFound().build(); // 404
        }

        // MIME 확인
        String contentType = Files.probeContentType(path);
        if (contentType == null) {
            contentType = "application/octet-stream";
        }
        if (!contentType.startsWith("image/")) {
            return ResponseEntity.status(415).build();
        }

        // "어디에 있든 상관없이, 읽을 수 있는 데이터" 를 표현하는 스프링 표준 interface
        Resource resource = new FileSystemResource(path);

        // inline : 가능하면 화면에 바로 표시해라
        // attachment : 첨부파일 형식으로 처리해라
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\""
                        + URLEncoder.encode(board.getOriginalName(), StandardCharsets.UTF_8) + "\"")
                .body(resource);

    }

    @GetMapping("like")
    public String like(@RequestParam("boardNum") int boardNum) {
        try {
            bs.like(boardNum);
        } catch (Exception e) {
            log.debug("[예외 발생] {}", e.getMessage());
        }
        return "redirect:read?boardNum=" + boardNum;
    }

    /**
     * 게시글 삭제
     */
    @PreAuthorize("isAuthenticated()")
    @GetMapping("delete")
    public String delete(
            @RequestParam("boardNum") int boardNum,
            @AuthenticationPrincipal UserDetails user) {
        try {
            bs.delete(boardNum, user.getUsername(), uploadPath);
        } catch (Exception e) {
            log.debug("예외 발생: {}", e.getMessage());
            return "redirect:/board/read?boardNum=" + boardNum;
        }

        return "redirect:/board/listAll";
    }

    /**
     * 게시글 수정
     */
    @PreAuthorize("isAuthenticated()")
    @GetMapping("update")
    public String update(@RequestParam("boardNum") int boardNum,
            @AuthenticationPrincipal UserDetails user,
            Model model) {

        try {
            BoardDTO boardDTO = bs.getBoard(boardNum);
            log.debug("수정페이지 boardDTO 확인 {}", boardDTO);

            if (!user.getUsername().equals(boardDTO.getMemberId())) {
                throw new RuntimeException("수정 권한이 없습니다.");
            }
            model.addAttribute("board", boardDTO);
            return "boardView/updateForm";
        } catch (Exception e) {
            log.debug("[예외발생] {}", e.getMessage());
            return "redirect:/board/list";
        }
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("update")
    public String update(BoardDTO boardDTO,
            @AuthenticationPrincipal UserDetails user,
            @RequestParam(name = "upload", required = false) MultipartFile upload) {

        try {
            bs.update(boardDTO, user.getUsername(), uploadPath, upload);
            return "redirect:read?boardNum=" + boardDTO.getBoardNum();
        } catch (Exception e) {
            log.debug("예외 발생 {}", e.getMessage());
            return "redirect:/board/list";
        }
    }

    /**
     * 게시판 목록을 조회하고 페이징 및 검색 기능을 제공
     * 
     * @param model      html에 저장하기 위한 객체
     * @param page       현재 페이지
     * @param searchType 검색대상
     * @param searchWord 검색어
     * @return
     */
    @PreAuthorize("isAuthenticated()")
    @GetMapping("list")
    public String list(Model model,
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "searchType", defaultValue = "") String searchType,
            @RequestParam(name = "searchWord", defaultValue = "") String searchWord) {

        log.debug("설정 값 : pageSize={}, linkSieze={}", pageSize, linkSize);
        log.debug("요청 파라미터 : page={}, searchType={}, searchWord={}", page, searchType, searchWord);

        /**
         * Page<T>
         * 조회된 데이터 목록 + 페이징 메타정보를 담고 있는 객체
         * 페이지를 담고 있는 리스트라고 생각하면 됨.
         * List<T> 처럼 데이터를 담고 있으면서도, 페이지 관련 정보를 함께 제공함
         * 
         * 메서드 설명
         * getContent() -------------실제 데이터 리스트 반환(List<T>)
         * getTotalElementS() -------전체 항목 수
         * getTotalPages() ----------전체 페이지 수
         * getNumber() --------------현재 페이지 번호 (0부터 시작)
         * hasNext(), isFirst() 등 --페이징 네이게이션에 활용 가능
         */
        Page<BoardDTO> boardPage = bs.getList(page, pageSize, searchType, searchWord);

        model.addAttribute("boardPage", boardPage);
        model.addAttribute("page", page);
        model.addAttribute("searchType", searchType);
        model.addAttribute("searchWord", searchWord);
        model.addAttribute("linkSize", linkSize);

        log.debug("전체 개수: {}", boardPage.getTotalElements());
        log.debug("전체 페이지 수: {}", boardPage.getTotalPages());
        log.debug("현재 페이지: {}", boardPage.getNumber());
        log.debug("페이지당 글 수: {}", boardPage.getSize());
        log.debug("이전 페이지 존재: {}", boardPage.hasPrevious());
        log.debug("다음 페이지 존재: {}", boardPage.hasNext());

        return "boardView/List";

    }

    @PostMapping("replyWrite")
    public String replyWrite(ReplyDTO replyDTO,
            @AuthenticationPrincipal UserDetails user) { // 나는 id도 히든태그를 만들어서 날려가지구 UserDetails 필요없음 ㅋㅋ
        log.debug("데이터 확인 : {}", replyDTO);

        bs.replyWrite(replyDTO);

        return "redirect:/board/read?boardNum=" + replyDTO.getBoardNum();
    }

    @GetMapping("replyDelete")
    public String replyDelete(ReplyDTO replyDTO,
            @AuthenticationPrincipal UserDetails user) {
        try {
            bs.deleteReply(replyDTO.getReplyNum(), user.getUsername());
        } catch (Exception e) {
            log.debug(e.getMessage());
        }
        return "redirect:/board/read?boardNum=" + replyDTO.getBoardNum();
    }

    /**
     * 댓글 수정
     * 
     * @param boardNum
     * @param replyNum
     * @param updatedContents
     * @param user
     * @return
     */
    @PostMapping("replyUpdate")
    public String replyUpdate(@RequestParam("boardNum") Integer boardNum,
            @RequestParam("replyNum") Integer replyNum,
            @RequestParam("updatedContents") String updatedContents,
            @AuthenticationPrincipal UserDetails user) {
        log.debug("수정 메시지 출력 {}", updatedContents);

        try {
            bs.replyUpdate(replyNum, updatedContents, user);

        } catch (Exception e) {
            log.debug("[예외 발생] {}", e.getMessage());
        }

        return "redirect:/board/read?boardNum=" + boardNum;
    }

    /**
     * 해당 아이디의 리플 목록
     * 
     * @param memberId
     * @param boardNum
     * @param model
     * @return
     */
    @GetMapping("replyList")
    public String replyList(@RequestParam("memberId") String memberId,
            @RequestParam("boardNum") Integer boardNum,
            Model model) {
        log.debug("리플목록 컨트롤러 아이디 출력:{}", memberId);
        try {
            List<ReplyDTO> listDTO = bs.getListReply(memberId);
            model.addAttribute("replyList", listDTO);
            model.addAttribute("memberId", memberId);
            model.addAttribute("countList", listDTO.size());
            model.addAttribute("boardNum", boardNum);
            return "/boardView/replyList";
        } catch (Exception e) {
            log.debug(e.getMessage());
            return "redirect:/board/read?boardNum=" + boardNum;
        }
    }

    @PostMapping("replyList")
    public String replyList(@RequestParam("searchId") String memberId,
            @RequestParam("boardNum") Integer boardNum) {
        log.debug("리플 목록 찾기 {},", memberId);

        return "redirect:/board/replyList?memberId=" + memberId + "&boardNum=" + boardNum;
    }

}
