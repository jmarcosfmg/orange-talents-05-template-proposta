package br.com.zup.oragetalents.proposta.proposta;

import java.net.URI;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/proposta")
public class PropostaController {

	@PersistenceContext
	private EntityManager em;

	@PostMapping
	@Transactional
	public ResponseEntity<?> criaProposta(@RequestBody @Valid PropostaRequest propostaRequest) {
		Proposta novaProposta = propostaRequest.toModel();
		em.persist(novaProposta);
		em.flush();
		return ResponseEntity.status(201)
				.header(HttpHeaders.LOCATION, URI.create("/proposta/" + novaProposta.getId().toString()).toString())
				.build();
	}

	@GetMapping
	@RequestMapping("/{id}")
	@Transactional
	public ResponseEntity<?> buscaProposta(@PathVariable Long id) {
		Proposta proposta = em.find(Proposta.class, id);
		Assert.notNull(proposta,"Não existe proposta com o id informado");
		return ResponseEntity.ok(new PropostaResponse(proposta));
	}
}
