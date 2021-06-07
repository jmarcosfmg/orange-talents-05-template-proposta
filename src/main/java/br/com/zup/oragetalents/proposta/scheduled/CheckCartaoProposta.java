package br.com.zup.oragetalents.proposta.scheduled;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.zup.oragetalents.proposta.external.cartoes.CartoesClient;
import br.com.zup.oragetalents.proposta.external.cartoes.ResultadoAnaliseCartoes;
import br.com.zup.oragetalents.proposta.proposta.Proposta;
import br.com.zup.oragetalents.proposta.proposta.PropostaRepository;

@Component
public class CheckCartaoProposta {

	@Autowired
	private CartoesClient cartoesClient;

	@Autowired
	private PropostaRepository propostaRepository;

	@Scheduled(fixedDelayString = "${proposta.timed.cartaoAnalise}") // Tempo sempre em milisegundos!
	private void checkCartaoProposta() {
		List<Proposta> updatedPropostas = propostaRepository.findAllByNumeroCartaoIsNull();
		for (Proposta proposta : updatedPropostas) {
			try {
				ResultadoAnaliseCartoes resultado = cartoesClient.analise(proposta.getId().toString());
				proposta.setNumeroCartao(resultado.getId());
			}catch(Exception e) {
				System.out.println(e.getLocalizedMessage());
			}
		}
		propostaRepository.saveAll(updatedPropostas);
	}

}
