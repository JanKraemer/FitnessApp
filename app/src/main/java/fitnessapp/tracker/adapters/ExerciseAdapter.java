package fitnessapp.tracker.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import fitnessapp.tracker.R;
import fitnessapp.tracker.interfaces.IOnItemClickListener;
import fitnessapp.tracker.models.TrainingsType;
import fitnessapp.tracker.models.Training;

import static fitnessapp.tracker.interfaces.ZoneIds.FORMATTER;

public class ExerciseAdapter extends RecyclerView.Adapter< ExerciseViewHolder > {

    private Context context;
    private List< Training > trainings;
    private IOnItemClickListener clickListener;

    public ExerciseAdapter( Context context, IOnItemClickListener clickListener ) {
        this( context, clickListener, new ArrayList< Training >( ) );
    }

    private ExerciseAdapter( Context context, IOnItemClickListener clickListener, ArrayList< Training > trainings ) {
        this.context = context;
        this.clickListener = clickListener;
        this.trainings = trainings;
    }

    @Override
    public ExerciseViewHolder onCreateViewHolder( ViewGroup parent, int viewType ) {
        View view = LayoutInflater.from( context ).inflate( R.layout.content_training, parent );
        return new ExerciseViewHolder( view, clickListener );
    }

    @Override
    public void onBindViewHolder( ExerciseViewHolder holder, int position ) {
        Training training = trainings.get( position );
        holder.title.setText( training.getTitle( ) );
        holder.day.setText( getTrainingDay( training ) );
        setImageOnCardView( holder, training );
        holder.setClickListener( position );
    }

    private void setImageOnCardView( ExerciseViewHolder holder, Training training ) {
        if ( training.getType( ).equals( TrainingsType.ENDURANCE ) ) {
            holder.image.setImageDrawable( context.getDrawable( R.drawable.ic_cardio ) );
        } else if ( training.getType( ).equals( TrainingsType.BODYBUILDING ) ) {
            holder.image.setImageDrawable( context.getDrawable( R.drawable.ic_bodybuilding ) );
        }
    }

    @Override
    public int getItemCount( ) {
        return trainings.size( );
    }

    public void addTrainingsToAdapter( List< Training > trainings ) {
        this.trainings = trainings;
    }

    private String getTrainingDay( Training training ) {
        return FORMATTER.format( training.getDate( ) );
    }

}
