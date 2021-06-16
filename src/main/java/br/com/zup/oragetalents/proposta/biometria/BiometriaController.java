package br.com.zup.oragetalents.proposta.biometria;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.oragetalents.proposta.cartao.Cartao;
import br.com.zup.oragetalents.proposta.cartao.CartaoRepository;
import br.com.zup.oragetalents.proposta.external.CustomMetrics;

@RestController
@RequestMapping("/biometria")
public class BiometriaController {

	@Autowired
	private CartaoRepository cartaoRepository;
	@Autowired
	private BiometriaRepository biometriaRepository;
	@Autowired
	private CustomMetrics metrics;

	@PostMapping("/{idCartao}")
	@Transactional
	public ResponseEntity<?> criaBiometria(@PathVariable(name = "idCartao", required = true) String idCartao,
			@RequestBody BiometriaRequest biometriaReq, UriComponentsBuilder uriCB) {
		Optional<Cartao> cartao = cartaoRepository.findById(idCartao);
		metrics.biometriaCriationCounter();
		if (cartao.isEmpty())
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cartão não encontrado");
		if (!biometriaReq.isValid())
			return ResponseEntity.badRequest().body("Biometria inválida");
		Biometria novaBiometria = biometriaReq.toModel(cartao.get());
		biometriaRepository.save(novaBiometria);
		cartao.get().addBiometria(novaBiometria);
		return ResponseEntity.created(uriCB.path("/biometria/{id}").buildAndExpand(novaBiometria.getId()).toUri())
				.build();

	}
}
