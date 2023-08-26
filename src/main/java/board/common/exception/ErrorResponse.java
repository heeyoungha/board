package board.common.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
    private boolean result;
    private String title;
    private String message;

    public static ErrorResponse of(String title, String message) {
        return new ErrorResponse(false, title, message);
    }
}
