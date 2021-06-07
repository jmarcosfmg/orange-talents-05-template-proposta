package br.com.zup.oragetalents.proposta.proposta;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropostaRepository extends CrudRepository<Proposta, Long>{
	
	public boolean existsByDocumento(String documento);

	public List<Proposta> findAllByNumeroCartaoIsNull();
}
