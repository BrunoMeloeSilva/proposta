package br.com.zupacademy.brunomeloesilva.proposta;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import br.com.zupacademy.brunomeloesilva.proposta.cartoes.CartaoModel;

@Entity
@Table(name = "PROPOSTA")
public class PropostaModel {

	@Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	private String UUID;
	@Column(nullable = false, length = 18, unique = true)
	private String cpfOuCnpj;
	@Column(nullable = false, length = 100)
	private String email;
	@Column(nullable = false, length = 100)
	private String nome;
	@Column(nullable = false)
	private String endereco;
	@Column(nullable = false, columnDefinition = "NUMERIC(15,2) UNSIGNED")
	private BigDecimal salario;
	@Enumerated(EnumType.STRING)
	private PropostaStatus propostaStatus;
	@OneToOne 
	private CartaoModel cartao;
	
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
	public PropostaStatus getPropostaStatus() {
		return propostaStatus;
	}
	public void setPropostaStatus(PropostaStatus propostaStatus) {
		this.propostaStatus = propostaStatus;
	}
	public CartaoModel getCartao() {
		return cartao;
	}
	public void setCartao(CartaoModel cartaoModel) {
		this.cartao = cartaoModel;
	}
}
