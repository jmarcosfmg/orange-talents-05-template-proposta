package br.com.zup.oragetalents.proposta.handler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ControllerErrorsHandler {

	@Autowired
	MessageSource messageSource;

	@ExceptionHandler(value = { MethodArgumentNotValidException.class })
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ResponseEntity<?> methodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request) {
		List<FieldErrorMessage> mensagens = new ArrayList<>();
		ex.getBindingResult().getFieldErrors().forEach(erro -> {
			mensagens.add(new FieldErrorMessage(erro.getField(), messageSource.getMessage(erro, request.getLocale())));
		});
		ex.getBindingResult().getGlobalErrors().forEach(erro -> {
			mensagens.add(new FieldErrorMessage(erro.getObjectName().toString(),
					messageSource.getMessage(erro, request.getLocale())));
		});
		return ResponseEntity.badRequest().body(mensagens);
	}

	@ExceptionHandler(value = { IllegalArgumentException.class })
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ResponseEntity<?> illegalArgumentException(IllegalArgumentException ex, WebRequest request) {
		return ResponseEntity.badRequest()
				.body(new FieldErrorMessage(ex.getCause().toString(), ex.getLocalizedMessage()));
	}

}
