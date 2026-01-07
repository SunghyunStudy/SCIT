package net.practice4.domain.entity;

import java.time.DateTimeException;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "guestbook")
@EntityListeners(AuditingEntityListener.class)
public class GuestBookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "num")
    private Integer num;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @Column(name = "message", nullable = false, columnDefinition = "text")
    private String message;

    @CreatedDate
    @Column(name = "inputdate", nullable = false)
    private LocalDateTime inputdate;

    @Builder.Default
    @Column(name = "recommend_cnt", nullable = false)
    private Integer recommendCnt = 0;

}
