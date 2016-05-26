package ip.cynic.android50;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class TabLayoutActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private TabLayout mTabLayout;
    private RecyclerView mRecyclerView;
    private List<String> mLists = new ArrayList<>();

    private String[] mTabTitles = new String[]{"tab1", "tab2", "tab3"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mTabLayout = (TabLayout) findViewById(R.id.tab_layout);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        setSupportActionBar(mToolbar);
        initData();
    }

    public void initData() {
        setSupportActionBar(mToolbar);

        mTabLayout.addTab(mTabLayout.newTab().setText(mTabTitles[0]));
        mTabLayout.addTab(mTabLayout.newTab().setText(mTabTitles[1]));
        mTabLayout.addTab(mTabLayout.newTab().setText(mTabTitles[2]));

        for (int i = 0; i < 20; i++) {
            mLists.add(i + "");
        }

        ListAdapter listAdapter = new ListAdapter(getApplicationContext(),mLists);
        mRecyclerView.setAdapter(listAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(layoutManager);

    }


}
