package br.com.zup.oragetalents.proposta.misc.external.cartoes;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(name="cartoes", url="${proposta.external.cartoes.host}:${proposta.external.cartoes.port}/api/cartoes")
public interface CartoesClient {
	
	@GetMapping("")
	ResultadoAnaliseCartoes analise(@RequestParam("idProposta") String idProposta);
	
	@PostMapping("/{id}/bloqueios")
	ResultadoBloqueio bloqueia(@PathVariable("id") String idCartao, @RequestBody BloqueioRequest sistemaResponsavel);
	
}
