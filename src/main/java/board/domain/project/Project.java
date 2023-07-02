package board.domain.project;

import board.domain.member.Member;
import board.domain.study.Study;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Project {

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

    public static Project of(String title, String startdate, Study study, Member member){
        Project project = new Project();
        project.title = title;
        project.startdate = startdate;
        project.study = study;
        project.member = member;
        return project;
    }

    public String getStudy(){
        return study.getStudyType();
    }
    public String userName(){
        return member.getUsername();
    }
}
