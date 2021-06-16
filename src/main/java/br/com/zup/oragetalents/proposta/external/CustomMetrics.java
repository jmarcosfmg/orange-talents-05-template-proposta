package br.com.zup.oragetalents.proposta.external;


import org.springframework.stereotype.Component;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;

@Component
public class CustomMetrics {
	
	private final MeterRegistry registry;
	
	public CustomMetrics(MeterRegistry registry) {
		this.registry = registry;
	}
	
	public Timer propostaTime() {
		return this.registry.timer("consulta_proposta");
	}
	
	public Timer findCartaoTime() {
		return this.registry.timer("consulta_cartao");
	}
		
	public void biometriaCriationCounter() {
		Counter counter = this.registry.counter("biometria_criada");
		counter.increment();
	}

	public void propostaCriationCounter() {
		Counter counter = this.registry.counter("proposta_criada");
		counter.increment();
	}
	
	public void propostaConsultCounter() {
		Counter counter = this.registry.counter("proposta_consultada");
		counter.increment();
	}
}
