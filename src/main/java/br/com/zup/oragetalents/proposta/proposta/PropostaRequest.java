package br.com.zup.oragetalents.proposta.proposta;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import br.com.zup.oragetalents.proposta.misc.validators.CPFOrCNPJ;

public class PropostaRequest {

	@NotBlank
	@CPFOrCNPJ
	private String documento;

	@NotBlank
	@Email
	private String email;
	
	@NotBlank
	private String nome;

	@NotBlank
	private String endereco;

	@NotNull
	@PositiveOrZero
	private Double salario;

	public PropostaRequest(@CPFOrCNPJ String documento, @NotBlank @Email String email, @NotBlank String endereco,
			@NotNull @PositiveOrZero Double salario, @NotBlank String nome) {
		this.documento = documento;
		this.email = email;
		this.endereco = endereco;
		this.salario = salario;
		this.nome = nome;
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

	public Proposta toModel() {
		return new Proposta(this.documento, this.email, this.nome, this.endereco, this.salario);
	}
}
