package br.com.zup.oragetalents.proposta.external;

public class ResultadoAnaliseFinanceira {
	private String documento;
	private String nome;
	private String idProposta;
	private String resultadoSolicitacao;

	public ResultadoAnaliseFinanceira(String documento, String nome, String idProposta, String resultadoSolicitacao) {
		this.documento = documento;
		this.nome = nome;
		this.idProposta = idProposta;
		this.resultadoSolicitacao = resultadoSolicitacao;
	}

	public String getDocumento() {
		return documento;
	}

	public String getNome() {
		return nome;
	}

	public String getIdProposta() {
		return idProposta;
	}

	public String getResultadoSolicitacao() {
		return resultadoSolicitacao;
	}

}
