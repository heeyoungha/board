package board.study;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StudyResponse {

    private Long id;
    private String studyType;

    private String location;

    private String title;

    private String description;


    @Builder
    public StudyResponse(Study study){
        this.id = study.getId();
        this.studyType = study.getStudyType();
        this.location = study.getLocation();
        this.title = study.getTitle();
        this.description = study.getDescription();
    }

}
