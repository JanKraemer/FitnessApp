package fitnessapp.tracker.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import fitnessapp.tracker.fragments.MonthFragment;
import fitnessapp.tracker.fragments.WeekFragment;


public class MainPagerAdapter extends FragmentPagerAdapter {

    private String[] titles;

    public MainPagerAdapter( FragmentManager fm, String[] titles ) {
        super( fm );
        this.titles = titles;
    }

    @Override
    public Fragment getItem( int position ) {

        switch ( position ) {
            case 0:
                return WeekFragment.newInstance( );

            case 1:
                return MonthFragment.newInstance( );

            default:
                return WeekFragment.newInstance( );
        }
    }

    @Override
    public int getCount( ) {
        // Show 3 total pages.
        return titles.length;
    }

    @Override
    public CharSequence getPageTitle( int position ) {
        if ( position < titles.length )
            return titles[ position ];

        return null;
    }
}
