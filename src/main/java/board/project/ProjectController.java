package board.project;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/project")
@Tag(name = "project V1 API")
public class ProjectController {

    private final ProjectService projectService;

    @ResponseBody
    @PostMapping
    @Operation(summary = "프로젝트를 생성합니다")
    public ResponseEntity<ProjectResponse> createProject(@RequestBody ProjectRequest request){
        ProjectResponse response = projectService.createProject(request);
        return ResponseEntity.ok(response);
    }
}
