package board.study;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SBookmarkResponse {
    @Schema(description = "스터디", required = true, example = "sports")
    private String studyType;
    @Schema(description = "북마크", required = true, example = "100")
    private Double bookmark;
}
