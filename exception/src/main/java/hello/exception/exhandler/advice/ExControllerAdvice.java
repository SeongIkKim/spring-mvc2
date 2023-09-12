package hello.exception.exhandler.advice;

import hello.exception.api.ApiExceptionV2Controller;
import hello.exception.exception.UserException;
import hello.exception.exhandler.ErrorResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
// 여러 컨트롤러에서 발생한 예외를 처리해줌. 대상 컨트롤러 지정하지 않으면 글로벌 처리.
//@ControllerAdvice(annotations = RestController.class) // 모든 @RestController
//@ControllerAdvice("hello.exception.api") // 패키지 내 컨트롤러 명시
//@ControllerAdvice(assignableTypes = {ControllerInterface.class, AbstractController.class}) // 특정 클래스의 하위 클래스 모두 명시
@RestControllerAdvice("hello.exception.api")
public class ExControllerAdvice {

    // 이 컨트롤러 내부에서 illegalArugmentException으로 터지는 handler가 있을 경우 이 핸들러가 호출되어 예외 응답 처리
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorResult illegalExHandler(IllegalArgumentException e) {
        log.error("[exceptionHandler] ex", e);
        return new ErrorResult("BAD", e.getMessage());
    }

    @ExceptionHandler // parameter가 있다면  클래스 생략 가능
    public ResponseEntity<ErrorResult> userHandler(UserException e) {
        log.error("[exceptionHandler] ex", e);
        ErrorResult errorResult = new ErrorResult("USER-EX", e.getMessage());
        return new ResponseEntity(errorResult, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public ErrorResult exHandler(Exception e) {
        log.error("[exceptionHandler] ex", e);
        return new ErrorResult("EX", "내부 오류");
    }
}
