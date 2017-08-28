package fitnessapp.tracker.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import fitnessapp.tracker.R;
import fitnessapp.tracker.interfaces.IOnItemClickListener;
import fitnessapp.tracker.models.Exercise;
import fitnessapp.tracker.models.ExerciseType;
import fitnessapp.tracker.models.Training;

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseViewHolder> {

    private Context context;
    private List<Training> trainings;
    private IOnItemClickListener clickListener;

    public ExerciseAdapter(Context context, IOnItemClickListener clickListener) {
        this(context,clickListener,new ArrayList<Training>());
    }

    public ExerciseAdapter(Context context, IOnItemClickListener clickListener, ArrayList<Training> trainings) {
        this.context = context;
        this.clickListener = clickListener;
        this.trainings = trainings;
    }

    @Override
    public ExerciseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.content_training,parent);
        return new ExerciseViewHolder(view,clickListener);
    }

    // Set the correct Picture
    @Override
    public void onBindViewHolder(ExerciseViewHolder holder, int position) {
        Training training = trainings.get(position);
        holder.title.setText(training.getTitle());
        holder.day.setText(convertDateToValue(training));
        setImageOnCardView(holder,training);
        holder.setClickListener(position);
    }

    private void setImageOnCardView(ExerciseViewHolder holder, Training training) {
        if(training.getType().equals(ExerciseType.AUSDAUER)){
            holder.image.setImageDrawable(context.getDrawable(R.drawable.ic_history_black_24dp));
        }else if (training.getType().equals(ExerciseType.BODYBUILDING)){
            holder.image.setImageDrawable(context.getDrawable(R.drawable.ic_history_black_24dp));
        }
    }

    private String convertDateToValue(Training training) {
        return "";
    }

    @Override
    public int getItemCount() {
        return trainings.size();
    }

    public void addTrainingsToAdapter(List<Training> trainings){
        this.trainings = trainings;
    }
}
