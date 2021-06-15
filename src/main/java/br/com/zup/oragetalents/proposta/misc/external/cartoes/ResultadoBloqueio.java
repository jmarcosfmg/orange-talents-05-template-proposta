package br.com.zup.oragetalents.proposta.misc.external.cartoes;

import javax.validation.constraints.NotBlank;

public class ResultadoBloqueio {
	
	@NotBlank
	private String resultado;

	public ResultadoBloqueio(@NotBlank String resultado) {
		this.resultado = resultado;
	}
	
	@Deprecated
	public ResultadoBloqueio() {
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}


}
