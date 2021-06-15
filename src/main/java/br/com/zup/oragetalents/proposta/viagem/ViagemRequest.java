package br.com.zup.oragetalents.proposta.viagem;

import java.util.Date;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import br.com.zup.oragetalents.proposta.cartao.Cartao;

public class ViagemRequest {

	@NotBlank
	private String destino;

	@NotNull
	@Future
	@DateTimeFormat(iso = ISO.DATE)
	private Date termino;

	public ViagemRequest(@NotBlank String destino, @NotNull @Future Date termino) {
		this.destino = destino;
		this.termino = termino;
	}

	public String getDestino() {
		return destino;
	}

	public Date getTermino() {
		return termino;
	}

	public AvisoViagem toModel(Cartao cartao, String remoteAddr, String userAgent) {
		return new AvisoViagem(cartao, remoteAddr, userAgent, this.destino, this.termino);
	}
}
