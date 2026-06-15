package in.neuprakash.SajiloYatra.exception;

import in.neuprakash.SajiloYatra.dto.response.APIResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<APIResponse<String>> handleAllExceptions(Exception e) {
        APIResponse<String> apiResponse = APIResponse.<String>builder().status(false).message("Exception caught").data(e.getMessage()).build();
        return ResponseEntity.badRequest().body(apiResponse);

    }
}
