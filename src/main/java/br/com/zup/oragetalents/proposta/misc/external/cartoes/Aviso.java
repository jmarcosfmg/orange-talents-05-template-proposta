package br.com.zup.oragetalents.proposta.misc.external.cartoes;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

public class Aviso {

	@DateTimeFormat(iso = ISO.DATE)
	private LocalDateTime validoAte;
	private String destino;

	@Deprecated
	public Aviso() {

	}

	public LocalDateTime getValidoAte() {
		return validoAte;
	}

	public String getDestino() {
		return destino;
	}

}
