package br.com.zupacademy.brunomeloesilva.proposta;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "PROPOSTA")
public class PropostaModel {

	@Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	private String UUID;
	@Column(nullable = false, length = 18)
	private String cpfOuCnpj;
	@Column(nullable = false, length = 100)
	private String email;
	@Column(nullable = false, length = 100)
	private String nome;
	@Column(nullable = false)
	private String endereco;
	@Column(nullable = false, columnDefinition = "NUMERIC(15,2) UNSIGNED")
	private BigDecimal salario;
	
	@Deprecated
	public PropostaModel() {/*Somente para o Hibernate*/}
	public PropostaModel(PropostaDTORequest propostaDTORequest) {
		this.cpfOuCnpj = propostaDTORequest.getCpfOuCnpj();
		this.email = propostaDTORequest.getEmail();
		this.nome = propostaDTORequest.getNome();
		this.endereco = propostaDTORequest.getEndereco();
		this.salario = propostaDTORequest.getSalario();
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
}
