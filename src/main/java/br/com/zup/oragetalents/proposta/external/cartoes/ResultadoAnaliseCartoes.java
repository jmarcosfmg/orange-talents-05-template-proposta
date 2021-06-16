package br.com.zup.oragetalents.proposta.external.cartoes;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import br.com.zup.oragetalents.proposta.external.cartoes.aviso.Aviso;
import br.com.zup.oragetalents.proposta.external.cartoes.bloqueio.Bloqueio;
import br.com.zup.oragetalents.proposta.external.cartoes.carteira.Carteira;

public class ResultadoAnaliseCartoes {

	private String id;
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private LocalDateTime emitidoEm;
	private String titular;
	private List<Bloqueio> bloqueios;
	private List<Aviso> avisos;
	private List<Carteira> carteiras;
	private List<Parcela> parcelas;
	private Double limite;
	private Renegociacao renegociacao;
	private Vencimento vencimento;
	private String idProposta;

	public ResultadoAnaliseCartoes(String id, LocalDateTime emitidoEm, String titular, List<Bloqueio> bloqueios,
			List<Aviso> avisos, List<Carteira> carteiras, List<Parcela> parcelas, Double limite,
			Renegociacao renegociacao, Vencimento vencimento, String idProposta) {
		this.id = id;
		this.emitidoEm = emitidoEm;
		this.titular = titular;
		this.bloqueios = bloqueios;
		this.avisos = avisos;
		this.carteiras = carteiras;
		this.parcelas = parcelas;
		this.limite = limite;
		this.renegociacao = renegociacao;
		this.vencimento = vencimento;
		this.idProposta = idProposta;
	}

	@Deprecated
	public ResultadoAnaliseCartoes() {

	}

	public List<Aviso> getAvisos() {
		return avisos;
	}

	public List<Bloqueio> getBloqueios() {
		return bloqueios;
	}

	public List<Carteira> getCarteiras() {
		return carteiras;
	}

	public LocalDateTime getEmitidoEm() {
		return emitidoEm;
	}

	public String getId() {
		return id;
	}

	public String getIdProposta() {
		return idProposta;
	}

	public Double getLimite() {
		return limite;
	}

	public List<Parcela> getParcelas() {
		return parcelas;
	}

	public Renegociacao getRenegociacao() {
		return renegociacao;
	}

	public String getTitular() {
		return titular;
	}

	public Vencimento getVencimento() {
		return vencimento;
	}

}
