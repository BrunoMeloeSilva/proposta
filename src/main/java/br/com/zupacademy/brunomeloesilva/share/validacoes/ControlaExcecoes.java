package br.com.zupacademy.brunomeloesilva.share.validacoes;

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
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.zupacademy.brunomeloesilva.proposta.validacoes.PropostaJaExisteException;
import feign.RetryableException;

@RestControllerAdvice
public class ControlaExcecoes extends ResponseEntityExceptionHandler {

	@Autowired
	private MessageSource messageSource;

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		List<ErroDTOResponse> erroDTOResponseList = retornaErroDosCampos(ex);
		
		return ResponseEntity.badRequest().body(erroDTOResponseList);
	}
	
	@ExceptionHandler(PropostaJaExisteException.class)
	protected ResponseEntity<Object> handlePropostaJaExisteException(MethodArgumentNotValidException ex) {

		List<ErroDTOResponse> erroDTOResponseList = retornaErroDosCampos(ex);
		return ResponseEntity.unprocessableEntity().body(erroDTOResponseList);
	}

	@ExceptionHandler(RetryableException.class)
	protected ResponseEntity<Object> handleConnectException(RetryableException ex) {
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(new ErroDTOResponse(null, ex.getLocalizedMessage()));
	}
	
	@ExceptionHandler(Exception.class)
	protected ResponseEntity<Object> handleException(Exception ex) {
		return ResponseEntity.badRequest().body(new ErroDTOResponse(null, "Ocorreu um erro inesperado."));
	}

	private List<ErroDTOResponse> retornaErroDosCampos(MethodArgumentNotValidException ex) {
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
//TODO Preciso aprender como extrair valores das propriedades do ValidationMessages em classes
//TODO Como faz para internacionalizar uma mensagem vinda de uma exception ?