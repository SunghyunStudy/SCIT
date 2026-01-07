package net.datasa.exam.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.datasa.exam.domain.entity.StudentEntity;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {

    private Integer seq;
    private String name;
    private String email;
    private String password;

    private String answer1;
    private String answer2;
    private String answer3;
    private String answer4;
    private String answer5;

    private Integer score;
    private LocalDateTime examDate;

    public static StudentDTO convertToStudentDTO(StudentEntity entity) {
        return StudentDTO.builder()
                .seq(entity.getSeq())
                .name(entity.getName())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .answer1(entity.getAnswer1())
                .answer2(entity.getAnswer2())
                .answer3(entity.getAnswer3())
                .answer4(entity.getAnswer4())
                .answer5(entity.getAnswer5())
                .score(entity.getScore())
                .examDate(entity.getExamDate())
                .build();
    }
}