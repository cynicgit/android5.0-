package ip.cynic.beautiful_girl.fragment;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import ip.cynic.beautiful_girl.R;
import ip.cynic.beautiful_girl.adapter.WeiXinAdapter;
import ip.cynic.beautiful_girl.bean.WeiXinTopic;
import ip.cynic.beautiful_girl.presenter.WeiXinPresenter;
import ip.cynic.beautiful_girl.view.WeiXinView;

/**
 * Created by cynic on 2016/6/7.
 */
public class WeiXinApiFragment extends BaseFragment implements WeiXinView {

    private static final String TAG = "GirlFragment";
    @Bind(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private LinearLayoutManager mLayoutManager;
    WeiXinAdapter mWeiXinAdapter;
    private WeiXinPresenter mWeiXinPresenter;

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container) {
        View view = inflater.inflate(R.layout.girl_layout, container, false);
        ButterKnife.bind(this, view);
        mWeiXinPresenter = new WeiXinPresenter(this);
        setRecyclerView();
        setSwipeRefreshLayout();
        mWeiXinPresenter.subsrcibe(1);
        return view;
    }

    @Override
    public void setRecyclerView() {
        mWeiXinAdapter = new WeiXinAdapter(this);
        mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mWeiXinAdapter);
        // mRecyclerView.addOnScrollListener(getOnScrollListener());
    }

    @Override
    public void setSwipeRefreshLayout() {
        //刷新时颜色变化
        mSwipeRefreshLayout.setColorSchemeColors(Color.BLUE, Color.GREEN, Color.RED, Color.YELLOW);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        //强制测绘
        mSwipeRefreshLayout.measure(View.MEASURED_SIZE_MASK, View.MEASURED_HEIGHT_STATE_SHIFT);
    }


    @Override
    public void onRefresh() {

    }

    @Override
    public void showLoading() {
        mSwipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showFailError() {
        Snackbar.make(mRecyclerView,"加载失败",Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showSuccessPage(List<WeiXinTopic> datas) {
        mWeiXinAdapter.setLists(datas);
    }
}
