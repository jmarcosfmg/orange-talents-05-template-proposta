package br.com.zup.oragetalents.proposta.biometria;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

import br.com.zup.oragetalents.proposta.cartao.Cartao;

@Entity
public class Biometria {

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(columnDefinition = "BINARY(16)")
	private UUID id;

	@NotBlank
	private String fingerprint;

	@NotNull
	@ManyToOne
	private Cartao cartao;

	@NotNull
	private LocalDateTime criacao = LocalDateTime.now();

	public Biometria(@NotBlank String fingerprint, Cartao cartao) {
		this.fingerprint = fingerprint;
		this.cartao = cartao;
	}

	@Deprecated
	public Biometria() {
	}

	public UUID getId() {
		return id;
	}

	public String getFingerprint() {
		return fingerprint;
	}

	public Cartao getCartao() {
		return cartao;
	}

	public LocalDateTime getCriacao() {
		return this.criacao;
	}
}
