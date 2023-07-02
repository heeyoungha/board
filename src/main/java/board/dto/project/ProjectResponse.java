package board.dto.project;

import board.domain.project.Project;
import lombok.Data;

@Data
public class ProjectResponse {

    private Long id;

    private String title;

    private String startdate;

    private String study;

    private String userName;

    public ProjectResponse(Project project){
        this.id = project.getId();
        this.startdate= project.getStartdate();
        this.title = project.getTitle();
        this.study = project.getStudy();
        this.userName = project.userName();
    }
}
