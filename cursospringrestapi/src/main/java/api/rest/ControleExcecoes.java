package api.rest;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.postgresql.util.PSQLException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@ControllerAdvice
public class ControleExcecoes extends ResponseEntityExceptionHandler {

	/* Tratamento da maioria dos erros */
	@Override
	@ExceptionHandler({ Exception.class, RuntimeException.class, Throwable.class })
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		String msg = "";

		if (ex instanceof MethodArgumentNotValidException) {
			List<ObjectError> list = ((MethodArgumentNotValidException) ex).getBindingResult().getAllErrors();
			for (ObjectError objectError : list) {
				msg += objectError.getDefaultMessage() + "\n";
			}
		} else {
			msg = ex.getMessage();
		}

		ObjetoError objetoError = new ObjetoError();
		objetoError.setError(msg);
		objetoError.setCode(status.value() + " ==> " + status.getReasonPhrase());

		return new ResponseEntity<>(objetoError, headers, status);
	}

	
	/* Tratamento da maioria dos erros a nível do banco de dados */
	@ExceptionHandler({ DataIntegrityViolationException.class, ConstraintViolationException.class, PSQLException.class,
			SQLException.class })
	protected ResponseEntity<Object> handleExceptionDataIntegry(Exception ex) {

		String msg = "";

		if (ex instanceof DataIntegrityViolationException) {
			System.out.println("Exception 111");
			msg = ((DataIntegrityViolationException) ex).getCause().getCause().getMessage();

		} else if (ex instanceof ConstraintViolationException) {
			System.out.println("Exception 222");
			msg = ((ConstraintViolationException) ex).getCause().getCause().getMessage();

		} else if (ex instanceof PSQLException) {
			System.out.println("Exception 333");
			msg = ((PSQLException) ex).getCause().getCause().getMessage();

		} else if (ex instanceof SQLException) {
			System.out.println("Exception 444");
			msg = ((SQLException) ex).getCause().getCause().getMessage();

		} else {
			System.out.println("Exception 555");
			msg = ex.getMessage();
		}

		System.out.println("Exception 666");
		
		ObjetoError objetoError = new ObjetoError();
		objetoError.setError(msg);
		objetoError.setCode("");

		return new ResponseEntity<>(objetoError, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}