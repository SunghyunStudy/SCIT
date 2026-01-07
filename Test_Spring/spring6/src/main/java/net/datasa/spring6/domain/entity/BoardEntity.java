package net.datasa.spring6.domain.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ajax_board")
public class BoardEntity {
    @Id
    @Column(name = "num")
    private Integer num;

    // 조회수
    @Column(name = "cnt", nullable = false, columnDefinition = "integer default 0")
    private Integer cnt;

}
