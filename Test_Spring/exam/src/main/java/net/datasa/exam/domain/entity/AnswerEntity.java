package net.datasa.exam.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "answer")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnswerEntity {

    // 문제 번호 (PK)
    @Id
    @Column(name = "num")
    private Integer num;

    // 정답지
    @Column(name = "correct_answer")
    private String correctAnswer;
}