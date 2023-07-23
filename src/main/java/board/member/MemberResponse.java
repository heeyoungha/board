package board.member;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Schema(description = "회원가입 응답 폼")
public class MemberResponse {

    private Long id;
    private String username;
    private String pw;
    private int age;
    private String interest;
    private String address1;
    private String address2;

    @Builder
    public MemberResponse(Member member){
//        this.address1 = member.getAddress1();
        this.id = member.getId();
        this.username = member.getUsername();
        this.pw = member.getPw();
        this.interest = member.getInterest();
        this.age = member.getAge();
    }

}
