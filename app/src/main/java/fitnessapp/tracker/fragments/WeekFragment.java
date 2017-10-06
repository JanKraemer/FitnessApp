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
import fitnessapp.tracker.R;
import fitnessapp.tracker.adapters.ExerciseAdapter;
import fitnessapp.tracker.database.DatabaseHelper;
import fitnessapp.tracker.interfaces.OnItemClickListener;
import fitnessapp.tracker.models.Training;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

public class WeekFragment extends Fragment {

    private ExerciseAdapter adapter;

    private DatabaseHelper databaseHelper;

    private LinearLayoutManager linearLayoutManager;

    private RecyclerView recyclerView;

    /**
     * Generate a new Fragment from this class with week as argument
     *
     * @return Fragment of this class
     */
    public static Fragment newInstance() {
        return new WeekFragment();
    }

    public WeekFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.content_week_fragment, container, false);
        initRecyclerView(view);
        callDatabaseExercises();
        return view;
    }

    /**
     * Release the actual DatabaseHelper
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (databaseHelper != null) {
            OpenHelperManager.releaseHelper();
            databaseHelper = null;
        }
    }

    /**
     * Call all trainings of the current week and set them to the Adapter
     */
    private void callDatabaseExercises() {
        try {
            QueryBuilder<Training, Integer> queryBuilder = getHelper()
                    .getTrainingDao()
                    .queryBuilder();

            List<Training> trainings = queryBuilder
                    .where()
                    .ge("date", getMondayMorning())
                    .query();

            adapter.addTrainingsToAdapter(trainings);
        } catch (SQLException e) {
            Log.e("SQL Exception", "Error on loading the trainings.", e);
        }
    }

    /**
     * Initialize the Databasehelper as Singleton for the work.
     *
     * @return databaseHelper
     */
    private DatabaseHelper getHelper() {
        if (databaseHelper == null) {
            databaseHelper =
                    OpenHelperManager.getHelper(getActivity(), DatabaseHelper.class);
            databaseHelper.getWritableDatabase();
        }
        return databaseHelper;
    }

    /**
     * Calculate the date of monday of this week as long value.
     *
     * @return long Value of the monday of the current week
     */
    private long getMondayMorning() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        return cal.getTimeInMillis();
    }

    private void initRecyclerView(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview_main);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new ExerciseAdapter(getActivity(), new OnItemClickListener() {
            @Override
            public void onClick(int position) {
                //open a DetailView for the Training with all Exercises and so on.
            }
        });
        recyclerView.setAdapter(adapter);
    }

}
