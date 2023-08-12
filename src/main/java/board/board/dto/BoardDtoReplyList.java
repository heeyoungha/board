package board.board.dto;

import board.board.domain.Reply;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

public class BoardDtoReplyList {
    private Long id;

    @Schema(description = "글쓴이", required = true, example = "홍길동")
    private String writer;

    @Schema(description = "제목", required = true, example = "첫번째 게시글입니다")
    private String title;

    @Schema(description = "내용", required = true, example = "내용 작성 필수")
    private String content;

    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    private List<Reply> replyList;

    @Builder
    public BoardDtoReplyList(BoardDto boardDto, List<Reply> replyList){
        this.id = boardDto.getId();
        this.writer = boardDto.getWriter();
        this.title = boardDto.getTitle();
        this.content = boardDto.getContent();
        this.createdDate = boardDto.getCreatedDate();
        replyList = replyList;
    }
}
