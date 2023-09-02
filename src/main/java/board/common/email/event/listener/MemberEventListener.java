package board.common.email.event.listener;


import board.common.email.event.MemberCreatedMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;



@Component
@Slf4j
@RequiredArgsConstructor
public class MemberEventListener {

    private final JavaMailSender javaMailSender;

    @EventListener
    public void info(MemberCreatedMessage event){
        System.out.println(String.format("%s님이 생성되었습니다", event.getUsername()));
    }

}
