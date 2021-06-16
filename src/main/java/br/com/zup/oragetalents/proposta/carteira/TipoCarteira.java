package br.com.zup.oragetalents.proposta.carteira;

public enum TipoCarteira {
	PAYPALL("PAYPALL"), 
	SAMSUNG_PAY("SAMSUNG_PAY");

	String value;
	
	TipoCarteira(String string) {
		this.value = string;
	}
	
	public static TipoCarteira getTipo(String string) {
		for (TipoCarteira en : TipoCarteira.values()) {
			if(string.equals(en.value)) {
				return en;
			}
		}
		throw new IllegalArgumentException("Tipo de carteira inválido!");
	}
}
