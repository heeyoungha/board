package board.domain.study;

import board.domain.TimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Entity
public class Study extends TimeEntity {

    @Id @GeneratedValue
    private Long id;

    private String study;

    @Builder
    public Study(String study){
        this.study = study;
    }
}
