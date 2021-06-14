package br.com.zup.oragetalents.proposta.cartao;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.oragetalents.proposta.cartao.bloqueio.BloqueioCartao;

@RestController
@RequestMapping("/cartoes")
public class CartaoController {
	
	@PersistenceContext
	private EntityManager em;
	
	@RequestMapping(value="/{idCartao}", method=RequestMethod.PATCH)
	@Transactional
	public ResponseEntity<?> bloqueiaCartao(@PathVariable String idCartao,HttpServletRequest request) {
		Cartao cartao = em.find(Cartao.class, idCartao);
		if(cartao == null) return ResponseEntity.status(404).body("Cartão inexistente!");
		if(cartao.getBloqueio() != null) return ResponseEntity.status(422).body("Cartão já se encontra bloqueado!");
		BloqueioCartao bloqueio = new BloqueioCartao(cartao, request.getRemoteAddr(), request.getHeader("User-Agent"));
		em.persist(bloqueio);
		em.persist(cartao);
		return ResponseEntity.ok().body("Cartão Bloqueado!");
	}
}
