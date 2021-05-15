package br.com.zupacademy.brunomeloesilva.proposta.cartoes.avisoviagem;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.zupacademy.brunomeloesilva.proposta.cartoes.CartaoModel;

@Entity
@Table(name = "AVISOVIAGEM")
public class AvisoViagemModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String destinoViagem;
	@Column(nullable = false) 
	private Date dataTerminoViagem;
	@Column(nullable = false)
	private LocalDateTime dataCadastro = LocalDateTime.now();
	@Column(nullable = false)
	private String userAgent;
	@Column(nullable = false)
	private String host;
	@OneToOne
	@JoinColumn(nullable = false)
	private CartaoModel cartao;
	
	@Deprecated
	public AvisoViagemModel() {/*Para Hibernate*/}
	public AvisoViagemModel(String destinoViagem, Date dataTerminoViagem, String userAgent,
			String host, CartaoModel cartao) {
		this.destinoViagem = destinoViagem;
		this.dataTerminoViagem = dataTerminoViagem;
		this.userAgent = userAgent;
		this.host = host;
		this.cartao = cartao;
	}
	public Long getId() {
		return id;
	}
	public String getDestinoViagem() {
		return destinoViagem;
	}
	public Date getDataTerminoViagem() {
		return dataTerminoViagem;
	}
	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}
	public String getUserAgent() {
		return userAgent;
	}
	public String getHost() {
		return host;
	}
	public CartaoModel getCartao() {
		return cartao;
	}
}
