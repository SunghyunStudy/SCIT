package net.datasa.exam.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "student")
@EntityListeners(AuditingEntityListener.class) // 날짜 자동 주입 리스너
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentEntity {

    // 응시 번호 (PK)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seq")
    private Integer seq;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "answer1")
    private String answer1;

    @Column(name = "answer2")
    private String answer2;

    @Column(name = "answer3")
    private String answer3;

    @Column(name = "answer4")
    private String answer4;

    @Column(name = "answer5")
    private String answer5;

    @Column(name = "score")
    private Integer score;

    @CreatedDate
    @Column(name = "exam_date")
    private LocalDateTime examDate;
}