package br.com.zup.oragetalents.proposta.external.financeira;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import br.com.zup.oragetalents.proposta.proposta.StatusProposta;

@Converter
public class StatusConverter
		implements AttributeConverter<StatusProposta, String> {

	public String convertToDatabaseColumn( StatusProposta value ) {
		if ( value == null ) {
			return null;
		}

		return value.getStatus();
	}

	public StatusProposta convertToEntityAttribute(String value) {
		if ( value == null ) {
			return null;
		}

		return StatusProposta.fromStatus( value );
	}
}