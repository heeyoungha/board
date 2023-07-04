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
    public StudyResponse(String studyType, Long id){
        this.id = id;
        this.studyType = studyType;
    }

    public static StudyResponse toStudyResponse(Study study){
        StudyResponse studyResponse = StudyResponse.builder()
                .studyType(study.getStudyType())
                .id(study.getId())
                .build();

        return studyResponse;
    }
}
