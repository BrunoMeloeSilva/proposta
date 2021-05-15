package br.com.zupacademy.brunomeloesilva.proposta.cartoes.avisoviagem;

import java.util.Date;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AvisoViagemDTORequest {
	@NotBlank
	private String destinoViagem;
	@Future @NotNull
	private Date dataTerminoViagem;
	
	public String getDestinoViagem() {
		return destinoViagem;
	}
	public Date getDataTerminoViagem() {
		return dataTerminoViagem;
	}
}