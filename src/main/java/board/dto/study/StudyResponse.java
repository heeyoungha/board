package board.dto.study;

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
}
