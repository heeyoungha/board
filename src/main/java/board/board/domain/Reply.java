package board.board.domain;

import board.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity(name = "reply")
@NoArgsConstructor
public class Reply extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @Column(nullable = false, length = 120)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="boardId")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="userId")
    private User user;

    @Builder
    public Reply(Board board, User user, String content){
        this.content = content;
        this.board = board;
        this.user = user;
    }

    public void updateReply(String content){
        this.content = content;
    }

};
