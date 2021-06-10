package br.com.zup.oragetalents.proposta.cartao;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.GenericGenerator;

import br.com.zup.oragetalents.proposta.biometria.Biometria;
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

	// Não deve ser usado, só está aqui por causa do Hibernate
	@Deprecated
	public void setBiometrias(List<Biometria> biometrias) {
		this.biometrias = biometrias;
	}

	public void addBiometria(Biometria biometria) {
		if (this.biometrias == null) {
			this.biometrias = new ArrayList<Biometria>();
		}
		this.biometrias.add(biometria);
	}
}
