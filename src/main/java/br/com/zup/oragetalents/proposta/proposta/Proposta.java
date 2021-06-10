package br.com.zup.oragetalents.proposta.proposta;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import org.hibernate.annotations.GenericGenerator;

import br.com.zup.oragetalents.proposta.cartao.Cartao;
import br.com.zup.oragetalents.proposta.external.financeira.StatusConverter;
import br.com.zup.oragetalents.proposta.validators.CPFOrCNPJ;

@Entity
public class Proposta {

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(columnDefinition = "BINARY(16)")
	private UUID id;

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

	@OneToOne
	private Cartao cartao;

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

	public Cartao getCartao() {
		return this.cartao;
	}

	public void setCartao(Cartao cartao) {
		this.cartao = cartao;
	}

	public String getNumeroCartao() {
		if (this.cartao == null)
			return null;
		return this.cartao.getNumero();
	}

}
