package br.com.zupacademy.brunomeloesilva.proposta.analisefinanceira;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "restricoesFinanceiras", url = "${api.restricoesFinanceiras.domain}")
public interface AnalisaRestricoesFinanceiras {
    
    @PostMapping("${api.restricoesFinanceiras.domain.recurso}")
    AnaliseFinanceiraDTOResponse analisar(AnaliseFinanceiraDTORequest dtoRequestAnaliseFinanceira);
    
}