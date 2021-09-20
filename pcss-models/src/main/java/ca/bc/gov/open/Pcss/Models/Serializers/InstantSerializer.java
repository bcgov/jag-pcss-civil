package ca.bc.gov.open.Pcss.Models.Serializers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.Locale;

public class InstantSerializer {

    public static String print(Instant xml) {
        String first = xml.toString();
        return first.substring(0, first.length() - 1);
    }

    public static Instant parse(String value) {
        try {
            Date d;
            try {
                d = new SimpleDateFormat("dd-MMM-yy hh.mm.ss.SSSSSS a", Locale.US).parse(value);
            } catch (ParseException ex) {
                d = new SimpleDateFormat("dd-MMM-yy", Locale.US).parse(value);
            }
            return d.toInstant();

        } catch (Exception ex) {
            return null;
        }
    }
}
