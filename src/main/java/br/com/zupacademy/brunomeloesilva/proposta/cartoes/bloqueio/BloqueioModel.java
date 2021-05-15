package br.com.zupacademy.brunomeloesilva.proposta.cartoes.bloqueio;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BLOQUEIO")
public class BloqueioModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private LocalDateTime dataBloqueio = LocalDateTime.now(); 
	@Column(nullable = false)
	private String userAgent;
	@Column(nullable = false)
	private String host;
	
	@Deprecated
	public BloqueioModel() {/*Para Hibernate*/}
	public BloqueioModel(String userAgent, String host) {
		this.userAgent = userAgent;
		this.host = host;
	}
	public Long getId() {
		return id;
	}
	public LocalDateTime getDataBloqueio() {
		return dataBloqueio;
	}
	public String getUserAgent() {
		return userAgent;
	}
	public String getHost() {
		return host;
	}	
}
