package board.common.exception;

import board.common.dto.ErrorResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.mail.MessagingException;

@ControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class CommonAdviceController {
    private final JavaMailSender javaMailSender;

//    @ResponseBody
//    @ExceptionHandler(value = Exception.class)
//    public ResponseEntity<ErrorResponse> exception(Exception e){
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                .body(ErrorResponse.of("관리자에게 문의주세요.", e.getMessage()));
//    }

    @ResponseBody
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = TypeException.class)
    public ResponseEntity<ErrorResponse> typeException(TypeException e){
        log.error("⚠️ 스터디 생성오류가 발생했습니다! \n [{}] ", e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErrorResponse.of("존재하지 않는 과목입니다.", e.getMessage()));
    }

    @ResponseBody
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = DomainException.class)
    public ResponseEntity<ErrorResponse> domainExeption(DomainException e) {
        log.error("⚠️ 조회 오류가 발생했습니다! \n [{}] ", e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErrorResponse.of("존재하지 않는 id입니다", e.getMessage()));
    }

    // 404 예외처리 핸들러
    @ResponseBody
    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Not Found")
    @ExceptionHandler(value = NoHandlerFoundException.class)
    public ResponseEntity<ErrorResponse> handle404(NoHandlerFoundException e){

        String errorMessage = e.getMessage();
        log.error("⚠️ 페이지를 찾을 수 없습니다! \n [{}] ", e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.of("페이지를 찾을 수 없습니다.", errorMessage));
    }

    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "No HttpInputMessage available - use non-deprecated constructors")
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handle400(HttpMessageNotReadableException e){

        String errorMessage = e.getMessage();
        log.error("⚠️ 잘못된 요청 파라미터입니다! \n [{}] ", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.of("잘못된 요청 파라미터입니다.", errorMessage));
    }

    @ResponseBody
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = MessagingException.class)
    public ResponseEntity<ErrorResponse> messagingException(MessagingException e){

        String errorMessage = e.getMessage();
        log.error("⚠️ 메일전송에 실패하였습니다! \n [{}] ", e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErrorResponse.of("메일전송에 실패하였습니다.", errorMessage));
    }
}
