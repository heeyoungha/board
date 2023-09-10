package board.member;


import board.board.domain.Reply;
import board.common.BaseEntity;
import board.member.type.Address;
import board.project.Project;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Delegate;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;
import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.hibernate.envers.RelationTargetAuditMode.NOT_AUDITED;

@Getter
@Entity
@DynamicUpdate //
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

    private String token;

    private String state;

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



    @Builder
    public Member(String username, String email, String pw, int age, String interest, board.member.type.Address address){
        this.username = username;
        this.email = email;
        this.pw = pw;
        this.interest = interest;
        this.address = address;
        this.age = age;
        this.token = "";
        this.state = "NOT_APPROVED";
    }
    //Member보다 다른 객체로 변경 필요.
    //@Builder
    public void updateMember(MemberRequest.UpdateMemberRequest request){
        this.username = request.getUsername();
        this.email = request.getEmail();
        this.pw = request.getPw();
        this.interest = request.getInterest();
        this.address = request.updateAddress(request);
        this.age = request.getAge();
    }

    public double getBookmarkAverageBookmark() {
        return projects.stream()
                .mapToInt(Project::getBookmark)
                .average()
                .orElseThrow();
    }

    public void updateToken(){

        this.token = UUID.randomUUID().toString();
    }

    public void updateStatus(String state){
        this.state = state;
    }

}
