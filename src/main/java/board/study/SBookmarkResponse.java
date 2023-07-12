package board.study;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SBookmarkResponse {
    private String studyType;
    private Double bookmark;
}
