package br.com.zupacademy.brunomeloesilva.proposta.cartoes.carteira;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.zupacademy.brunomeloesilva.proposta.cartoes.CartaoModel;

@Entity
@Table(name = "CARTEIRADIGITAL")
public class CarteiraModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToOne
	@JoinColumn(nullable = false)
	private CartaoModel cartao;
	@Column(nullable = false)
	private String email;
	@Column(nullable = false)
	private LocalDateTime dataCriacao = LocalDateTime.now();
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private TipoCarteira carteira;
	
	@Deprecated
	public CarteiraModel() {/*Para Hibernate*/}
	public CarteiraModel(CartaoModel cartao, String email, TipoCarteira carteira) {
		this.cartao = cartao;
		this.email = email;
		this.carteira = carteira;
	}
	
	public Long getId() {
		return id;
	}
	public CartaoModel getCartao() {
		return cartao;
	}
	public String getEmail() {
		return email;
	}
	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}
	public TipoCarteira getCarteira() {
		return carteira;
	}
	
	
}
