package board.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberResponse {

    private Long id;
    private String username;
    private String pw;
    private int age;
    private String interest;
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

//    @Builder
//    public MemberResponse(Member member){
//        this.address1 = member.getAddress1();
//        this.id = member.getId();
//        this.username = member.getUsername();
//        this.pw = member.getPw();
//        this.interest = member.getInterest();
//        this.age = member.getAge();
//    }
}
