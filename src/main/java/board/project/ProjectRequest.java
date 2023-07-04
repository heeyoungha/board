package board.project;

import board.study.type.StudyType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProjectRequest {

    private String title;

    private String startdate;

    private String study;

    private String userName;

    public StudyType getStudyType(){
        return StudyType.valueOf(study);
    }
}
