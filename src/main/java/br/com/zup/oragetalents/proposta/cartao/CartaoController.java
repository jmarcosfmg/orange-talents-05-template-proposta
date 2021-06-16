package br.com.zup.oragetalents.proposta.cartao;

import java.util.function.Supplier;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.oragetalents.proposta.bloqueiocartao.BloqueioCartao;
import br.com.zup.oragetalents.proposta.carteira.Carteira;
import br.com.zup.oragetalents.proposta.carteira.CarteiraRequest;
import br.com.zup.oragetalents.proposta.carteira.ResultadoCarteira;
import br.com.zup.oragetalents.proposta.carteira.TipoCarteira;
import br.com.zup.oragetalents.proposta.external.Metrics;
import br.com.zup.oragetalents.proposta.external.cartoes.CartoesClient;
import br.com.zup.oragetalents.proposta.external.cartoes.aviso.Aviso;
import br.com.zup.oragetalents.proposta.external.cartoes.aviso.ResultadoAviso;
import br.com.zup.oragetalents.proposta.external.cartoes.bloqueio.BloqueioRequest;
import br.com.zup.oragetalents.proposta.viagem.AvisoViagem;
import br.com.zup.oragetalents.proposta.viagem.ViagemRequest;
import feign.FeignException;
import feign.FeignException.UnprocessableEntity;

@RestController
@RequestMapping("/cartoes")
public class CartaoController {

	@Autowired
	private CartoesClient cartoesClient;

	@Autowired
	private Metrics metrics;

	@PersistenceContext
	private EntityManager em;

	@RequestMapping(value = "/{idCartao}", method = RequestMethod.PATCH)
	@Transactional
	public ResponseEntity<?> bloqueiaCartao(@PathVariable String idCartao, HttpServletRequest request) {
		Cartao cartao = buscaCartao(idCartao);
		if (cartao.isBlocked())
			return ResponseEntity.status(422).body("Cartão se encontra bloqueado!");		
		BloqueioCartao bloqueio = new BloqueioCartao(cartao, request.getRemoteAddr(), request.getHeader("User-Agent"));
		ResultadoCarteira resultado = (ResultadoCarteira) requisitaCliente(() -> cartoesClient.bloqueia(idCartao, new BloqueioRequest("Proposta")));
		if (resultado.getResultado().equals("BLOQUEADO")) {
			cartao.bloqueiaCartao();
			em.persist(bloqueio);
			em.persist(cartao);
			return ResponseEntity.ok().body("Cartão Bloqueado!");
		}
		return ResponseEntity.status(500).body("Ação não permitida!");
	}

	@RequestMapping(value = "/{idCartao}/viagem", method = RequestMethod.POST)
	@Transactional
	public ResponseEntity<?> solicitaViagem(@PathVariable String idCartao, HttpServletRequest request,
			@RequestBody @Valid ViagemRequest viagemReq) {
		Cartao cartao = buscaCartao(idCartao);
		ResultadoAviso resultado = (ResultadoAviso) requisitaCliente(() -> cartoesClient.avisaViagem(idCartao, new Aviso(viagemReq.getDestino(), viagemReq.getTermino())));
		if(resultado.getResultado().equals("CRIADO")){
			AvisoViagem viagem = viagemReq.toModel(cartao, request.getRemoteAddr(), request.getHeader("User-Agent"));
			em.persist(viagem);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.status(500).body("Ação não permitida!");
	}

	@PutMapping("/{idCartao}")
	@Transactional
	public ResponseEntity<?> insereCarteira(@PathVariable String idCartao, @RequestBody @Valid CarteiraRequest carteiraReq, UriComponentsBuilder uriBuilder) {
		Cartao cartao = buscaCartao(idCartao);
		ResultadoCarteira resultado = (ResultadoCarteira) requisitaCliente(() -> cartoesClient.insereCarteira(idCartao, carteiraReq));
		Carteira carteira = new Carteira(resultado.getId(), carteiraReq.getEmail(), TipoCarteira.PAYPALL);
		cartao.addCarteira(carteira);
		em.persist(carteira);
		em.persist(cartao);
		return ResponseEntity.ok().build();
	}

	private Cartao buscaCartao(@NotBlank String idCartao) {
		Cartao cartao = metrics.findCartaoTime().record(() -> em.find(Cartao.class, idCartao));
		if (cartao == null) {
			throw new EntityNotFoundException("Cartao não foi encontrado");
		}
		return cartao;
	}

	private Object requisitaCliente(Supplier<?> s) {
		try {
			return s.get();
		}catch(UnprocessableEntity e) {
			throw e;
		}
		catch (FeignException e) {
			throw e;
		}
	}

}
