package board.member;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MBookmarkResponse {
    @Schema(description = "유저네임", required = true, example = "홍길동")
    private String userName;
    @Schema(description = "북마크", required = true, example = "200")
    private Double bookmark;
}
