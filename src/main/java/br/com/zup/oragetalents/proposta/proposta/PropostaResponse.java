package br.com.zup.oragetalents.proposta.proposta;

import java.util.UUID;

public class PropostaResponse {

	private UUID id;

	private String documento;

	private String nome;

	private String email;

	private String endereco;

	private Double salario;

	private String status;

	private String numeroCartao;

	public PropostaResponse(Proposta proposta) {
		this.id = proposta.getId();
		this.documento = proposta.getDocumento();
		this.nome = proposta.getNome();
		this.email = proposta.getEmail();
		this.endereco = proposta.getEndereco();
		this.salario = proposta.getSalario();
		this.status = proposta.getStatusProposta();
		this.numeroCartao = proposta.getNumeroCartao();
	}

	public UUID getId() {
		return id;
	}

	public String getDocumento() {
		return documento;
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

	public String getNome() {
		return nome;
	}

	public String getStatus() {
		return status;
	}

	public String getNumeroCartao() {
		return numeroCartao;
	}
}
