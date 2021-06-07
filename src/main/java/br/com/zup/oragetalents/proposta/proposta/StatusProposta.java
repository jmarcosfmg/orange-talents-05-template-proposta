package br.com.zup.oragetalents.proposta.proposta;

public enum StatusProposta {
	
	COM_RESTRICAO("NAO_ELEGIVEL"),
	SEM_RESTRICAO("ELEGIVEL");
	
	private final String status;

	StatusProposta(String string) {
		this.status = string;
	}
	
    public static StatusProposta fromStatus(String status) {
        if ( status.toUpperCase().equals("NAO_ELEGIVEL")) {
            return COM_RESTRICAO;
        }
        if (status.toUpperCase().equals("ELEGIVEL")) {
            return SEM_RESTRICAO;
        }
        throw new UnsupportedOperationException(
            "The status " + status + " is not supported!"
        );
    }

    public String getStatus() {
        return status;
    }
}
