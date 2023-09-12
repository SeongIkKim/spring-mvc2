package hello.exception.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// 이 exception이 핸들러에서 일어나 던져지면 httpStatusCode가 400으로 반환됨
// error reason을 message.properties와도 연결 가능
@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "error.bad")
public class BadRequestException extends RuntimeException{
}
