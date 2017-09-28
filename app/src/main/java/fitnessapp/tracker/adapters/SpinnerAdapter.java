package fitnessapp.tracker.adapters;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import fitnessapp.tracker.R;
import fitnessapp.tracker.interfaces.OnItemClickListener;
import fitnessapp.tracker.models.SpinnerItem;

public class SpinnerAdapter extends ArrayAdapter< SpinnerItem > {

    private ArrayList< SpinnerItem > items;

    public SpinnerAdapter( Activity activity, int textview, ArrayList< SpinnerItem > items ) {
        super( activity, R.layout.spinner_layout, textview, items );
        this.items = items;
    }

    @Override
    public View getDropDownView( int position, View convertView, @NonNull ViewGroup parent ) {
        return getView( position, convertView, parent );
    }

    @Override
    public SpinnerItem getItem( int position ) {
        return items.get( position );
    }

    @NonNull
    @Override
    public View getView( final int position, View v, @NonNull ViewGroup parent ) {
        View view = super.getView( position, v, parent );
        setValues( view, items.get( position ) );
        return view;
    }

    private void setValues( View view, SpinnerItem item ) {
        TextView lbl = ( TextView ) view.findViewById( R.id.spinner_text );
        lbl.setText( item.getText( ) );
        CircleImageView image = ( CircleImageView ) view.findViewById( R.id.spinner_image );
        image.setImageResource( item.getImageId( ) );
    }
}
