package board.project;

import board.study.type.StudyType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class ProjectRequest {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateProjectRequest{
        private String title;

        private String startdate;

        private String study;

        private String userName;

        private int bookmark;
        public StudyType getStudyType(){
            return StudyType.valueOf(study);
        }
    }

    @Data
    @NoArgsConstructor
    public static class UpdateProjectRequest{

        private String title;
        private String startdate;
    }


}
