package board.member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberRequest {

    private String username;

    private String pw;

    private int age;

    private String interest;

    private String address1;

    private String address2;

    private String zipcode;

    public MemberRequest(String username, String pw, int age, String interest,String address ){
        this.username = username;
        this.pw = pw;
        this.age = age;
        this.interest = interest;
        this.address1 = address;
    }

}
