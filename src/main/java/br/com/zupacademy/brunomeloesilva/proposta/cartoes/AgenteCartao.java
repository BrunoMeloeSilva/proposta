package br.com.zupacademy.brunomeloesilva.proposta.cartoes;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import br.com.zupacademy.brunomeloesilva.proposta.PropostaModel;
import feign.FeignException;

@Configuration
@EnableAsync
@EnableScheduling
public class AgenteCartao {
	
	@Autowired
	APICartao apiCartao;
	@PersistenceContext
	EntityManager entityManager;
	
	@Scheduled(fixedRate=6000)//60000*5
	@Transactional
	public void consultaGeracaoCartaoDasPropostasElegiveis() {
		List<String> listPropostaId = listaPropostasElegiveisSemCartao();
		
		listPropostaId.forEach(idProposta -> {
			try {
				CartaoDTOResponse cartaoGerado = apiCartao.verificaSeCartaoFoiGerado(idProposta);
				//Só quem precisa do try é a linha acima :)
				if(cartaoGerado != null ) {
					PropostaModel propostaModel = entityManager.find(PropostaModel.class, idProposta);
					CartaoModel cartaoModel = new CartaoModel(cartaoGerado);
					entityManager.persist(cartaoModel);
					propostaModel.setCartao(cartaoModel);
				}
			}catch(FeignException.FeignClientException.BadRequest e) {
				//TODO Preciso fazer ofuscação no logger de forma global, para melhorar essas mesagens que não dizem nada.
				System.err.println("A consulta para verificar se o cartão foi gerado, retornou erro 400, Bad Request.");
			}catch (FeignException.FeignClientException.InternalServerError e) {
				System.err.println("A consulta para verificar se o cartão foi gerado, retornou erro 500, Internal Server Error.");
			}
		});
	}
	
	private List<String> listaPropostasElegiveisSemCartao() {
		//TODO Essa string SQL, Hard Coded, aqui é um ponto de falha de segurança, como corrigir isso ?
		String sql = String.format("Select t.UUID From PropostaModel t Where t.propostaStatus = 'ELEGIVEL' And t.cartao Is Null");
		TypedQuery<String> query = entityManager.createQuery(sql, String.class);
		List<String> listPropostaId = query.getResultList();
		return listPropostaId;
	}
}