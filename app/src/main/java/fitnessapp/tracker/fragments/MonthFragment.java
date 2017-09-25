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
import fitnessapp.tracker.interfaces.IOnItemClickListener;
import fitnessapp.tracker.models.Training;

public class MonthFragment extends Fragment{

    private static final String MONTH = "month";
    private ExerciseAdapter adapter;
    private DatabaseHelper databaseHelper;

    public MonthFragment() {
    }

    public static int getNumberOfWeek() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.WEEK_OF_YEAR);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.content_week_fragment, container, false);
        initRecyclerView(view);
        callDatabaseExercises();
        return view;
    }


    public static Fragment newInstance() {
        Bundle args = new Bundle();
        args.putInt(MONTH, getNumberOfWeek());
        WeekFragment fragment = new WeekFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private void initRecyclerView(View view) {
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview_main);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new ExerciseAdapter(getActivity(), new IOnItemClickListener() {
            @Override
            public void onClick(int position) {
                //open a DetailView for the Training with all Exercises and so on.
            }
        });
        recyclerView.setAdapter(adapter);
    }

    private void callDatabaseExercises() {
        try {
            QueryBuilder<Training, Integer> queryBuilder = getHelper().getTrainingDao().queryBuilder();
            List<Training> trainings = queryBuilder.where().ge("date", getFirstDayOfMonth()).query();
            adapter.addTrainingsToAdapter(trainings);
        } catch (SQLException e) {
            Log.e("SQL Exception", "Error on loading the trainings.", e);
        }
    }

    private long getFirstDayOfMonth() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        return cal.getTimeInMillis();
    }

    private DatabaseHelper getHelper() {
        if (databaseHelper == null) {
            databaseHelper =
                    OpenHelperManager.getHelper(getActivity(), DatabaseHelper.class);
            databaseHelper.getWritableDatabase();
        }
        return databaseHelper;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (databaseHelper != null) {
            OpenHelperManager.releaseHelper();
            databaseHelper = null;
        }
    }
}
