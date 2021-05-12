package br.com.zupacademy.brunomeloesilva.proposta.cartoes;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "cartoes", url = "${api.cartoes.domain}")
public interface APICartao {
	
	@GetMapping("${api.cartoes.domain.recurso}")
    CartaoDTOResponse verificaSeCartaoFoiGerado(@PathVariable String idProposta);
}