package board.member;


import board.board.domain.Reply;
import board.common.BaseEntity;
import board.member.type.Address;
import board.project.Project;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Delegate;
import org.hibernate.annotations.Where;
import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static org.hibernate.envers.RelationTargetAuditMode.NOT_AUDITED;

@Getter
@Entity
@Table(name = "member")
@Audited(targetAuditMode = NOT_AUDITED)
@AuditOverride(forClass=BaseEntity.class)
@NoArgsConstructor
@Where(clause = "is_deleted = false")
public class Member extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String username;

    private String email;

    private String pw;

    @NotAudited
    private int age;

    private String interest;

    @Embedded
    @Delegate
    private Address address;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Project> projects = new ArrayList<>();

    @NotAudited
    @OneToMany(mappedBy = "member")
    private List<Reply> replyList;

    //메소드

    @Builder
    public Member(String username, String email, String pw, int age, String interest, board.member.type.Address address){
        this.username = username;
        this.email = email;
        this.pw = pw;
        this.interest = interest;
        this.address = address;
        this.age = age;
    }

//    public String getAddress1(){
//        return address.
//    }

    public double getBookmarkAverageBookmark() {
        return projects.stream()
                .mapToInt(Project::getBookmark)
                .average()
                .orElseThrow();
    }

}
