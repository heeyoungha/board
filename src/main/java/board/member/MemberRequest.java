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

    private String address;

}
