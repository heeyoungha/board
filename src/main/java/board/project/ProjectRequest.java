package board.project;

import board.study.type.StudyType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

public class ProjectRequest {

    @Data
    @NoArgsConstructor
    @Schema(description = "프로젝트 생성 요청")
    public static class CreateProjectRequest{

        @Schema(description = "제목", example = "런닝 1회차")
        private String title;

        @Schema(description = "시작날짜", example = "2023-07-02")
        private String startdate;

        @Schema(description = "스터디", example = "sports")
        private String study;

        @Schema(description = "유저네임", example = "홍길동")
        private String userName;

        public StudyType getStudyType(){
            return StudyType.valueOf(study);
        }
    }

    @Data
    @NoArgsConstructor
    @Schema(description = "프로젝트 수정 요청")
    public static class UpdateProjectRequest{

        @Schema(description = "제목", example = "런닝 2회차")
        private String title;

        @Schema(description = "시작날짜", example = "2023-07-02")
        private String startdate;
    }


}
