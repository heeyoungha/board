package board.common.exception;

public class BusinessLogicException extends RuntimeException {

    private BusinessLogicException(String message){super(message);}

    public static BusinessLogicException sendingEmailException(Long id){
        return new BusinessLogicException((String.format("%s 해당 id의 메일전송에 실패했습니다",id)));
    }
}
