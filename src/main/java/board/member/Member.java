package board.member;


import board.BaseEntity;
import board.project.Project;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Delegate;
import org.apache.tomcat.jni.Address;
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
@Audited(targetAuditMode = NOT_AUDITED)
@AuditOverride(forClass= BaseEntity.class)
@NoArgsConstructor
@Where(clause = "is_deleted = false")
public class Member extends BaseEntity {

    @Id @GeneratedValue
    private Long id;

    private String username;

    private String pw;

    @NotAudited
    private int age;

    private String interest;

    @Embedded
    @Delegate
    private Address address = new Address();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Project> projects = new ArrayList<>();

    @Builder
    public Member(String username, String pw, int age, String interest, Address address){
        this.username = username;
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
