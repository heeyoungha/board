package board.study;

import lombok.*;

@Getter
@Builder(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED) //리플렉션을
public class StudyInfo {
    private Long id;
    private String studyType;
    private String location;
    private String title;
    private String description;
    private boolean published;

    public static StudyInfo of(Study study){
        return StudyInfo.builder()
                .id(study.getId())
                .studyType(study.getStudyType())
                .location(study.getLocation())
                .title(study.getTitle())
                .description(study.getDescription())
                .published(study.isPublished())
                .build();

    }
}
