package net.datasa.spring3.domain.dto;

import lombok.Data;
/*
	Entity 클래스와 DTO 클래스를 분리해서 사용
	Entity			(Service <-> Repository) DB 매핑 객체
	DTO				Contorller <-> Service 데이터 전달용 객체
 */
@Data
public class PersonDTO {
	private String id;
	private String name;
	private Integer age;
}
