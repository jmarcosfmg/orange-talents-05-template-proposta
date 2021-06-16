package br.com.zup.oragetalents.proposta.external.cartoes.carteira;

import java.time.LocalDateTime;

public class Carteira {
	private String id;
	private String email;
	private LocalDateTime associadaEm;
	private String emissor;

	@Deprecated
	public Carteira() {
	}

	public String getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public LocalDateTime getAssociadaEm() {
		return associadaEm;
	}

	public String getEmissor() {
		return emissor;
	}

}
