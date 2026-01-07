package net.datasa.spring6.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "ajax_member")
public class MemberEntity {
    @Id
    @Column(name = "id", length = 30)
    private String id;

    @Column(name = "name", nullable = false, length = 30)
    private String name;
}
