package board.board.domain;

import board.common.BaseEntity;
import board.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "reply")
//@Audited
@NoArgsConstructor
public class Reply extends BaseEntity {

    @Id
    @Column(name = "reply_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @Column(nullable = false, length = 120)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="board_id")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="member_id")
    private Member member;

    @Builder
    public Reply(Board board, Member member, String content){
        this.content = content;
        this.board = board;
        this.member = member;
    }

    public void updateReply(String content){
        this.content = content;
    }

};
