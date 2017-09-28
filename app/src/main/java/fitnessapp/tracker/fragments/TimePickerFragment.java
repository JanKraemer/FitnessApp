package fitnessapp.tracker.fragments;

import android.app.Activity;
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
    private Calendar calendar;

    @Override
    public void onAttach( Activity activity ) {
        super.onAttach( activity );
        listener = ( OnDateChangedListener ) activity;
    }

    @Override
    public Dialog onCreateDialog( Bundle savedInstanceState ) {
        // Use the current date as the default date in the picker

        long value = getArguments( ).getLong( getString( R.string.timeInMilis ) );
        calendar = Calendar.getInstance( );
        calendar.setTimeInMillis( value );
        int hours = calendar.get( Calendar.HOUR_OF_DAY );
        int min = calendar.get( Calendar.MINUTE );

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog( getActivity( ), this, hours, min, true );
    }

    @Override
    public void onTimeSet( TimePicker view, int hourOfDay, int minute ) {
        updateCalendar( hourOfDay, minute );
        listener.onDateChanged( calendar );
    }

    private void updateCalendar( int hoursOfDay, int minute ) {
        calendar.set( Calendar.HOUR_OF_DAY, hoursOfDay );
        calendar.set( Calendar.MINUTE, minute );
    }

}
