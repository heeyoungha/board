package board.project;

import lombok.Data;

@Data
public class ProjectResponse {

    private Long id;

    private String title;

    private String startdate;

    private Long study_id;

    private String userName;

    private int bookmark;

    public ProjectResponse(Project project){
        this.id = project.getId();
        this.startdate= project.getStartdate();
        this.title = project.getTitle();
        this.study_id = project.getStudyId();
        this.userName = project.userName();
        this.bookmark = project.getBookmark();
    }

}
