package br.com.zupacademy.brunomeloesilva.proposta.analisefinanceira;

public class AnaliseFinanceiraDTOResponse {
	
	private String documento;
	private String nome;
	private AnaliseFinanceiraStatus resultadoSolicitacao;
	private String idProposta;
	
	public String getDocumento() {
		return documento;
	}
	public String getNome() {
		return nome;
	}
	public AnaliseFinanceiraStatus getResultadoSolicitacao() {
		return resultadoSolicitacao;
	}
	public String getIdProposta() {
		return idProposta;
	}
}
