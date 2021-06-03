package br.com.zup.oragetalents.proposta.proposta;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import br.com.zup.oragetalents.proposta.validators.CPFOrCNPJ;

@Entity
public class Proposta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@CPFOrCNPJ
	private String documento;

	@NotBlank
	@Email
	private String email;

	@NotBlank
	private String endereco;

	@NotNull
	@PositiveOrZero
	private Double salario;

	public Proposta(String documento, @NotBlank @Email String email, @NotBlank String endereco,
			@NotNull @PositiveOrZero Double salario) {
		this.documento = documento;
		this.email = email;
		this.endereco = endereco;
		this.salario = salario;
	}

	@Deprecated
	public Proposta() {
	}

	public Long getId() {
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
}
