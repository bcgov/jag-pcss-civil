package ca.bc.gov.open.pcss.models.serializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.Locale;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InstantDeserializer extends JsonDeserializer<Instant> {
    @Override
    public Instant deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException {
        try {
            Date d =
                    new SimpleDateFormat("dd-MMM-yy hh.mm.ss.SSSSSS a", Locale.US)
                            .parse(jsonParser.getText());
            return d.toInstant();
        } catch (ParseException e) {
            log.error(e.getLocalizedMessage());
        }
        return null;
    }
}
