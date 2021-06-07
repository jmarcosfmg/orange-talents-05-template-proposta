package br.com.zup.oragetalents.proposta.external.cartoes;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

public class Renegociacao {
	private String id;
	private Integer quantidade;
	private Double valor;
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private LocalDateTime dataDeCriacao;

	@Deprecated
	public Renegociacao() {

	}

	public String getId() {
		return id;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public Double getValor() {
		return valor;
	}

	public LocalDateTime getDataDeCriacao() {
		return dataDeCriacao;
	}

}
