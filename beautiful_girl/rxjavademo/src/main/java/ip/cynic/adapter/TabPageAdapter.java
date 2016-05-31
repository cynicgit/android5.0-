package ip.cynic.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by cynic on 2016/5/28.
 */
public class TabPageAdapter extends FragmentPagerAdapter{


    private String[] mTitles = new String[]{"基本","Map"};
    private List<Fragment> mFragments;

    public TabPageAdapter(FragmentManager fm , List<Fragment> fragments) {
        super(fm);
        this.mFragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}
