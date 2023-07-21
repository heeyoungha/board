package board.member;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "회원가입 요청 폼")
public class MemberRequest {

    @Schema(description = "유저네임", required = true)
    private String username;

    @Schema(description = "비밀번호", required = true)
    private String pw;

    @Schema(description = "나이")
    private int age;

    @Schema(description = "취미")
    private String interest;

    @Schema(description = "주소1", example = "서울시 동작구")
    private String address1;

    @Schema(description = "주소2", example = "상세주소")
    private String address2;

    @Schema(description = "코드")
    private String zipcode;

    public MemberRequest(String username, String pw, int age, String interest,String address ){
        this.username = username;
        this.pw = pw;
        this.age = age;
        this.interest = interest;
        this.address1 = address;
    }

}
