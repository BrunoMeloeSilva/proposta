package br.com.zupacademy.brunomeloesilva.proposta.cartoes;

import java.time.LocalDateTime;

public class CartaoDTOResponse {

	private String id;
	private LocalDateTime emitidoEm;
	private String titular;
	//private List<Object> bloqueios = null;
	//private List<Object> avisos = null;
	//private List<Object> carteiras = null;
	//private List<Object> parcelas = null;
	private Integer limite;
	//private Renegociacao renegociacao;
	//private Vencimento vencimento;
	private String idProposta;

	public String getId() {
		return id;
	}
	public LocalDateTime getEmitidoEm() {
		return emitidoEm;
	}
	public String getTitular() {
		return titular;
	}
	public Integer getLimite() {
		return limite;
	}
	public String getIdProposta() {
		return idProposta;
	}
	
}