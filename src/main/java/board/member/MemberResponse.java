package board.member;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Schema(description = "회원가입 응답 폼")
public class MemberResponse {

    @Schema(description = "아이디", required = true, example = "1")
    private Long id;
    @Schema(description = "유저네임", required = true, example = "홍길동")
    private String username;
    @Schema(description = "비밀번호", required = true, example = "1234")
    private String pw;
    @Schema(description = "나이", example = "20")
    private int age;
    @Schema(description = "취미", example = "런닝")
    private String interest;
    @Schema(description = "주소1", example = "서울시 동작구")
    private String address1;
    @Schema(description = "주소2", example = "상세주소")
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
