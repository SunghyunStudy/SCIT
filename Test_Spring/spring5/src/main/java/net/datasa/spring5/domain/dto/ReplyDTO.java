package net.datasa.spring5.domain.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.datasa.spring5.domain.entity.ReplyEntity;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReplyDTO {
    private Integer replyNum; // 리플 번호
    private Integer boardNum; // 게시글 번호
    private String memberId; // 작성자 아이디
    private String memberName; // 자성자 이름
    private String contents; // 리플내용
    private LocalDateTime createDate; // 작성시간

    public static ReplyDTO convertToReplyDTO(ReplyEntity entity) {
        return ReplyDTO.builder()
                .replyNum(entity.getReplyNum())
                .boardNum(entity.getBoard().getBoardNum())
                .memberId(entity.getMember().getMemberId())
                .memberName(entity.getMember().getMemberName())
                .contents(entity.getContents())
                .createDate(entity.getCreateDate())
                .build();
    }
}
