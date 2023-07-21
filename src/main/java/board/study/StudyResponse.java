package board.study;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StudyResponse {

    private Long id;
    private String studyType;

    @Builder
    public StudyResponse(Study study){
        this.id = study.getId();
        this.studyType = study.getStudyType();
    }

}
