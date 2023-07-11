package board.member;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberResponse {

    private Long id;
    private String username;
    private String pw;
    private int age;
    private String interest;
    private String address;

    @Builder
    public MemberResponse(Member member){
        this.address = member.getAddress();
        this.id = member.getId();
        this.username = member.getUsername();
        this.pw = member.getPw();
        this.interest = member.getInterest();
        this.age = member.getAge();
    }

}
