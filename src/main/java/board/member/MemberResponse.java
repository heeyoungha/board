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
    public MemberResponse(Long id, String username, String pw, int age, String interest, String address){
        this.address = address;
        this.id = id;
        this.username = username;
        this.pw = pw;
        this.interest = interest;
        this.age = age;
    }

    public static MemberResponse toMemberResponse(Member member){
        MemberResponse m = MemberResponse.builder()
                .age(member.getAge())
                .pw(member.getPw())
                .interest(member.getInterest())
                .id(member.getId())
                .username(member.getUsername())
                .address(member.getAddress())
                .build();

        return m;
    }
}
