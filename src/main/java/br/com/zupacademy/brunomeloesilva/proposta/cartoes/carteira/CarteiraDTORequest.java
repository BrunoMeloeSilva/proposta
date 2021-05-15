package br.com.zupacademy.brunomeloesilva.proposta.cartoes.carteira;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CarteiraDTORequest {
	@NotBlank @Email 
	private String email;
	@NotNull 
	private TipoCarteira carteira;
	
	public String getEmail() {
		return email;
	}
	public TipoCarteira getCarteira() {
		return carteira;
	}	
}
