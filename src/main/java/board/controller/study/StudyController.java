package board.controller.study;

import board.dto.study.StudyRequest;
import board.dto.study.StudyResponse;
import board.service.study.StudyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/study")
@Tag(name = "study V1 API")
public class StudyController {

    private final StudyService studyService;

    @ResponseBody
    @PostMapping
    @Operation(summary = "스터디를 생성합니다")
    public ResponseEntity<StudyResponse> createStudy(@RequestBody StudyRequest request){
        StudyResponse response = studyService.createStudy(request);
        return ResponseEntity.ok(response);
    }
}
