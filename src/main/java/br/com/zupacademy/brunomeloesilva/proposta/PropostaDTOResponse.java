package br.com.zupacademy.brunomeloesilva.proposta;

import java.math.BigDecimal;

public class PropostaDTOResponse {
	
	private String UUID;
	private String cpfOuCnpj;
	private String email;
	private String nome;
	private String endereco;
	private BigDecimal salario;
	private PropostaStatus propostaStatus;

	public PropostaDTOResponse(PropostaModel propostaModel) {
		this.UUID = propostaModel.getUUID();
		this.cpfOuCnpj = propostaModel.getCpfOuCnpj();
		this.email = propostaModel.getEmail();
		this.nome = propostaModel.getNome();
		this.endereco = propostaModel.getEndereco();
		this.salario = propostaModel.getSalario();
		this.propostaStatus = propostaModel.getPropostaStatus();
	}

	public String getUUID() {
		return UUID;
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
	
}
