package br.com.zupacademy.brunomeloesilva.proposta.analisefinanceira;

import br.com.zupacademy.brunomeloesilva.proposta.PropostaModel;
import br.com.zupacademy.brunomeloesilva.share.seguranca.Criptografia;

public class AnaliseFinanceiraDTORequest {
	
	private String documento;
	private String nome;
	private String idProposta;
	
	public AnaliseFinanceiraDTORequest(PropostaModel propostaModel, Criptografia criptografia) {
		this.documento = criptografia.descriptografar(propostaModel.getCpfOuCnpj());
		this.nome = propostaModel.getNome();
		this.idProposta = propostaModel.getUUID();
	}

	public String getDocumento() {
		return documento;
	}
	public String getNome() {
		return nome;
	}
	public String getIdProposta() {
		return idProposta;
	}
}