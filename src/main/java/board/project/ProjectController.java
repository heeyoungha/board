package board.project;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/project")
@Tag(name = "project V1 API")
public class ProjectController {

    private final ProjectService projectService;

    @ResponseBody
    @PostMapping
    @Operation(summary = "프로젝트를 생성합니다")
    public ResponseEntity<ProjectResponse> createProject(@RequestBody ProjectRequest.CreateProjectRequest request){
        ProjectResponse response = projectService.createProject(request);
        return ResponseEntity.ok(response);
    }

    @ResponseBody
    @GetMapping("/{id}")
    @Operation(summary = "프로젝트를 조회합니다")
    public ResponseEntity<ProjectResponse> readProject(@PathVariable Long id){
        ProjectResponse response = projectService.readProject(id);
        return ResponseEntity.ok(response);
    }
    @ResponseBody
    @GetMapping
    @Operation(summary = "프로젝트 리스트를 조회합니다")
    public ResponseEntity<List<ProjectResponse>> readProjectList(){
        List<ProjectResponse> response = projectService.readProjectList();
        return ResponseEntity.ok(response);
    }
    @ResponseBody
    @DeleteMapping("/{id}")
    @Operation(summary = "프로젝트를 삭제합니다")
    public ResponseEntity<ProjectResponse> deleteProject(@PathVariable Long id){
        projectService.deleteProject(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @ResponseBody
    @PatchMapping("/{id}")
    @Operation(summary = "프로젝트를 수정합니다")
    public ResponseEntity<Void> updateProject(
            @PathVariable Long id,
            @RequestBody ProjectRequest.UpdateProjectRequest request){
        projectService.updateService(id, request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
