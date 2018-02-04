package exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@ControllerAdvice
public class ExceptionHandlerAdvice {
	@ExceptionHandler(MyAppBizException.class)
	public String handleException() {
		return "error";
	}
}
