package board.common.email.event.listener;


import board.common.config.AppProperties;
import board.common.email.event.MemberCreatedMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
@Slf4j
@RequiredArgsConstructor
public class MemberEventListener {

    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;
    private final AppProperties appProperties;

    @EventListener
    public void info(MemberCreatedMessage event){
        System.out.println(String.format("%s님이 생성되었습니다", event.getUsername()));
    }

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void sendMail(MemberCreatedMessage event) {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try{
            makeEmailForm(event, mimeMessage);
            javaMailSender.send(mimeMessage);

        } catch (MessagingException e){
            log.error("error");
        }

        log.info("Success");

    }

    private void makeEmailForm(MemberCreatedMessage event, MimeMessage mimeMessage) throws MessagingException {
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
        mimeMessageHelper.setTo(event.getTo()); // 메일 수신자
        mimeMessageHelper.setSubject(event.getSubject()); // 메일 제목

        //String getHtml = readHtmlFile();

        String token = event.getToken();
        Context context = new Context();
        context.setVariable("link", "/v1/member/check-email-token?token=" + token +
                "&id=" + event.getId());
        context.setVariable("userName", event.getUsername());
        context.setVariable("linkName", "이메일 인증하기");
        context.setVariable("message", " 챌린저 서비스를 사용하려면 링크를 클릭하세요.");
        context.setVariable("host", appProperties.getHost());
        String message = templateEngine.process("mail/simple-link", context);

        mimeMessageHelper.setText(message, true); // 메일 본문 내용, HTML 여부
    }

    /* html 읽어오기 => css부분 인식이 안됨
    private String readHtmlFile() {
        String htmlFilePath = "templates/mail/confirmJoinMail.html";
        String htmlContent = ""; // HTML 내용을 저장할 변수

        try {
            // 클래스패스 리소스로부터 HTML 파일을 읽어옴
            Resource resource = new ClassPathResource(htmlFilePath);

            // 리소스를 읽어오기 위한 InputStreamReader 사용
            InputStreamReader reader = new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8);

            // HTML 파일 내용을 문자열로 읽어옴
            htmlContent = FileCopyUtils.copyToString(reader);

            // 리소스 사용 후 스트림을 닫음
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return htmlContent;
    }

     */

    /* 인증 실패 시 회원가입 취소 로직 -> 일단 가입은 시키되 status로 분류
    private void rollbackRegistration(MemberCreatedMessage event) {
        log.error(String.format("%d 님의 메일전송 오류. 롤백합니다.",event.getId()));
        memberRepository.deleteById(event.getId());
        throw BusinessLogicException.sendingEmailException(event.getId());
    }

     */

}
