package br.com.zup.oragetalents.proposta.biometria;

import java.util.Base64;

import javax.validation.constraints.NotBlank;

import br.com.zup.oragetalents.proposta.cartao.Cartao;

public class BiometriaRequest {

	@NotBlank
	private String biometria;

	public BiometriaRequest(String biometria) {
		this.biometria = biometria;
	}
	
	@Deprecated
	public BiometriaRequest() {
	}

	public String getBiometria() {
		return biometria;
	}

	public boolean isValid() {
		try {
			Base64.getDecoder().decode(biometria.toString());
		} catch (IllegalArgumentException e) {
			return false;
		}
		return true;
	}
	
	public Biometria toModel(Cartao cartao) {
		return new Biometria(this.biometria, cartao);
	}
}
