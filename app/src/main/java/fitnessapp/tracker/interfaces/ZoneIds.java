package fitnessapp.tracker.interfaces;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;

public interface ZoneIds {
    TimeZone TIME_ZONE = TimeZone.getTimeZone("Europe/Berlin");
    SimpleDateFormat FORMATTER = new SimpleDateFormat("dd.MM.yyyy kk:mm", Locale.GERMANY);
}
