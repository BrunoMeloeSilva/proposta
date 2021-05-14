package br.com.zupacademy.brunomeloesilva.proposta;

import java.math.BigDecimal;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.zupacademy.brunomeloesilva.proposta.validacoes.CpfOuCnpj;
import br.com.zupacademy.brunomeloesilva.proposta.validacoes.RegraDeNegocios;
import br.com.zupacademy.brunomeloesilva.share.validacoes.ValorDuplicado;

public class PropostaDTORequest {
	
	@NotBlank @CpfOuCnpj 
	@ValorDuplicado(classeDominio = PropostaModel.class, nomeCampo = "cpfOuCnpj", groups = RegraDeNegocios.class)
	private String cpfOuCnpj;
	@NotBlank @Email
	private String email;
	@NotBlank
	private String nome;
	@NotBlank
	private String endereco;
	@NotNull @Positive
	private BigDecimal salario;
	
	public PropostaDTORequest(String cpfOuCnpj, String email, String nome,
			String endereco, BigDecimal salario) {
		this.cpfOuCnpj = cpfOuCnpj.replaceAll("[\\.\\-\\/]", "");
		this.email = email;
		this.nome = nome;
		this.endereco = endereco;
		this.salario = salario;
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

	public PropostaModel toModel() {
		return new PropostaModel(this);
	}
	
	
}
