package fitnessapp.tracker.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import fitnessapp.tracker.R;
import fitnessapp.tracker.models.Training;

public class AddTrainingActivity extends AppCompatActivity {

    private boolean closeFlag = false;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_add_training );
        initActionbar( );
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

    private void saveTraining( ) {

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
                        finish();
                    }
                } )
                .setNegativeButton( R.string.cancel, new DialogInterface.OnClickListener( ) {
                    @Override
                    public void onClick( DialogInterface dialog, int which ) {
                        dialog.cancel();
                    }
                } )
                .create( );

        dialog.show( );
    }
}
