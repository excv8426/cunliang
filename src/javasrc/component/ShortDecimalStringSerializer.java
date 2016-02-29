package javasrc.component;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class ShortDecimalStringSerializer extends JsonSerializer<String> {

	@Override
	public void serialize(String string, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
			throws IOException, JsonProcessingException {
		StringBuilder write=new StringBuilder();
		char c;
		if (!string.trim().equals("")) {
			try {
				BigDecimal bigDecimal=new BigDecimal(string);
				write.append(bigDecimal.setScale(2, RoundingMode.HALF_UP).toPlainString());
				for (int i = write.length()-1; i >0; i--) {
					c=write.charAt(i);
					if (c=='0') {
						write.deleteCharAt(i);
					} else if (c=='.') {
						write.deleteCharAt(i);
						break;
					} else {
						break;
					}
				}
			} catch (NumberFormatException e) {
				write.append(string);
				e.printStackTrace();
			}

		}
		jsonGenerator.writeString(write.toString());
	}

}
