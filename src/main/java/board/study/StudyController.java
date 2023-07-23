package board.study;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/study")
@Tag(name = "study V1 API", description = "스터디를 관리하는 API")
public class StudyController {

    private final StudyService studyService;

    @ResponseBody
    @PostMapping
    @Operation(summary = "스터디를 생성합니다 ➕ ")
    public ResponseEntity<StudyResponse> createStudy(@RequestBody StudyRequest request){
        StudyResponse response = studyService.createStudy(request);
        return ResponseEntity.ok(response);
    }

    @ResponseBody
    @GetMapping
    @Operation(summary = "스터디 리스트를 모두 조회합니다 🔍 [ ]")
    public ResponseEntity<List<StudyResponse>> readStudyList(){
        List<StudyResponse> responses = studyService.readStudyList();
        return ResponseEntity.ok(responses);
    }

    @ResponseBody
    @GetMapping("/{id}")
    @Operation(summary = "특정 스터디를 조회합니다 🔍 S ")
    public ResponseEntity<StudyResponse> readStudy(@PathVariable Long id){
        StudyResponse response = studyService.readStudy(id);
        return ResponseEntity.ok(response);
    }

    @ResponseBody
    @DeleteMapping("/{id}")
    @Operation(summary = "특정 스터디를 삭제합니다 🗑")
    public ResponseEntity<StudyResponse> deleteStudy(@PathVariable Long id){
        studyService.deleteStudy(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @ResponseBody
    @GetMapping("/{id}/average-bookmark")
    @Operation(summary = "스터디 평균 북마크를 조회합니다 🔍🔰")
    public ResponseEntity<SBookmarkResponse> getAverageBookmark(@PathVariable Long id){
        SBookmarkResponse response = studyService.getAverageBookmark(id);
        return ResponseEntity.ok(response);
    }
}
