package ip.cynic.android50;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    private List<String> mLists = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();
    }

    private void initView() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
    }

    public void initData() {
        setSupportActionBar(mToolbar);

        for (int i = 0; i < 20; i++) {
            mLists.add(i + "");
        }

        ListAdapter listAdapter = new ListAdapter(getApplicationContext(), mLists);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL, false);

        mRecyclerView.setAdapter(listAdapter);
        mRecyclerView.setLayoutManager(layoutManager);

        listAdapter.setOnItemClickListener(new ListAdapter.ItemClickListener() {
            @Override
            public void OnItemClick(View v, int position) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(getApplicationContext(), TabLayoutActivity.class));
                        break;

                    case 1:
                        startActivity(new Intent(getApplicationContext(), TablayoutViewPagerActivity.class));
                        break;

                    case 2:
                        startActivity(new Intent(getApplicationContext(), TabLayoutFragmentActivity.class));
                        break;

                    default:
                        break;
                }
            }
        });

    }

}
