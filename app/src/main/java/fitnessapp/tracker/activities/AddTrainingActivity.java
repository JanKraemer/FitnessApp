package fitnessapp.tracker.activities;

import android.content.DialogInterface;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

import fitnessapp.tracker.R;
import fitnessapp.tracker.adapters.SpinnerAdapter;
import fitnessapp.tracker.fragments.DatePickerFragment;
import fitnessapp.tracker.interfaces.OnDateChangedListener;
import fitnessapp.tracker.interfaces.ZoneIds;
import fitnessapp.tracker.models.SpinnerItem;

import static fitnessapp.tracker.interfaces.ZoneIds.FORMATTER;

public class AddTrainingActivity extends AppCompatActivity implements OnDateChangedListener {

    private Spinner spinner;
    private TextView date;
    private TextView time;
    private Calendar calendarDate;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_add_training );
        initActionbar( );
        initCalendarDate( );
        initDatePicker( );
        initTimePicker( );
        initSpinner( );
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item ) {
        int id = item.getItemId( );

        switch ( id ) {
            case android.R.id.home:
                onBackPressed( );
                return true;

            case R.id.action_save:
                saveTraining( );
                return true;

            default:
                return super.onOptionsItemSelected( item );
        }
    }

    @Override
    public boolean onCreateOptionsMenu( Menu menu ) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater( ).inflate( R.menu.menu_addtraining, menu );
        return true;
    }

    @Override
    public void onBackPressed( ) {
        AlertDialog dialog = new AlertDialog.Builder( this )
                .setTitle( getString( R.string.discard_title ) )
                .setPositiveButton( R.string.discard, new DialogInterface.OnClickListener( ) {
                    @Override
                    public void onClick( DialogInterface dialog, int which ) {
                        finish( );
                    }
                } )
                .setNegativeButton( R.string.cancel, new DialogInterface.OnClickListener( ) {
                    @Override
                    public void onClick( DialogInterface dialog, int which ) {
                        dialog.cancel( );
                    }
                } )
                .create( );

        dialog.show( );
    }

    private void initActionbar( ) {
        ActionBar actionBar = getSupportActionBar( );
        if ( actionBar != null ) {
            actionBar.setTitle( R.string.newTraining );
            actionBar.setDisplayHomeAsUpEnabled( true );
            actionBar.setDisplayShowHomeEnabled( true );
            actionBar.setHomeAsUpIndicator(
                    ContextCompat.getDrawable( this, R.drawable.ic_close_white_24dp ) );
        }
    }

    private void initSpinner( ) {
        ArrayList< SpinnerItem > list = new ArrayList<>( );
        list.add( new SpinnerItem( getString( R.string.bodybuilding ), R.drawable.ic_bodybuilding ) );
        list.add( new SpinnerItem( getString( R.string.endurance ), R.drawable.ic_cardio ) );

        SpinnerAdapter adapter = new SpinnerAdapter( this, R.id.spinner_text, list );

        spinner = ( Spinner ) findViewById( R.id.type_spinner );

        if ( spinner != null ) {
            spinner.setAdapter( adapter );
        }
    }

    private void initDatePicker( ) {
        date = ( TextView ) findViewById( R.id.training_date );
        date.setOnClickListener( new View.OnClickListener( ) {
            @Override
            public void onClick( View v ) {
                showDatePickerDialog( v );
            }
        } );
    }

    private void initTimePicker( ) {
        time = ( TextView ) findViewById( R.id.training_time );
        time.setOnClickListener( new View.OnClickListener( ) {
            @Override
            public void onClick( View v ) {

            }
        } );
    }

    public void showDatePickerDialog( View v ) {
        Bundle bundle = new Bundle();
        bundle.putLong( getString( R.string.timeInMilis ), calendarDate.getTimeInMillis());
        DialogFragment newFragment = new DatePickerFragment( );
        newFragment.setArguments( bundle );
        newFragment.show( getSupportFragmentManager( ), "datePicker" );
    }


    private void saveTraining( ) {
        SpinnerItem item = ( SpinnerItem ) spinner.getSelectedItem( );
    }

    @Override
    public void onDateChanged( Calendar givenDate ) {
        if ( DateUtils.isToday( givenDate.getTimeInMillis( ) ) ) {
            date.setText( getString( R.string.today ) );
        } else if ( isDateYesterday( givenDate ) ) {
            date.setText( getString( R.string.yesterday ) );
        } else {
            date.setText( FORMATTER.format( givenDate.getTime( ) ) );
        }
        calendarDate = givenDate;
    }

    private boolean isDateYesterday( Calendar givenDate ) {
        Calendar yesterday = getYesterdayAsDate( );
        String a = FORMATTER.format( yesterday.getTime() );
        String b = FORMATTER.format( givenDate.getTime() );
        return FORMATTER.format( yesterday.getTime() ).equals( FORMATTER.format( givenDate.getTime(  ) ) );
    }

    private void initCalendarDate( ) {
        calendarDate = Calendar.getInstance();
        calendarDate.set( Calendar.HOUR, 8 );
        calendarDate.set( Calendar.MINUTE, 0 );
    }

    public Calendar getYesterdayAsDate( ) {
        Calendar calendar = Calendar.getInstance( );
        calendar.add( Calendar.DAY_OF_YEAR, -1 );
        return calendar;
    }
}
