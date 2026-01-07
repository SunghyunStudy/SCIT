package net.datasa.exam.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datasa.exam.domain.dto.StudentDTO;
import net.datasa.exam.domain.entity.AnswerEntity;
import net.datasa.exam.domain.entity.StudentEntity;
import net.datasa.exam.repository.AnswerRepository;
import net.datasa.exam.repository.StudentRepository;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class ExamService {
    private final StudentRepository sr;
    private final AnswerRepository ar;

    public void write(StudentDTO studentDTO) {
        boolean checkEmail = sr.existsByEmail(studentDTO.getEmail());
        if (checkEmail) {
            throw new RuntimeException("이미 응시한 이메일입니다.");
        }

        int score = 0;

        List<AnswerEntity> answerList = ar.findAll();

        if (answerList.get(0).getCorrectAnswer().equals(studentDTO.getAnswer1())) {
            score += 20;
        }
        if (answerList.get(1).getCorrectAnswer().equals(studentDTO.getAnswer2())) {
            score += 20;
        }
        if (answerList.get(2).getCorrectAnswer().equals(studentDTO.getAnswer3())) {
            score += 20;
        }
        if (answerList.get(3).getCorrectAnswer().equals(studentDTO.getAnswer4())) {
            score += 20;
        }
        if (answerList.get(4).getCorrectAnswer().equals(studentDTO.getAnswer5())) {
            score += 20;
        }

        StudentEntity entity = StudentEntity.builder()
                .name(studentDTO.getName())
                .password(studentDTO.getPassword())
                .email(studentDTO.getEmail())
                .answer1(studentDTO.getAnswer1())
                .answer2(studentDTO.getAnswer2())
                .answer3(studentDTO.getAnswer3())
                .answer4(studentDTO.getAnswer4())
                .answer5(studentDTO.getAnswer5())
                .score(score)
                .build();
        sr.save(entity);
    }

    public List<StudentDTO> selectAll(String sort) {
        Sort s = Sort.by(Sort.Direction.ASC, "seq");

        if (sort.equals("date")) {
            s = Sort.by(Sort.Direction.ASC, "examDate");
        } else if (sort.equals("name")) {
            s = Sort.by(Sort.Direction.ASC, "name");
        } else if (sort.equals("score")) {
            s = Sort.by(Sort.Direction.DESC, "score");
        }

        List<StudentEntity> entityList = sr.findAll(s);
        List<StudentDTO> dtoList = new ArrayList<>();

        for (StudentEntity studentEntity : entityList) {
            StudentDTO dto = StudentDTO.convertToStudentDTO(studentEntity);
            dtoList.add(dto);
        }

        return dtoList;
    }

    public StudentDTO selectOne(Integer seq) {
        StudentEntity entity = sr.findById(seq)
                .orElseThrow(() -> new EntityNotFoundException("학생이 존재하지 않습니다."));
        StudentDTO dto = StudentDTO.convertToStudentDTO(entity);
        return dto;
    }

    public void delete(Integer seq, String password) {
        StudentEntity entity = sr.findById(seq)
                .orElseThrow(() -> new EntityNotFoundException("학생이 존재하지 않습니다"));

        if (!entity.getPassword().equals(password)) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }

        sr.delete(entity);
    }

}
