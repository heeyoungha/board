package board.study;

import board.BaseEntity;
import board.exception.TypeException;
import board.project.Project;
import board.study.type.StudyType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;
import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Audited
@AuditOverride(forClass= BaseEntity.class)
@Where(clause = "is_deleted = false")
public class Study extends BaseEntity {

    @Id @GeneratedValue
    private Long id;

    private StudyType studyType;

    @OneToMany(mappedBy = "study", cascade = CascadeType.ALL)
    private List<Project> projects = new ArrayList<>();

    @Builder
    public Study(String study){
        //this.study = study;
        try{
            this.studyType = StudyType.valueOf(study);
        } catch(IllegalArgumentException e){
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
