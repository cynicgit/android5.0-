package ip.cynic.android50;

import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class TablayoutViewPagerActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    private int[] mResIDs = new int[]{R.mipmap.a, R.mipmap.b, R.mipmap.c};

    private String[] mTabTitles = new String[]{"tab1", "tab2", "tab3"};

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
        mTabLayout.addTab(mTabLayout.newTab().setText(mTabTitles[0]));
        mTabLayout.addTab(mTabLayout.newTab().setText(mTabTitles[1]));
        mTabLayout.addTab(mTabLayout.newTab().setText(mTabTitles[2]));

        MyAdapter adapter = new MyAdapter();
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
       // mTabLayout.setTabsFromPagerAdapter(adapter);

    }


    class MyAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return mResIDs.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

//        @Override
//        public void destroyItem(View container, int position, Object object) {
//            ((ViewPager)container).removeView((View) object);
//        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = new ImageView(getApplicationContext());
            imageView.setImageResource(mResIDs[position]);
            container.addView(imageView);
            return imageView;
        }


        @Override
        public CharSequence getPageTitle(int position) {
            return mTabTitles[position];
        }
    }


}
