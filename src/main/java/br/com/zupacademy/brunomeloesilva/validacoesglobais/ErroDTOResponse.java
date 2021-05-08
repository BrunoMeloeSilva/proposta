package br.com.zupacademy.brunomeloesilva.validacoesglobais;

public class ErroDTOResponse {

	private String campo;
	private String mensagem;

	public ErroDTOResponse(String field, String mensagem) {
		this.campo = field;
		this.mensagem = mensagem;
	}

	public String getCampo() { return campo; }
	public String getMensagem() { return mensagem; }
}
