package board.board.domain;

import board.common.TimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import javax.persistence.*;
import java.util.List;

@Getter
@Entity
@Audited
@AuditOverride(forClass = TimeEntity.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board extends TimeEntity {

    @Id @GeneratedValue
    private Long id;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(length = 10, nullable = false)
    private String writer;

    @NotAudited
    @OneToMany(mappedBy = "board")
    private List<Reply> replyList;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;


    @Builder
    public Board(Long id, String title, String content, String writer) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writer = writer;
    }
}
