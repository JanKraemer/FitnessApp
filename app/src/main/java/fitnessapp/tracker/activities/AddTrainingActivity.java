package fitnessapp.tracker.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.os.SystemClock;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Spinner;

import java.util.ArrayList;

import fitnessapp.tracker.R;
import fitnessapp.tracker.adapters.SpinnerAdapter;
import fitnessapp.tracker.models.SpinnerItem;
import fitnessapp.tracker.models.Training;

public class AddTrainingActivity extends AppCompatActivity {

    private Spinner spinner;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_add_training );
        initActionbar( );
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

        if ( spinner != null ){
            spinner.setAdapter( adapter );
        }


    }

    private void saveTraining( ) {
        SpinnerItem item = (SpinnerItem ) spinner.getSelectedItem( );
    }

}
