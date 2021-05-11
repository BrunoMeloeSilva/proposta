package br.com.zupacademy.brunomeloesilva.proposta.analisefinanceira;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "restricoesFinanceiras", url = "http://localhost:9999")
public interface AnalisaRestricoesFinanceiras {
    
    @PostMapping("/api/solicitacao")
    AnaliseFinanceiraDTOResponse analisar(AnaliseFinanceiraDTORequest dtoRequestAnaliseFinanceira);
    
}