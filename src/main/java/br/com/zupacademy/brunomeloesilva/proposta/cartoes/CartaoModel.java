package br.com.zupacademy.brunomeloesilva.proposta.cartoes;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.zupacademy.brunomeloesilva.proposta.cartoes.avisoviagem.AvisoViagemModel;
import br.com.zupacademy.brunomeloesilva.proposta.cartoes.bloqueio.BloqueioModel;

@Entity
@Table(name = "CARTAO")
public class CartaoModel {

	@Id
	private String id;
	private LocalDateTime emitidoEm;
	private String titular;
	@OneToOne
	private BloqueioModel bloqueio;
	@OneToMany(mappedBy = "cartao")
	private List<AvisoViagemModel> avisosDeViagens;
	//private List<Object> carteiras = null;
	//private List<Object> parcelas = null;
	private Integer limite;
	//private Renegociacao renegociacao;
	//private Vencimento vencimento;
	private String idProposta;
	private String status;
	
	@Deprecated
	public CartaoModel() {/*Para uso do Hibernate no retorno das consultas*/}
	public CartaoModel(CartaoDTOResponse cartaoGerado) {
		this.id = cartaoGerado.getId();
		this.emitidoEm = cartaoGerado.getEmitidoEm();
		this.titular = cartaoGerado.getTitular();
		this.limite = cartaoGerado.getLimite();
		this.idProposta = cartaoGerado.getIdProposta();
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
	public BloqueioModel getBloqueio() {
		return bloqueio;
	}
	public void setBloqueio(BloqueioModel bloqueio) {
		this.bloqueio = bloqueio;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<AvisoViagemModel> getAvisosDeViagens() {
		return avisosDeViagens;
	}	
	
	
}
//TODO Deveria habilitar UTC no projeto.