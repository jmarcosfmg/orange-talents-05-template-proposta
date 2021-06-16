package br.com.zup.oragetalents.proposta.external.cartoes;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.zup.oragetalents.proposta.carteira.CarteiraRequest;
import br.com.zup.oragetalents.proposta.carteira.ResultadoCarteira;
import br.com.zup.oragetalents.proposta.external.cartoes.aviso.Aviso;
import br.com.zup.oragetalents.proposta.external.cartoes.aviso.ResultadoAviso;
import br.com.zup.oragetalents.proposta.external.cartoes.bloqueio.BloqueioRequest;
import br.com.zup.oragetalents.proposta.external.cartoes.bloqueio.ResultadoBloqueio;

@Component
@FeignClient(name = "cartoes", url = "${proposta.external.cartoes.host}:${proposta.external.cartoes.port}/api/cartoes")
public interface CartoesClient {

	@GetMapping("")
	ResultadoAnaliseCartoes analise(@RequestParam("idProposta") String idProposta);

	@PostMapping("/{id}/bloqueios")
	ResultadoBloqueio bloqueia(@PathVariable("id") String idCartao, @RequestBody BloqueioRequest sistemaResponsavel);

	@PostMapping("/{id}/avisos")
	ResultadoAviso avisaViagem(@PathVariable("id") String idCartao, @RequestBody Aviso avisoReq);

	@PostMapping("/{id}/carteiras")
	ResultadoCarteira insereCarteira(@PathVariable("id") String idCartao, @RequestBody CarteiraRequest carteiraReq);

}
