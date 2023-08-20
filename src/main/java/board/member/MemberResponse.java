package board.member;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@Schema(description = "회원가입 응답 폼")
@AllArgsConstructor
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
    @Schema(description = "주소")
    private MemberAddressResponse address;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MemberAddressResponse{
        private String address1;
        private String address2;
        private String zipcode;
    }

}
