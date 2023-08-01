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
    @Column(name = "study_type", unique = true)
    private StudyType studyType;

    @OneToMany(mappedBy = "study")
    private List<Project> projects = new ArrayList<>();

    @Builder
    public Study(String study){
        //this.study = study;
        try{
            this.studyType = StudyType.valueOf(study);
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
