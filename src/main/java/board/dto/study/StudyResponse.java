package board.dto.study;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StudyResponse {

    private Long id;
    private String study;

    @Builder
    public StudyResponse(String study, Long id){
        this.id = id;
        this.study = study;
    }
}
