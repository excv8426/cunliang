package javasrc.component;

import java.io.IOException;
import java.util.Set;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import javasrc.entity.Discount;

public class NullDiscountSerializer extends JsonSerializer<Set<Discount>> {

	@Override
	public void serialize(Set<Discount> arg0, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
			throws IOException, JsonProcessingException {
		jsonGenerator.writeString("");
		
	}

}
