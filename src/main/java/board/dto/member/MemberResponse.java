package board.dto.member;

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
}
