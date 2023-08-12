package board.project;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class ProjectRequest {

    @Data
    @NoArgsConstructor
    @Schema(description = "프로젝트 생성 요청")
    @AllArgsConstructor
    public static class CreateProjectRequest{

        @Schema(description = "타이틀", required = true, example = "망원 러닝 7월")
        private String title;

        @Schema(description = "시작일자", required = true, example = "2020-02-02")
        private String startDate;

        @Schema(description = "스터디 ID", required = true, example = "1")
        private Long studyId;

        @Schema(description = "유저네임", required = true, example = "홍길동")
        private String userName;

        @Schema(description = "북마크", example = "20")
        private int bookmark;

    }

    @Data
    @NoArgsConstructor
    @Schema(description = "프로젝트 수정 요청")
    public static class UpdateProjectRequest{
        @Schema(description = "타이틀", required = true, example = "망원 러닝 7월")
        private String title;
        @Schema(description = "시작일자", required = true, example = "2020-02-02")
        private String startDate;
    }


}
