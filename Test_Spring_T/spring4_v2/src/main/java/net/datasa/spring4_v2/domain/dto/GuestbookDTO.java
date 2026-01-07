package net.datasa.spring4_v2.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GuestbookDTO {
    Integer num;                    //글번호. 기본키
    String name;                	//작성자 이름
    String password;            	//비밀번호
    String message;             	//게시글 내용
    LocalDateTime inputdate;        //작성시간
	
	Integer recommendCnt;
}
