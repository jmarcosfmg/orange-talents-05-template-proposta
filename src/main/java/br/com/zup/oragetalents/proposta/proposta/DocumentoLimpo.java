package br.com.zup.oragetalents.proposta.proposta;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class DocumentoLimpo {
	private String documento;

	public DocumentoLimpo(String documento) {
		this.documento = documento;
	}

	public String getDocumento() {
		return documento;
	}

	public String cifraDocumento() {
		BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
		return bCrypt.encode(this.documento);
	}
}
