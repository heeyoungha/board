package board.board.dto;

import board.board.domain.Board;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Schema(description = "게시글 요청")
public class BoardDto {
    private Long id;

    @Schema(description = "글쓴이", required = true, example = "홍길동")
    private String writer;

    @Schema(description = "제목", required = true, example = "첫번째 게시글입니다")
    private String title;

    @Schema(description = "내용", required = true, example = "내용 작성 필수")
    private String content;

    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

//    public Board toEntity(){
//        Board build = Board.builder()
//                .id(id)
//                .writer(writer)
//                .title(title)
//                .content(content)
//                .build();
//        return build;
//    }

    @Builder
    public BoardDto(Board board){
        this.id = board.getId();
        this.writer = board.getWriter();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.createdDate = board.getCreatedDate();
    }
}
