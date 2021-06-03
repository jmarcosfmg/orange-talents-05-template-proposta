package br.com.zup.oragetalents.proposta.proposta;

public class PropostaResponse {

	private Long id;

	private String email;

	private String endereco;

	private Double salario;

	public PropostaResponse(Proposta proposta) {
		this.id = proposta.getId();
		this.email = proposta.getEmail();
		this.endereco = proposta.getEndereco();
		this.salario = proposta.getSalario();
	}

	public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public String getEndereco() {
		return endereco;
	}

	public Double getSalario() {
		return salario;
	}

}
