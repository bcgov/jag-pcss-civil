package ca.bc.gov.open.pcss.Models.Serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class InstantSerializer extends JsonSerializer<Instant> {
    @Override
    public void serialize(Instant value, JsonGenerator gen, SerializerProvider serializers)
            throws IOException {
        String out =
                DateTimeFormatter.ofPattern("dd-MMM-yyyy")
                        .withZone(ZoneId.of("GMT-7"))
                        .withLocale(Locale.US)
                        .format(value);
        gen.writeString(out);
    }

    public static String convert(Instant value) {
        if (value == null) {
            return null;
        }

        return DateTimeFormatter.ofPattern("dd-MMM-yyyy")
                .withZone(ZoneId.of("GMT-7"))
                .withLocale(Locale.US)
                .format(value);
    }
}
