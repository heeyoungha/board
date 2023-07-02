package board.domain.member;


import board.domain.TimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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

    @Builder
    public Member(String username, String pw, int age, String interest, String address){
        this.username = username;
        this.pw = pw;
        this.interest = interest;
        this.address = address;
        this.age = age;
    }

}
