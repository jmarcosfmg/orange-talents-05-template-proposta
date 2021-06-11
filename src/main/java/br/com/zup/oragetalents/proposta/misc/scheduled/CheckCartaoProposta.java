package br.com.zup.oragetalents.proposta.misc.scheduled;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.zup.oragetalents.proposta.cartao.Cartao;
import br.com.zup.oragetalents.proposta.cartao.CartaoRepository;
import br.com.zup.oragetalents.proposta.misc.external.cartoes.CartoesClient;
import br.com.zup.oragetalents.proposta.misc.external.cartoes.ResultadoAnaliseCartoes;
import br.com.zup.oragetalents.proposta.proposta.Proposta;
import br.com.zup.oragetalents.proposta.proposta.PropostaRepository;

@Component
public class CheckCartaoProposta {

	@Autowired
	private CartoesClient cartoesClient;

	@Autowired
	private PropostaRepository propostaRepository;

	@Autowired
	private CartaoRepository cartaoRepository;

	@Scheduled(fixedDelayString = "${proposta.timed.cartaoAnalise}") // Tempo sempre em milisegundos!
	private void checkCartaoProposta() {
		List<Proposta> updatedPropostas = propostaRepository.findAllByCartaoIsNull();
		List<Cartao> newCartaoList = new ArrayList<>();
		for (Proposta proposta : updatedPropostas) {
			try {
				ResultadoAnaliseCartoes resultado = cartoesClient.analise(proposta.getId().toString());
				Cartao newCartao = new Cartao(resultado.getId(), proposta);
				proposta.setCartao(newCartao);
				newCartaoList.add(newCartao);
			} catch (Exception e) {
				System.out.println(e.getLocalizedMessage());
			}
		}
		cartaoRepository.saveAll(newCartaoList);
		propostaRepository.saveAll(updatedPropostas);
	}

}
