package board.board.dto;

import board.board.domain.Board;
import board.board.domain.Reply;
import board.board.domain.User;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ReplyDto {

    private Long id;

    private String content;

    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public Reply toEntity(Board board, User user, String content){
        Reply build = Reply.builder()
                .content(content)
                .user(user)
                .board(board)
                .build();
        return build;
    }

    @Builder
    public ReplyDto(Reply reply){
        this.id = reply.getId();
        this.content = reply.getContent();
        this.createdDate = reply.getCreatedDate();
        this.modifiedDate = reply.getModifiedDate();
    }
}
