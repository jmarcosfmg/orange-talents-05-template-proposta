package br.com.zup.oragetalents.proposta.carteira;

public class ResultadoCarteira {
	private String resultado;
	
	private String id;

	public ResultadoCarteira(String resultado, String id) {
		this.resultado = resultado;
		this.id = id;
	}
	
	@Deprecated
	public ResultadoCarteira() {
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}	
}
