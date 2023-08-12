package board.study;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StudyResponse {
    @Schema(description = "아이디", required = true, example = "1")
    private Long id;
    @Schema(description = "스터디", required = true, example = "sports")
    private String studyType;

    @Builder
    public StudyResponse(Study study){
        this.id = study.getId();
        this.studyType = study.getStudyType();
    }

}
