package ip.cynic.android50;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import ip.cynic.android50.fragment.PageFragment;

public class TabLayoutFragmentActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    private int[] mResIDs = new int[]{R.mipmap.a, R.mipmap.b, R.mipmap.c};

    private String[] mTabTitles = new String[]{"tab1", "tab2", "tab3"};

    List<Fragment> mFragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablayout_view_pager);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mTabLayout = (TabLayout) findViewById(R.id.tab_layout);
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        setSupportActionBar(mToolbar);

        setTabs();
    }

    private void setTabs() {

        for (int i = 0; i < 3; i++) {
            mFragments.add(PageFragment.newInstance(i));
        }
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        MyAdapter adapter = new MyAdapter(getSupportFragmentManager(), mFragments);
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);

    }


    class MyAdapter extends FragmentPagerAdapter {

        private List<Fragment> fragments;

        public MyAdapter(FragmentManager fm, List<Fragment> fragments) {
            super(fm);
            this.fragments = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTabTitles[position];
        }
    }


}
