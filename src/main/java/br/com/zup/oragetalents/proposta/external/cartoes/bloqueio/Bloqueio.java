package br.com.zup.oragetalents.proposta.external.cartoes.bloqueio;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

public class Bloqueio {
	private String id;
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private LocalDateTime bloqueadoEm;

	@Deprecated
	public Bloqueio() {
	}

	public String getId() {
		return id;
	}

	public LocalDateTime getBloqueadoEm() {
		return bloqueadoEm;
	}

}
