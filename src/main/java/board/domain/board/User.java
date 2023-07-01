package board.domain.board;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class User {

    @Id @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "user")
    private List<Reply> replyList;
}
