package fitnessapp.tracker.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;
import fitnessapp.tracker.R;
import fitnessapp.tracker.interfaces.IOnItemClickListener;

public class ExerciseViewHolder extends RecyclerView.ViewHolder {

    private IOnItemClickListener clickListener;
    private CardView cardView;
    CircleImageView image;
    TextView title;
    TextView day;

    public ExerciseViewHolder(View itemView, IOnItemClickListener clickListener) {
        super(itemView);
        this.clickListener = clickListener;
        this.cardView = (CardView) itemView.findViewById(R.id.training);
        image = (CircleImageView) itemView.findViewById(R.id.training_image);
        title = (TextView) itemView.findViewById(R.id.training_title);
        day = (TextView) itemView.findViewById(R.id.training_day);
    }

    void setClickListener(final  int position){
        final View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onClick(position);
            }
        };
        cardView.setOnClickListener(onClickListener);
    }
}
