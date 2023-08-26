package board.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class CommonAdviceController {

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

    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Some parameters are invalid")
    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> nullException(IllegalArgumentException e){

        String errorMessage = e.getMessage();
        log.error("⚠️ 잘못된 요청 파라미터입니다! \n [{}] ", e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.of("잘못된 요청 파라미터입니다.", errorMessage));
    }

    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "No HttpInputMessage available - use non-deprecated constructors")
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> nullException(HttpMessageNotReadableException e){

        String errorMessage = e.getMessage();
        log.error("⚠️ 잘못된 요청 파라미터입니다! \n [{}] ", e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.of("잘못된 요청 파라미터입니다.", errorMessage));
    }
}
