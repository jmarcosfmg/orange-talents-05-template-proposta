package br.com.zup.oragetalents.proposta.cartao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.oragetalents.proposta.cartao.bloqueio.BloqueioCartao;
import br.com.zup.oragetalents.proposta.misc.external.Metrics;
import br.com.zup.oragetalents.proposta.misc.external.cartoes.BloqueioRequest;
import br.com.zup.oragetalents.proposta.misc.external.cartoes.CartoesClient;
import br.com.zup.oragetalents.proposta.viagem.AvisoViagem;
import br.com.zup.oragetalents.proposta.viagem.ViagemRequest;

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
		Cartao cartao = metrics.findCartaoTime().record(() -> em.find(Cartao.class, idCartao));
		if (cartao == null)
			return ResponseEntity.status(404).body("Cartão inexistente!");
		if (cartao.isBlocked())
			return ResponseEntity.status(422).body("Cartão se encontra bloqueado!");
		BloqueioCartao bloqueio = new BloqueioCartao(cartao, request.getRemoteAddr(), request.getHeader("User-Agent"));
		try {
			String resultado = cartoesClient.bloqueia(idCartao, new BloqueioRequest("Proposta")).getResultado();
			if (resultado.equals("BLOQUEADO")) {
				cartao.bloqueiaCartao();
				em.persist(bloqueio);
			}
		} catch (Exception e) {
			return ResponseEntity.status(500).build();
		}
		em.persist(cartao);
		return ResponseEntity.ok().body("Cartão Bloqueado!");
	}

	@RequestMapping(value = "/{idCartao}/viagem", method = RequestMethod.POST)
	@Transactional
	public ResponseEntity<?> solicitaViagem(@PathVariable String idCartao, HttpServletRequest request,
			@RequestBody @Valid ViagemRequest viagemReq) {
		Cartao cartao = metrics.findCartaoTime().record(() -> em.find(Cartao.class, idCartao));
		if (cartao == null)
			return ResponseEntity.status(404).body("Cartão inexistente!");
		AvisoViagem viagem = viagemReq.toModel(cartao, request.getRemoteAddr(), request.getHeader("User-Agent"));
		em.persist(viagem);
		return ResponseEntity.ok().build();
	}
}
