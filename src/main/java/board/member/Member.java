package board.member;


import board.TimeEntity;
import board.project.Project;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
@Where(clause = "is_deleted = false")
public class Member extends TimeEntity {

    @Id @GeneratedValue
    private Long id;

    private String username;

    private String pw;

    private int age;

    private String interest;

    private String address;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Project> projects = new ArrayList<>();

    @Builder
    public Member(String username, String pw, int age, String interest, String address){
        this.username = username;
        this.pw = pw;
        this.interest = interest;
        this.address = address;
        this.age = age;
    }

}
