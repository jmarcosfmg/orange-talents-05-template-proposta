package br.com.zup.oragetalents.proposta.proposta;

public enum StatusProposta {
	
	COM_RESTRICAO("NAO_ELEGIVEL"),
	SEM_RESTRICAO("ELEGIVEL");
	
	private final String status;

	StatusProposta(String string) {
		this.status = string;
	}
	
	@Override
    public String toString() {
        return this.status;
    }
}
