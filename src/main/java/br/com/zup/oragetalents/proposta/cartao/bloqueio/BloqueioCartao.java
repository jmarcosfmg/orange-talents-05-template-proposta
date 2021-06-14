package br.com.zup.oragetalents.proposta.cartao.bloqueio;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

import br.com.zup.oragetalents.proposta.cartao.Cartao;

@Entity
public class BloqueioCartao {
	
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(columnDefinition = "BINARY(16)")
	private UUID id;
	
	@NotNull
	private LocalDateTime instanteBloqueio = LocalDateTime.now();
	
	@OneToOne
	private Cartao cartao;
	
	@NotBlank
	private String ip;
	
	@NotBlank
	private String userAgent;
	
	public BloqueioCartao(Cartao cartao, String ip, String userAgent) {
		this.cartao = cartao;
		cartao.bloqueiaCartao(this);
		this.ip = ip;
		this.userAgent = userAgent;
	}
	
	@Deprecated
	public BloqueioCartao() {
	}
	
	public UUID getId() {
		return id;
	}

	public LocalDateTime getInstanteBloqueio() {
		return instanteBloqueio;
	}

	public Cartao getCartao() {
		return cartao;
	}

	public String getIp() {
		return ip;
	}

	public String getUserAgent() {
		return userAgent;
	}
}
