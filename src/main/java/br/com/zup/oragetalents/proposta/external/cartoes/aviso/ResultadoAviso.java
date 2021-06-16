package br.com.zup.oragetalents.proposta.external.cartoes.aviso;

public class ResultadoAviso {
	private String resultado;

	public ResultadoAviso(String resultado) {
		this.resultado = resultado;
	}
	@Deprecated
	public ResultadoAviso() {
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}
}
