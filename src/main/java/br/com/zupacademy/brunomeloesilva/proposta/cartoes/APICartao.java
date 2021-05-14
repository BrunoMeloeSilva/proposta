package br.com.zupacademy.brunomeloesilva.proposta.cartoes;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "cartoes", url = "${api.cartoes.domain}")
public interface APICartao {
	
	@GetMapping("${api.cartoes.domain.recurso}")
    CartaoDTOResponse verificaSeCartaoFoiGerado(@PathVariable String idProposta);
	
	@PostMapping("${api.cartoes.domain.recurso.bloqueio}")
	CartaoStatusDTOResposnse informaBloqueioCartao(@PathVariable String numeroCartao, BloqueioDTORequest sistemaResponsavel);
	
}