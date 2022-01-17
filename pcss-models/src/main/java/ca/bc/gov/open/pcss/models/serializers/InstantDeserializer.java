package ca.bc.gov.open.pcss.models.serializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Locale;
import java.util.TimeZone;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InstantDeserializer extends JsonDeserializer<Instant> {
    @Override
    public Instant deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException {
        try {
            var sfd = new SimpleDateFormat("dd-MMM-yy hh.mm.ss.SSSSSS a", Locale.US);
            sfd.setTimeZone(TimeZone.getTimeZone("UTC"));
            return sfd.parse(jsonParser.getText()).toInstant();
        } catch (ParseException e) {
            try {
                var sfd = new SimpleDateFormat("dd-MMM-yy", Locale.US);
                sfd.setTimeZone(TimeZone.getTimeZone("UTC"));
                return sfd.parse(jsonParser.getText()).toInstant();
            } catch (ParseException e2) {
                log.error(e2.getLocalizedMessage());
            }
        }
        return null;
    }
}
