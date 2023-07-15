package board.project;

import board.TimeEntity;
import board.member.Member;
import board.member.type.Bookmark;
import board.study.Study;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Getter
@Entity
@Audited
@NoArgsConstructor
@Where(clause = "is_deleted = false")
public class Project extends TimeEntity {

    @Id @GeneratedValue
    private Long id;

    private String title;

    private String startdate;

    @ManyToOne
    @JoinColumn(name = "study_id")
    private Study study;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Convert(converter = Bookmark.BookmarkConverter.class)
    private Bookmark bookmark;

    public static Project of(String title, String startdate, Study study, Member member, int bookmark){
        Project project = new Project();
        project.title = title;
        project.startdate = startdate;
        project.study = study;
        project.member = member;
        project.bookmark = new Bookmark(bookmark);
        return project;
    }

    public String getStudy(){
        return study.getStudyType();
    }
    public String userName(){
        return member.getUsername();
    }

    public Integer getBookmark(){
        return bookmark.getBookmark();
    }

    public void updateProject(String title, String startdate) {
        this.title = title;
        this.startdate = startdate;
    }
}
