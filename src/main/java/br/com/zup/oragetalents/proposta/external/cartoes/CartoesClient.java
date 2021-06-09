package br.com.zup.oragetalents.proposta.external.cartoes;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(name="cartoes", url="${proposta.external.cartoes.host}:${proposta.external.cartoes.port}/api")
public interface CartoesClient {
	
	@GetMapping("/cartoes")
	ResultadoAnaliseCartoes analise(@RequestParam("idProposta") String idProposta);
}
