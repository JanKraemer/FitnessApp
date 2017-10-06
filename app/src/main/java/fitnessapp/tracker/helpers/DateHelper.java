package fitnessapp.tracker.helpers;

import static fitnessapp.tracker.interfaces.ZoneIds.DATE_FORMAT;

import java.util.Calendar;

public class DateHelper {

    public static Calendar initCalendarDate() {
        Calendar calendarDate = Calendar.getInstance();
        calendarDate.set(Calendar.HOUR_OF_DAY, 8);
        calendarDate.set(Calendar.MINUTE, 0);
        return calendarDate;
    }

    public static boolean isDateYesterday(Calendar givenDate) {
        Calendar yesterday = getYesterdayAsDate();
        return DATE_FORMAT.format(yesterday.getTime()).equals(DATE_FORMAT.format(givenDate.getTime()));
    }

    private static Calendar getYesterdayAsDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        return calendar;
    }
}
