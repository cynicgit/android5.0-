package ip.cynic.beautiful_girl.fragment;

import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.Bind;
import butterknife.ButterKnife;
import ip.cynic.beautiful_girl.R;
import ip.cynic.beautiful_girl.adapter.GirlAdapter;
import ip.cynic.beautiful_girl.view.GirlView;

/**
 * Created by cynic on 2016/5/26.
 */
public class GirlFragment extends BaseFragment implements GirlView,SwipeRefreshLayout.OnRefreshListener {

    @Bind(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;
    private GirlAdapter mGirlAdapter;

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container) {
        View view = inflater.inflate(R.layout.girl_layout, container, false);
        ButterKnife.bind(this,view);

        mGirlAdapter = new GirlAdapter();
        RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mGirlAdapter);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        return view;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showFailError(View v) {

    }

    @Override
    public void showSuccessPage() {

    }


    /**
     * SwipeRefreshLayout 下拉刷新时回调
     */
    @Override
    public void onRefresh() {

    }
}
