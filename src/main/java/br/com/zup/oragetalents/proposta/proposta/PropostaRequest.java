package br.com.zup.oragetalents.proposta.proposta;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

public class PropostaRequest {
	@NotBlank
	@Email
	private String email;

	@NotBlank
	private String endereco;

	@NotNull
	@PositiveOrZero
	private Double salario;

	public PropostaRequest(@NotBlank @Email String email, @NotBlank String endereco,
			@NotNull @PositiveOrZero Double salario) {
		super();
		this.email = email;
		this.endereco = endereco;
		this.salario = salario;
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
		return new Proposta(this.email, this.endereco, this.salario);
	}
}
