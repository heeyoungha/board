package board.study;

import board.BaseEntity;
import board.exception.TypeException;
import board.project.Project;
import board.study.type.StudyType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "study")
//@Audited
//@AuditOverride(forClass= BaseEntity.class)
@Where(clause = "is_deleted = false")
public class Study extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "study_id")
    private Long id;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "study_type")
    private StudyType studyType;

    private String location;

    private String title;

    private String description;

    private boolean published;

    @OneToMany(mappedBy = "study", cascade = CascadeType.ALL)
    private List<Project> projects = new ArrayList<>();

    @Builder
    public Study(String study, String location,String title, String description){
        //this.study = study;
        try{
            this.studyType = StudyType.valueOf(study);
            this.location = location;
            this.title = title;
            this.description = description;
            this.published = false;
        } catch(Exception e){
            throw TypeException.of("스터디", study);
        }
    }

    public String getStudyType(){
        return studyType.name();
    }

    public double getAverageBookmark(){
        return projects.stream()
                .mapToInt(Project::getBookmark)
                .average()
                .orElseThrow();
    }
}
