package br.com.zupacademy.brunomeloesilva.proposta;

import java.math.BigDecimal;

import br.com.zupacademy.brunomeloesilva.proposta.cartoes.CartaoDTOResponse;
import br.com.zupacademy.brunomeloesilva.share.seguranca.Criptografia;

public class PropostaDTOResponseCompleta {
	
	private String idProposta;
	private String cpfOuCnpj;
	private String email;
	private String nome;
	private String endereco;
	private BigDecimal salario;
	private PropostaStatus propostaStatus;
	private CartaoDTOResponse cartao;

	public PropostaDTOResponseCompleta(PropostaModel propostaModel, Criptografia criptografia) {
		this.idProposta = propostaModel.getUUID();
		this.cpfOuCnpj = criptografia.descriptografar(propostaModel.getCpfOuCnpj());
		this.email = propostaModel.getEmail();
		this.nome = propostaModel.getNome();
		this.endereco = propostaModel.getEndereco();
		this.salario = propostaModel.getSalario();
		this.propostaStatus = propostaModel.getPropostaStatus();
		this.cartao = new CartaoDTOResponse(propostaModel.getCartao());
	}

	public String getIdProposta() {
		return idProposta;
	}

	public String getCpfOuCnpj() {
		return cpfOuCnpj;
	}

	public String getEmail() {
		return email;
	}

	public String getNome() {
		return nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public PropostaStatus getPropostaStatus() {
		return propostaStatus;
	}

	public CartaoDTOResponse getCartao() {
		return cartao;
	}
}
