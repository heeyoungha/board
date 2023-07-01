package board.domain.member;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Entity
@NoArgsConstructor
public class Member {

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
