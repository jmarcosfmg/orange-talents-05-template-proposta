package br.com.zup.oragetalents.proposta.misc.external.cartoes;

public class Parcela {
	private String id;
	private Integer quantidade;
	private Double valor;

	@Deprecated
	public Parcela() {

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
}
