package net.datasa.ex1_db.domain.dto;

import lombok.Data;

@Data
public class UserDTO {
	private String id;
	private String pw;
	private String name;
	private String phone;
}
