package board.domain.study;

import board.domain.TimeEntity;
import board.domain.study.type.StudyType;
import board.exception.TypeException;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Entity
@Where(clause = "is_deleted = false")
public class Study extends TimeEntity {

    @Id @GeneratedValue
    private Long id;

    private StudyType studyType;

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
}
