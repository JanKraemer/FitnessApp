package fitnessapp.tracker.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;
import fitnessapp.tracker.R;
import fitnessapp.tracker.interfaces.OnItemClickListener;

public class ExerciseViewHolder extends RecyclerView.ViewHolder {

    private OnItemClickListener clickListener;
    private CardView cardView;
    CircleImageView image;
    TextView day;

    public ExerciseViewHolder( View itemView, OnItemClickListener clickListener ) {
        super( itemView );
        this.clickListener = clickListener;
        this.cardView = ( CardView ) itemView.findViewById( R.id.training );
        image = ( CircleImageView ) itemView.findViewById( R.id.training_image );
        day = ( TextView ) itemView.findViewById( R.id.training_day );
    }

    void setClickListener( final int position ) {
        final View.OnClickListener onClickListener = new View.OnClickListener( ) {
            @Override
            public void onClick( View v ) {
                clickListener.onClick( position );
            }
        };
        cardView.setOnClickListener( onClickListener );
    }
}
