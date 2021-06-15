package br.com.zup.oragetalents.proposta.cartao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.oragetalents.proposta.cartao.bloqueio.BloqueioCartao;
import br.com.zup.oragetalents.proposta.misc.external.cartoes.BloqueioRequest;
import br.com.zup.oragetalents.proposta.misc.external.cartoes.CartoesClient;

@RestController
@RequestMapping("/cartoes")
public class CartaoController {

	@Autowired
	private CartoesClient cartoesClient;

	@PersistenceContext
	private EntityManager em;

	@RequestMapping(value = "/{idCartao}", method = RequestMethod.PATCH)
	@Transactional
	public ResponseEntity<?> bloqueiaCartao(@PathVariable String idCartao, HttpServletRequest request) {
		Cartao cartao = em.find(Cartao.class, idCartao);
		if (cartao == null)
			return ResponseEntity.status(404).body("Cartão inexistente!");
		if (cartao.isBlocked())
			return ResponseEntity.status(422).body("Cartão já se encontra bloqueado!");
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
}
