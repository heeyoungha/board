package board.project;

import lombok.*;

@Getter
@Builder(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED) //리플렉션을 위해
public class ProjectInfo {

    private Long id;
    private String title;
    private String startDate;
    private int bookmark;
    private String StudyId;
    private String userName;

    public static ProjectInfo of(Project project) {
        return ProjectInfo.builder()
                .id(project.getId())
                .title(project.getTitle())
                .startDate(project.getStartDate())
                .bookmark(project.getBookmark())
                .StudyId(project.getStudyName())
                .userName(project.userName())
                .build();
    }
}
