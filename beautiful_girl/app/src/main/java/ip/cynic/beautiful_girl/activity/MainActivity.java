package ip.cynic.beautiful_girl.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import ip.cynic.beautiful_girl.R;
import ip.cynic.beautiful_girl.adapter.TabAdapter;
import ip.cynic.beautiful_girl.fragment.GirlFragment;
import ip.cynic.beautiful_girl.fragment.PageFragment;
import ip.cynic.beautiful_girl.fragment.WeiXinApiFragment;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.tab_layout)
    TabLayout mTabLayout;
    @Bind(R.id.tool_bar)
    Toolbar mToolbar;
    @Bind(R.id.view_pager)
    ViewPager mViewPager;
    private List<Fragment> mFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);

        setTabs();
    }

    private void setTabs() {
        mFragments = new ArrayList<>();
        mFragments.add(new GirlFragment());
        mFragments.add(new WeiXinApiFragment());
        for (int i = 2; i < 3; i++) {
            mFragments.add(PageFragment.newInstance(i));
        }
        TabAdapter tabAdapter = new TabAdapter(getSupportFragmentManager(), mFragments);
        mViewPager.setAdapter(tabAdapter);
        mTabLayout.setupWithViewPager(mViewPager);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
