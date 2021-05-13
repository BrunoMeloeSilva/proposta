package br.com.zupacademy.brunomeloesilva.proposta.cartoes;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
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

	@Deprecated
	public CartaoDTOResponse() {/*Para retorno do Feign*/}
	public CartaoDTOResponse(CartaoModel cartao) {
		id = cartao.getId();
		emitidoEm = cartao.getEmitidoEm();
		titular = cartao.getTitular();
		limite = cartao.getLimite();
	}
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