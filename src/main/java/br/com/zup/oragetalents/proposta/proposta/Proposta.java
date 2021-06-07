package br.com.zup.oragetalents.proposta.proposta;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import br.com.zup.oragetalents.proposta.external.financeira.StatusConverter;
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
	private String nome;

	@NotBlank
	private String endereco;

	@NotNull
	@PositiveOrZero
	private Double salario;

	@Convert(converter = StatusConverter.class)
	private StatusProposta status;

	private String numeroCartao;

	public Proposta(String documento, @NotBlank @Email String email, @NotBlank String nome, @NotBlank String endereco,
			@NotNull @PositiveOrZero Double salario) {
		this.documento = documento;
		this.email = email;
		this.nome = nome;
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

	public void setStatusProposta(StatusProposta status) {
		this.status = status;
	}

	public void setStatusProposta(String status) {
		Class<StatusProposta> enumType = StatusProposta.class;
		this.status = Enum.valueOf(enumType, status);
	}

	public String getStatusProposta() {
		return this.status.toString();
	}

	public String getNome() {
		return this.nome;
	}

	public Double getSalario() {
		return salario;
	}

	public String getNumeroCartao() {
		return this.numeroCartao;
	}

	public void setNumeroCartao(String cartao) {
		this.numeroCartao = cartao;
	}

}
