package net.datasa.spring4.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GuestBookDTO {
	Integer num;				// 글번호
	String name;				// 이름
	String password;			// 비번
	String message;				// 게시글 내용
	LocalDateTime inputdate;	// 작성시간
	
	Integer recommendCnt;		// 추천수
}
