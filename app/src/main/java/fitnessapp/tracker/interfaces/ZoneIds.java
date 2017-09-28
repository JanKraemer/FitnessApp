package fitnessapp.tracker.interfaces;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;

public interface ZoneIds {
    TimeZone TIME_ZONE = TimeZone.getTimeZone( "Europe/Berlin" );
    SimpleDateFormat DATE_FORMAT = new SimpleDateFormat( "dd.MM.yyyy", Locale.GERMANY );
    SimpleDateFormat TIME_FORMAT = new SimpleDateFormat( "kk:mm", Locale.GERMANY );
}
