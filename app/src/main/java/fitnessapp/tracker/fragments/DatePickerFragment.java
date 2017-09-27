package fitnessapp.tracker.fragments;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import java.util.Calendar;

import fitnessapp.tracker.R;
import fitnessapp.tracker.interfaces.OnDateChangedListener;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    private OnDateChangedListener listener;

    @Override
    public void onAttach( Activity activity ) {
        super.onAttach( activity );
        listener = ( OnDateChangedListener ) activity;
    }

    @Override
    public Dialog onCreateDialog( Bundle savedInstanceState ) {
        // Use the current date as the default date in the picker

        long value = getArguments().getLong( getString( R.string.timeInMilis ) );
        final Calendar c = Calendar.getInstance();
        c.setTimeInMillis( value );
        int year = c.get( Calendar.YEAR );
        int month = c.get( Calendar.MONTH );
        int day = c.get( Calendar.DAY_OF_MONTH );

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog( getActivity( ), this, year, month, day );
    }

    public void onDateSet( DatePicker view, int year, int month, int day ) {
        listener.onDateChanged( createCalendar( year, month, day ) );
    }

    private Calendar createCalendar( int year, int month, int day ) {
        Calendar calendar = Calendar.getInstance( );
        calendar.set( Calendar.YEAR, year );
        calendar.set( Calendar.MONTH, month );
        calendar.set( Calendar.DAY_OF_MONTH, day );
        return calendar;
    }

}
