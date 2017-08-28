package fitnessapp.tracker.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import fitnessapp.tracker.fragments.WeekFragment;


public class MainPagerAdapter extends FragmentPagerAdapter {

    private Context context;
    private String[] titles;

    public MainPagerAdapter(Context context, FragmentManager fm, String[] titles) {
        super(fm);
        this.titles = titles;
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                return WeekFragment.newInstance();

            case 1:
                return new Fragment();

            default:
                return new Fragment();
        }
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return titles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if(position < titles.length)
            return titles[position];

        return null;
    }
}
