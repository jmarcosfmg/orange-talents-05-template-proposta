package br.com.zup.oragetalents.proposta.cartao.bloqueio;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BloqueioCartaoRepository extends CrudRepository<BloqueioCartao, UUID>{
	
	List<BloqueioCartao> findAllByCartaoBloqueadoIsFalse();
}
