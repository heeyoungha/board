package board.board.dto;

import board.board.domain.Board;
import board.board.domain.Reply;
import board.member.Member;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Schema(description = "댓글 요청")
public class ReplyDto {

    @Schema(description = "댓글_아이디", example = "1")
    private Long replyId;

    @Schema(description = "게시글_아이디", required = true, example = "1")
    private Long boardId;

    @Schema(description = "댓글", required = true, example = "첫번째 댓글입니다")
    private String content;

    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public Reply toEntity(Board board, Member member, String content){
        Reply build = Reply.builder()
                .content(content)
                .member(member)
                .board(board)
                .build();
        return build;
    }

    @Builder
    public ReplyDto(Reply reply){
        this.replyId = reply.getId();
        this.content = reply.getContent();
        this.createdDate = reply.getCreatedDate();
        this.modifiedDate = reply.getModifiedDate();
    }
}
