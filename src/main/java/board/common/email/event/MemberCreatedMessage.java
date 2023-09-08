package board.common.email.event;

import board.member.Member;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class MemberCreatedMessage {

    private Long id;
    private String username;
    private String to;
    private String subject;
    private String message;

    private String token;

    private String state;
    private String email;

    public MemberCreatedMessage(Member member){
        this.id = member.getId();
        this.to = member.getEmail();
        this.subject = "축하합니다. 가입이 승인되었습니다";
        this.message = String.format("%s님의 가입을 축하합니다", member.getUsername());
        this.username = member.getUsername();
        this.token = member.getToken();
        this.state = member.getState();
        this.email = member.getEmail();
    }
}
