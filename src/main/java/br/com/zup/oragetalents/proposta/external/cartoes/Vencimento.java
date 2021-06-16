package br.com.zup.oragetalents.proposta.external.cartoes;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

public class Vencimento {
	private String id;
	private Integer dia;
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private LocalDateTime dataDeCriacao;

	@Deprecated
	public Vencimento() {

	}

	public String getId() {
		return id;
	}

	public Integer getDia() {
		return dia;
	}

	public LocalDateTime getDataDeCriacao() {
		return dataDeCriacao;
	}
}
