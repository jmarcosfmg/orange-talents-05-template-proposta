package br.com.zup.oragetalents.proposta.cartao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

import br.com.zup.oragetalents.proposta.biometria.Biometria;
import br.com.zup.oragetalents.proposta.cartao.bloqueio.BloqueioCartao;
import br.com.zup.oragetalents.proposta.proposta.Proposta;

@Entity
public class Cartao {

	@Id
	@NotBlank
	private String id;

	@OneToOne
	private Proposta proposta;

	@OneToMany
	private List<Biometria> biometrias;
	
	@OneToOne
	private BloqueioCartao bloqueio;

	public Cartao(@NotBlank String id, Proposta proposta) {
		this.id = id;
		this.proposta = proposta;
	}

	@Deprecated
	public Cartao() {

	}
	
	public String getId() {
		return id;
	}

	public Proposta getProposta() {
		return proposta;
	}

	public List<Biometria> getBiometrias() {
		return biometrias;
	}

	public void bloqueiaCartao(BloqueioCartao bloqueio) {
		this.bloqueio = bloqueio;
	}
	
	public BloqueioCartao getBloqueio() {
		return this.bloqueio;
	}

	public void addBiometria(Biometria biometria) {
		if (this.biometrias == null) {
			this.biometrias = new ArrayList<Biometria>();
		}
		this.biometrias.add(biometria);
	}
}
