package board.common.email.event.listener;


import board.common.email.event.MemberCreatedMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


@Component
@Slf4j
@RequiredArgsConstructor
public class MemberEventListener {

    private final JavaMailSender javaMailSender;

    @EventListener
    public void info(MemberCreatedMessage event){
        System.out.println(String.format("%s님이 생성되었습니다", event.getUsername()));
    }

    @EventListener
    public void sendMail(MemberCreatedMessage event) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
            mimeMessageHelper.setTo(event.getTo()); // 메일 수신자
            mimeMessageHelper.setSubject(event.getSubject()); // 메일 제목
            mimeMessageHelper.setText(event.getMessage(), true); // 메일 본문 내용, HTML 여부
            javaMailSender.send(mimeMessage);

            log.info("Success");


        } catch (MessagingException e) {
            log.info("fail");
            throw new RuntimeException(e);
        }
    }

}
