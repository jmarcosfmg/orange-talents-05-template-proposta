package br.com.zup.oragetalents.proposta.carteira;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class CarteiraRequest {

	@NotBlank
	@Email
	private String email;

	@NotBlank
	private String carteira;

	public CarteiraRequest(@NotBlank @Email String email, @NotBlank String carteira) {
		this.email = email;
		this.carteira = carteira;
	}

	@Deprecated
	public CarteiraRequest() {
	}

	public String getEmail() {
		return email;
	}

	public String getCarteira() {
		return carteira;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setCarteira(String carteira) {
		this.carteira = carteira;
	}

}
