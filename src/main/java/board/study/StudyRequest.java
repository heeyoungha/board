package board.study;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "스터 요청 폼")
public class StudyRequest {

    @Schema(description = "스터디", required = true, example = "sports")
    private String study;
    @Schema(description = "위치", required = true, example = "망원")
    private String location;
    @Schema(description = "제목", required = true, example = "망원 런닝 모집")
    private String title;
    @Schema(description = "설명", example = "주 3회 런닝 인증 스터디입니다")
    private String description;

}
