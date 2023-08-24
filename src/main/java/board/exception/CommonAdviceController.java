package board.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.stream.Collectors;

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
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> nullException(MethodArgumentNotValidException e){

        String errorMessage = e.getBindingResult().getFieldErrors().stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining());
        log.error("⚠️ null 오류가 발생했습니다! \n [{}] ", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.of("잘못된 요청 파라미터입니다.", errorMessage));
    }
}
