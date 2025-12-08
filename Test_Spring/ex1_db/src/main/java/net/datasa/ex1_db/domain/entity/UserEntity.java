package net.datasa.ex1_db.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="users")
public class UserEntity {
	@Id
	@Column(name = "id", nullable = false, length = 50)  // 실제 테이블의 컬럼명, 제약조건 NN, Varchar(30)
	private String id;  // 여기 id는 자바에서 쓸 변수명임. 만약에 column 어노테이션을 안쓸거면 테이블 컬럼명이랑 변수명이랑 일치해야됨.
	
	@Column(name = "pw", nullable = false, length = 50)
	private String pw;
	
	@Column(name = "name", nullable = false, length = 50)
	private String name;
	
	@Column(name = "phone", nullable = false, length = 50)
	private String phone;
}
