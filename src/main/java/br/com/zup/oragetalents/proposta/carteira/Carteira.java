package br.com.zup.oragetalents.proposta.carteira;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Carteira {

	@Id
	@NotBlank
	private String id;

	@NotBlank
	@Email
	private String email;

	@NotNull
	@Enumerated(EnumType.STRING)
	private TipoCarteira carteira;

	public Carteira(@NotBlank String id, @NotBlank @Email String email, @NotNull TipoCarteira carteira) {
		this.id = id;
		this.email = email;
		this.carteira = carteira;
	}

	public String getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public TipoCarteira getCarteira() {
		return carteira;
	}
}
