package board.common.email.event.listener;


import board.common.email.event.MemberCreatedMessage;
import board.common.exception.BusinessLogicException;
import board.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


@Component
@Slf4j
@RequiredArgsConstructor
public class MemberEventListener {

    private final JavaMailSender javaMailSender;
    private final MemberRepository memberRepository;

    @EventListener
    public void info(MemberCreatedMessage event){
        System.out.println(String.format("%s님이 생성되었습니다", event.getUsername()));
    }

    @Async
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
            rollbackRegistration(event);
        } catch (MailSendException e) {
            log.info("fail");
            rollbackRegistration(event);
        }
    }

    private void rollbackRegistration(MemberCreatedMessage event) {
        log.error(String.format("%d 님의 메일전송 오류. 롤백합니다.",event.getId()));
        memberRepository.deleteById(event.getId());
        throw BusinessLogicException.sendingEmailException(event.getId());
    }

}
