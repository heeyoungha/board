package board.study;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StudyRequest {

    @Schema(description = "스터디", required = true, example = "sports")
    private String study;
}
