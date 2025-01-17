package br.com.zup.oragetalents.proposta.proposta;

import java.net.URI;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.oragetalents.proposta.external.CustomMetrics;
import br.com.zup.oragetalents.proposta.external.financeira.FinanceiraClient;
import br.com.zup.oragetalents.proposta.external.financeira.ResultadoAnaliseFinanceira;
import br.com.zup.oragetalents.proposta.external.financeira.SolicitacaoAnalise;

@RestController
@RequestMapping("/proposta")
public class PropostaController {

	@Autowired
	private PropostaRepository propostaRepo;
	
	@Autowired
	private CustomMetrics metrics;

	@Autowired
	private FinanceiraClient financeira;

	@PostMapping
	@Transactional
	public ResponseEntity<?> criaProposta(@RequestBody @Valid PropostaRequest propostaRequest) {
		if (propostaRepo.existsByDocumento(new DocumentoLimpo(propostaRequest.getDocumento()).cifraDocumento()))
			return ResponseEntity.status(422).build();
		Proposta novaProposta = propostaRepo.save(propostaRequest.toModel());
		SolicitacaoAnalise solicitacaoAnalise = new SolicitacaoAnalise(novaProposta);
		ResultadoAnaliseFinanceira resultadoAnalise = financeira.analise(solicitacaoAnalise);
		novaProposta.setStatusProposta(resultadoAnalise.getResultadoSolicitacao());
		propostaRepo.save(novaProposta);
		metrics.propostaCriationCounter();
		return ResponseEntity.status(201)
				.header(HttpHeaders.LOCATION, URI.create("/proposta/" + novaProposta.getId().toString()).toString())
				.build();
	}

	 
	@GetMapping("/{id}")
	@Transactional
	public ResponseEntity<?> buscaProposta(@PathVariable UUID id) {
		Optional<Proposta> proposta = metrics.propostaTime().record(() -> propostaRepo.findById(id));
		metrics.propostaConsultCounter();
		if (proposta.isEmpty())
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(new PropostaResponse(proposta.get()));
	}
}
