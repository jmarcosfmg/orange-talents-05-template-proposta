package br.com.zup.oragetalents.proposta.viagem;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import br.com.zup.oragetalents.proposta.cartao.Cartao;

@Entity
public class AvisoViagem {

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(columnDefinition = "BINARY(16)")
	private UUID id;

	@NotNull
	@ManyToOne
	private Cartao cartao;

	@NotBlank
	private String ipCliente;

	@NotBlank
	private String userAgent;

	@NotBlank
	private String destino;

	@NotNull
	@Future
	@DateTimeFormat(iso = ISO.DATE)
	private Date termino;

	@NotNull
	private LocalDateTime instanteAviso = LocalDateTime.now();

	public AvisoViagem(@NotNull Cartao cartao, @NotBlank String ipCliente, @NotBlank String userAgent,
			@NotBlank String destino, @NotNull @Future Date termino) {
		this.cartao = cartao;
		this.ipCliente = ipCliente;
		this.userAgent = userAgent;
		this.destino = destino;
		this.termino = termino;
	}

	public UUID getId() {
		return id;
	}

	public Cartao getCartao() {
		return cartao;
	}

	public String getIpCliente() {
		return ipCliente;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public String getDestino() {
		return destino;
	}

	public Date getTermino() {
		return termino;
	}

	public LocalDateTime getInstanteAviso() {
		return instanteAviso;
	}
}
