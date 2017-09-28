package fitnessapp.tracker.fragments;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.TimePicker;

import java.util.Calendar;

import fitnessapp.tracker.R;
import fitnessapp.tracker.interfaces.OnDateChangedListener;

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

    private OnDateChangedListener listener;

    @Override
    public void onAttach( Activity activity ) {
        super.onAttach( activity );
        listener = ( OnDateChangedListener ) activity;
    }

    @Override
    public Dialog onCreateDialog( Bundle savedInstanceState ) {
        // Use the current date as the default date in the picker

        long value = getArguments( ).getLong( getString( R.string.timeInMilis ) );
        final Calendar c = Calendar.getInstance( );
        c.setTimeInMillis( value );
        int hours = c.get( Calendar.HOUR_OF_DAY );
        int min = c.get( Calendar.MINUTE );

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog( getActivity( ), this, hours, min, true );
    }

    @Override
    public void onTimeSet( TimePicker view, int hourOfDay, int minute ) {
        listener.onDateChanged( createCalendar( hourOfDay, minute ) );
    }

    private Calendar createCalendar( int hoursOfDay, int minute ) {
        final Calendar calendar = Calendar.getInstance( );
        calendar.set( Calendar.HOUR_OF_DAY, hoursOfDay );
        calendar.set( Calendar.MINUTE, minute );
        return calendar;
    }

}
