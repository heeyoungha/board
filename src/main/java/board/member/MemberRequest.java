package board.member;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "회원가입 요청")
public class MemberRequest {

    @Schema(description = "유저네임", required = true, example = "홍길동")
    private String username;

    @Schema(description = "비밀번호", required = true, example = "1234")
    private String pw;

    @Schema(description = "나이", example = "20")
    private int age;

    @Schema(description = "취미", example = "런닝")
    private String interest;

    @Schema(description = "주소")
    private MemberAddressRequest address;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Schema(description = "회원가입 요청 폼_주소")
    public static class MemberAddressRequest {

        @Schema(description = "주소1", example = "서울시 동작구")
        private String address1;

        @Schema(description = "주소2", example = "상세주소")
        private String address2;

        @Schema(description = "코드", example = "405963")
        private String zipcode;


    }
}
