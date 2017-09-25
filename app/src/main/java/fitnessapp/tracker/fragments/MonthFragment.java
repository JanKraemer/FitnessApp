package fitnessapp.tracker.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import fitnessapp.tracker.R;
import fitnessapp.tracker.adapters.ExerciseAdapter;
import fitnessapp.tracker.database.DatabaseHelper;
import fitnessapp.tracker.interfaces.OnItemClickListener;
import fitnessapp.tracker.models.Training;

public class MonthFragment extends Fragment {

    private static final String MONTH = "month";
    private ExerciseAdapter adapter;
    private DatabaseHelper databaseHelper;

    public MonthFragment( ) { }

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState ) {
        // Inflate the layout for this fragment
        View view = inflater.inflate( R.layout.content_week_fragment, container, false );
        initRecyclerView( view );
        callDatabaseExercises( );
        return view;
    }

    /**
     *
     * @return a new instance of this fragment
     */
    public static Fragment newInstance( ) {
        return new WeekFragment( );
    }

    private void initRecyclerView( View view ) {
        RecyclerView recyclerView = ( RecyclerView ) view.findViewById( R.id.recyclerview_main );
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager( getActivity( ) );
        recyclerView.setLayoutManager( linearLayoutManager );
        adapter = new ExerciseAdapter( getActivity( ), new OnItemClickListener( ) {
            @Override
            public void onClick( int position ) {
                //open a DetailView for the Training with all Exercises and so on.
            }
        } );
        recyclerView.setAdapter( adapter );
    }

    /**
     * Call all trainings of the current month and set them to the Adapter
     */
    private void callDatabaseExercises( ) {
        try {
            QueryBuilder< Training, Integer > queryBuilder = getHelper( )
                    .getTrainingDao( )
                    .queryBuilder( );

            List< Training > trainings = queryBuilder
                    .where( )
                    .ge( "date", getFirstDayOfMonth( ) )
                    .query( );

            adapter.addTrainingsToAdapter( trainings );
        } catch ( SQLException e ) {
            Log.e( "SQL Exception", "Error on loading the trainings.", e );
        }
    }

    /**
     * Calculate the date of the first day of this month as long value.
     *
     * @return long Value of the first day  of the current month
     */
    private long getFirstDayOfMonth( ) {
        Calendar cal = Calendar.getInstance( );
        cal.set( Calendar.DAY_OF_MONTH, 1 );
        cal.set( Calendar.HOUR_OF_DAY, 0 );
        cal.set( Calendar.MINUTE, 0 );
        return cal.getTimeInMillis( );
    }

    /**
     * Initialize the Databasehelper as Singleton for the work.
     *
     * @return databaseHelper
     */
    private DatabaseHelper getHelper( ) {
        if ( databaseHelper == null ) {
            databaseHelper =
                    OpenHelperManager.getHelper( getActivity( ), DatabaseHelper.class );
            databaseHelper.getWritableDatabase( );
        }
        return databaseHelper;
    }

    /**
     *  Release the actual DatabaseHelper
     */
    @Override
    public void onDestroy( ) {
        super.onDestroy( );
        if ( databaseHelper != null ) {
            OpenHelperManager.releaseHelper( );
            databaseHelper = null;
        }
    }
}
