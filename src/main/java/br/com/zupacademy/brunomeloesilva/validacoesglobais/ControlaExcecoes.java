package br.com.zupacademy.brunomeloesilva.validacoesglobais;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ControlaExcecoes extends ResponseEntityExceptionHandler {
	
	@Autowired
	private MessageSource messageSource;
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
	
		List<ErroDTOResponse> erroDTOResponseList = retornaErroDosCamposOuErroDaClasse(ex);
		
		return ResponseEntity.badRequest().body(erroDTOResponseList);
	}

	private List<ErroDTOResponse> retornaErroDosCamposOuErroDaClasse(MethodArgumentNotValidException ex) {
		List<ErroDTOResponse> erroDTOResponseList = new ArrayList<>();
		List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
		
		fieldErrors.forEach(field -> {
			String mensagem = messageSource.getMessage(field, LocaleContextHolder.getLocale());
			ErroDTOResponse erro = new ErroDTOResponse(field.getField(), mensagem);
			erroDTOResponseList.add(erro);
		});

		return erroDTOResponseList;
	}
}