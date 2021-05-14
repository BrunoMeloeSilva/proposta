package br.com.zupacademy.brunomeloesilva.proposta.biometria;

import br.com.zupacademy.brunomeloesilva.proposta.cartoes.CartaoModel;
import br.com.zupacademy.brunomeloesilva.share.validacoes.ValorDuplicado;

public class BiometriaDTORequest {

	@Base64 @ValorDuplicado(classeDominio = BiometriaModel.class, nomeCampo = "fingerprint")
	private String fingerprint;

	public String getFingerprint() {
		return fingerprint;
	}

	public BiometriaModel toModel(CartaoModel cartaoModel) {
		return new BiometriaModel(fingerprint, cartaoModel);
	}
}
