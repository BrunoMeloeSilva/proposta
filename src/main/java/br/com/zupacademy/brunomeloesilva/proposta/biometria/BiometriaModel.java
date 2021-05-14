package br.com.zupacademy.brunomeloesilva.proposta.biometria;

import java.time.LocalDateTime;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.zupacademy.brunomeloesilva.proposta.cartoes.CartaoModel;

@Entity
@Table(name = "BIOMETRIA")
public class BiometriaModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(optional = false)
	private CartaoModel cartao;
	@Column(nullable = false, unique = true)  @Lob @Basic(fetch = FetchType.LAZY)
	private String fingerprint;
	@Column(nullable = false) 
	private LocalDateTime dataCadastro = LocalDateTime.now();
	
	@Deprecated
	public BiometriaModel() {/*Para o Hibernate*/}
	public BiometriaModel(String fingerprint, CartaoModel cartao) {
		this.fingerprint = fingerprint;
		this.cartao = cartao;
	}
	public Long getId() {
		return id;
	}
	public CartaoModel getCartao() {
		return cartao;
	}
	public String getFingerprint() {
		return fingerprint;
	}
	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}
}
