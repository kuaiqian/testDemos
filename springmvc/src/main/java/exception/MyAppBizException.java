package exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_GATEWAY,reason="bad502")
public class MyAppBizException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
