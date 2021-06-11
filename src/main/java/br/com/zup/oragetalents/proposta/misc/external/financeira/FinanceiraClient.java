package br.com.zup.oragetalents.proposta.misc.external.financeira;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Component
@FeignClient(name="financeira", url="${proposta.external.financeira.host}:${proposta.external.financeira.port}/api")
public interface FinanceiraClient {
	
	@PostMapping("/solicitacao")
	ResultadoAnaliseFinanceira analise(@RequestBody SolicitacaoAnalise solicitacao);

}
