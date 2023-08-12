package board.board.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.util.Pair;

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

    public Pair<Long, List<Reply>> get() {
        Pair<Long, List<Reply>> pair = Pair.of(id, replyList);

        Long id = pair.getFirst();
        List<Reply> reply = pair.getSecond();

        return pair;
    }

}
