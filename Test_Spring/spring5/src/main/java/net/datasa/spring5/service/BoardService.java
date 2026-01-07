package net.datasa.spring5.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datasa.spring5.controller.AdminController;
import net.datasa.spring5.domain.dto.BoardDTO;
import net.datasa.spring5.domain.dto.ReplyDTO;
import net.datasa.spring5.domain.entity.BoardEntity;
import net.datasa.spring5.domain.entity.MemberEntity;
import net.datasa.spring5.domain.entity.ReplyEntity;
import net.datasa.spring5.repository.BoardRepository;
import net.datasa.spring5.repository.MemberRepository;
import net.datasa.spring5.repository.ReplyRepository;
import net.datasa.spring5.util.FileManager;

/**
 * 게시판 관련 서비스
 */
@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class BoardService {

    private final AdminController adminController;

    private final MemberRepository mr;
    private final BoardRepository br;
    private final ReplyRepository rr;
    private final FileManager fm;

    // BoardService(AdminController adminController) {
    // this.adminController = adminController;
    // }

    /**
     * 게시글 저장
     * 
     * @param boardDTO   저장할 글 정보
     * @param uploadPath 파일 저장할 경로
     * @param upload     업로드된 파일
     */
    @Transactional(rollbackOn = IOException.class)
    public void write(BoardDTO boardDTO, String uploadPath, MultipartFile upload) throws IOException {
        // originalName, fileName <- 얘도 같이 저장해야 함.
        // fileName = fm.saveFile(경로, 첨부파일)

        MemberEntity memberEntity = mr.findById(boardDTO.getMemberId())
                .orElseThrow(
                        () -> new EntityNotFoundException("회원아이디가 없습니다."));

        BoardEntity entity = BoardEntity.builder()
                .member(memberEntity)
                .title(boardDTO.getTitle())
                .contents(boardDTO.getContents())
                .build();

        // 첨부파일이 있는 경우
        if (upload != null && !upload.isEmpty()) {
            String fileName = fm.saveFile(uploadPath, upload);
            entity.setFileName(fileName);
            entity.setOriginalName(upload.getOriginalFilename());
        }

        br.save(entity);
    }

    public List<BoardDTO> getListAll() {
        // 1.
        // Sort sort = Sort.by(Sort.Direction.DESC, "boardNum");
        // List<BoardEntity> entityList = br.findAll(sort);

        // 2.
        List<BoardEntity> entityList = br.findAllWithMemberOrderByBoardNumDesc();

        List<BoardDTO> dtoList = new ArrayList<>();

        for (BoardEntity entity : entityList) {
            BoardDTO dto = new BoardDTO();
            dto.setBoardNum(entity.getBoardNum());
            dto.setTitle(entity.getTitle());
            dto.setMemberId(entity.getMember().getMemberId());
            dto.setViewCount(entity.getViewCount());
            dto.setCreateDate(entity.getCreateDate());
            // BoardDTO dto = BoardDTO.convertToBoardDTO(entity); 일일히 set하기 귀찮으면 이거 써도 됨
            dtoList.add(dto);
        }

        return dtoList;
    }

    /**
     * 게시글 1개 조회
     * 
     * @param boardNum
     * @return
     */
    public BoardDTO getBoard(Integer boardNum) {

        BoardEntity entity = br.findById(boardNum).orElseThrow(
                () -> new EntityNotFoundException("게시글 정보가 없습니다."));

        entity.setViewCount(entity.getViewCount() + 1);

        BoardDTO boardDTO = BoardDTO.convertToBoardDTO(entity);

        return boardDTO;
    }

    public void download(int boardNum, HttpServletResponse response, String uploadPath) {
        BoardEntity board = br.findById(boardNum)
                .orElseThrow(
                        () -> new EntityNotFoundException("게시글이 없습니다."));

        if (board.getFileName() == null || board.getOriginalName() == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "첨부파일이 없습니다."); // 404
        }

        // Path : 파일 / 폴더 경로를 표현하는 객체
        // .resolve() : 경로 + 파일 이름
        // .normalize() : 경로에 ./ ../ 같은 것들을 정리해줌
        Path path = Paths.get(uploadPath).resolve(board.getFileName()).normalize();

        if (!Files.exists(path)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "파일이 서버에 없습니다.");
        }

        // Content-Type : 서버가 브라우저에게 전달하는 응답의 종류
        // HTML : text/html, JSON : application/json, PNG : image/png
        // application/octet-stream : 특정 타입이 아닌 바이너리 데이터
        // Content-Disposition : 데이터를 어떻게 처리할지 힌트를 주는 헤더 정보
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition",
                "attachment; filename=\"" + URLEncoder.encode(board.getOriginalName(), StandardCharsets.UTF_8) + "\"");

        // 스트림 전송
        try (
                InputStream in = Files.newInputStream(path);
                ServletOutputStream out = response.getOutputStream()) {
            FileCopyUtils.copy(in, out);
            out.flush();

        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "다운로드 중 오류", e);
        }
    }

    /**
     * 추천수 증가(중복 허용)
     * 
     * @param boardNum
     */
    public void like(int boardNum) {
        BoardEntity entity = br.findById(boardNum).orElseThrow(() -> new EntityNotFoundException("게시글이 없습니다."));

        entity.setLikeCount(entity.getLikeCount() + 1);
    }

    /**
     * 게시글 삭제 + 첨부파일 삭제
     */
    public void delete(int boardNum, String username, String uploadPath) {
        BoardEntity boardEntity = br.findById(boardNum)
                .orElseThrow(() -> new EntityNotFoundException("게시글이 없습니다."));

        if (!boardEntity.getMember().getMemberId().equals(username)) {
            throw new RuntimeException("삭제 권한이 없습니다.");
        }

        // DB 데이터 삭제
        br.delete(boardEntity);

        // 파일 삭제(파일 삭제 실패해도 DB삭제는 됨)
        if (boardEntity.getFileName() != null && !boardEntity.getFileName().isEmpty()) {
            try {
                boolean result = fm.deleteFile(uploadPath, boardEntity.getFileName());
                if (!result) {
                    log.debug(" > 삭제 대상 파일이 이미 없음.");
                }
            } catch (Exception e) {
                log.debug("파일 삭제 실패: {}", e.getMessage());
            }
        }
    }

    /**
     * 게시글 수정 처리
     * 
     * @param boardDTO
     * @param userName
     * @param uploadPath
     * @param upload
     */
    public void update(BoardDTO boardDTO, String username, String uploadPath, MultipartFile upload) {

        BoardEntity boardEntity = br.findById(boardDTO.getBoardNum())
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 게시글임"));

        if (!boardEntity.getMember().getMemberId().equals(username)) {
            throw new RuntimeException("수정권한이 없습니다.");
        }

        boardEntity.setContents(boardDTO.getContents());
        boardEntity.setTitle(boardDTO.getTitle());

        if (upload != null && !upload.isEmpty()) {
            if (boardEntity.getFileName() != null) {
                try {
                    fm.deleteFile(uploadPath, boardEntity.getFileName());
                    String fileName = fm.saveFile(uploadPath, upload);
                    boardEntity.setOriginalName(upload.getOriginalFilename());
                    boardEntity.setFileName(fileName);
                } catch (Exception e) {
                    log.debug("예외 발생 {}", e.getMessage());
                }
            }
        }

        br.save(boardEntity);
    }

    /**
     * 검색 후 지정한 한 페이지 분량의 글 목록 조회
     * 
     * @param page       현재 페이지
     * @param pageSize   한 페이지당 글 수
     * @param searchType 검색 대상
     * @param searchWord 검색어
     * @return 한 페이지 글 목록
     */
    public Page<BoardDTO> getList(int page, int pageSize, String searchType, String searchWord) {
        // Page 객체는 항상 번호가 0부터 시작
        page--;

        // Pageable : 어떤 페이지를, 몇 개 씩, 어떤 정렬로 가져올 것인지를 정의하는 인터페이스
        // ------------Page 객체에 담길 데이터를 조회하기 위해 필요한 페이징 조건
        // 페이지 조회하기위한 조건을 담은 객체
        // 페이지 조회 조건(현재 페이지, 페이지당 글 수, 정렬 순서, 정렬 기준 컬럼)
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.DESC, "boardNum");

        Page<BoardEntity> entityPage = switch (searchType) {
            case "title" -> br.findByTitleContaining(searchWord, pageable);
            case "contents" -> br.findByContentsContaining(searchWord, pageable);
            case "id" -> br.findByMember_MemberId(searchWord, pageable);
            case "all" -> br.searchAll(searchWord, pageable);
            default -> br.findAll(pageable);
        };

        log.debug("조회된 결과 엔티티 페이지 : {}", entityPage.getContent());

        Page<BoardDTO> boardDTOPage = entityPage.map(BoardDTO::convertToBoardDTO);
        return boardDTOPage;
    }

    public void replyWrite(ReplyDTO replyDTO) {
        MemberEntity member = mr.findById(replyDTO.getMemberId())
                .orElseThrow(() -> new EntityNotFoundException("해당 회원이 없습니다."));

        BoardEntity board = br.findById(replyDTO.getBoardNum())
                .orElseThrow(() -> new EntityNotFoundException("해당 게시글이 없습니다."));

        ReplyEntity replyEntity = ReplyEntity.builder()
                .member(member)
                .board(board)
                .contents(replyDTO.getContents())
                .build();

        rr.save(replyEntity);
    }

    // 특정 게시글의 댓글 전부 list로 가져오기
    public List<ReplyDTO> getListReplyAll(Integer boardNum) {
        // 1. 익명객체를 통한 자바측 데이터 정렬된 리플 목록
        // List<ReplyEntity> replyEntityList = rr.findAllByBoard_BoardNum(boardNum);

        // Collections.sort(replyEntityList, new Comparator<ReplyEntity>() {
        // @Override
        // public int compare(ReplyEntity o1, ReplyEntity o2) {
        // return o2.getCreateDate().compareTo(o1.getCreateDate());
        // }
        // });
        // List<ReplyDTO> listReplyDTO = new ArrayList<>();
        // for (ReplyEntity replyEntity : replyEntityList) {
        // ReplyDTO dto = ReplyDTO.convertToReplyDTO(replyEntity);
        // listReplyDTO.add(dto);
        // }

        // 2. stream 이용
        // BoardEntity entity = br.findById(boardNum).orElseThrow(
        // () -> new EntityNotFoundException("게시글 정보가 없습니다."));
        // List<ReplyDTO> listReplyDTO = entity.getReplyList().stream()
        // .sorted(
        // (a, b) -> b.getCreateDate().compareTo(a.getCreateDate()))
        // .map(ReplyDTO::convertToReplyDTO).toList();

        // 3. fetch join
        List<ReplyEntity> replyEntityList = rr.findByBoardNumWithMemberOrderByCreateDateDesc(boardNum);

        List<ReplyDTO> listReplyDTO = new ArrayList<>();
        for (ReplyEntity replyEntity : replyEntityList) {
            ReplyDTO dto = ReplyDTO.convertToReplyDTO(replyEntity);
            listReplyDTO.add(dto);
        }

        return listReplyDTO;
    }

    public void deleteReply(Integer replyNum, String user) {
        ReplyEntity replyEntity = rr.findById(
                replyNum)
                .orElseThrow(() -> new EntityNotFoundException("게시글이 없습니다."));

        if (!replyEntity.getMember().getMemberId().equals(user)) {
            throw new RuntimeException("삭제 권한이 없습니다.");
        }

        // DB 데이터 삭제
        rr.delete(replyEntity);

    }

    public void replyUpdate(Integer replyNum, String updatedContents, UserDetails user) {
        ReplyEntity replyEntity = rr.findById(replyNum)
                .orElseThrow(() -> new EntityNotFoundException("게시글이 없습니다."));

        if (!replyEntity.getMember().getMemberId().equals(user.getUsername())) {
            throw new RuntimeException("수정 권한이 없습니다.");
        }

        replyEntity.setContents(updatedContents);

        rr.save(replyEntity);
    }

    /**
     * 리플 목록 조회
     * 
     * @param memberId
     * @return
     */
    public List<ReplyDTO> getListReply(String memberId) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createDate");

        List<ReplyEntity> listEntity = rr.findAllByMember_MemberId(memberId, sort);

        List<ReplyDTO> listDTO = new ArrayList<>();
        for (ReplyEntity entity : listEntity) {
            ReplyDTO dto = ReplyDTO.convertToReplyDTO(entity);
            listDTO.add(dto);
        }

        return listDTO;
    }
}
