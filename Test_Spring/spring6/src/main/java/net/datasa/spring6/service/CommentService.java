package net.datasa.spring6.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datasa.spring6.domain.dto.CommentDTO;
import net.datasa.spring6.domain.entity.CommentEntity;
import net.datasa.spring6.repository.CommentRepository;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository cr;

    public List<CommentDTO> getAllList() {
        List<CommentEntity> entityList = cr.findAll();
        List<CommentDTO> dtoList = new ArrayList<>();

        // Entity 에서 DTO 로 데이터 옮겨 담기
        for (CommentEntity entity : entityList) {
            CommentDTO dto = CommentDTO.builder()
                    .num(entity.getNum())
                    .comment(entity.getComment())
                    .name(entity.getName())
                    .build();
            dtoList.add(dto);
        }
        return dtoList;
    }

    // 댓글저장
    public void setComment(CommentDTO commentDTO) {
        CommentEntity entity = new CommentEntity();
        entity.setComment(commentDTO.getComment());
        entity.setName(commentDTO.getName());
        cr.save(entity);
    }

    public void deletComment(Integer num) {
        cr.deleteById(num);
    }

    public void upComment(CommentDTO commentDTO) {
        CommentEntity entity = cr.findById(commentDTO.getNum()).orElseThrow(
                () -> new EntityNotFoundException("회원이 존재하지 않습니다."));
        entity.setComment(commentDTO.getComment());
        cr.save(entity);
    }
}
