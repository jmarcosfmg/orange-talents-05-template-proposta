package br.com.zup.oragetalents.proposta.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Component
@FeignClient(name="financeira", url="localhost:9999/api")
public interface FinanceiraClient {
	
	@PostMapping("/solicitacao")
	ResultadoAnaliseFinanceira analise(@RequestBody SolicitacaoAnalise solicitacao);
	

}
