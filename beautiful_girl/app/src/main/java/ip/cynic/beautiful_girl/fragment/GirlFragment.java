package ip.cynic.beautiful_girl.fragment;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import ip.cynic.beautiful_girl.R;
import ip.cynic.beautiful_girl.adapter.GirlAdapter;
import ip.cynic.beautiful_girl.bean.GankGirl;
import ip.cynic.beautiful_girl.presenter.GirlPresenter;
import ip.cynic.beautiful_girl.view.GirlView;

/**
 * Created by cynic on 2016/5/26.
 */
public class GirlFragment extends BaseFragment implements GirlView, SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = "GirlFragment";
    @Bind(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;
    private GirlAdapter mGirlAdapter;

    private GirlPresenter mPresenter;

    private int page = 1;
    private boolean isRefresh;
    private boolean isLoadMore;

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container) {
        View view = inflater.inflate(R.layout.girl_layout, container, false);
        ButterKnife.bind(this, view);
        mPresenter = new GirlPresenter(this);
        mGirlAdapter = new GirlAdapter();
        RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mGirlAdapter);
        //刷新时颜色变化
        mSwipeRefreshLayout.setColorSchemeColors(Color.BLUE, Color.GREEN, Color.RED, Color.YELLOW);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        //第一次加载数据 可以缓存从本地获取
        mPresenter.initData(page);
        return view;
    }


    @Override
    public void showLoading() {
        isRefresh = true;
        mSwipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        isRefresh = false;
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showFailError() {
        hideLoading();
        Snackbar.make(mRecyclerView, "加载失败", Snackbar.LENGTH_LONG)
                .setAction("重试", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mPresenter.loadPage(page);
                    }
                }).show();
    }

    @Override
    public void showSuccessPage(List<GankGirl> datas) {
        hideLoading();
        mGirlAdapter.setBeautyGirls(datas);
        page++;
    }


    /**
     * SwipeRefreshLayout 下拉刷新时回调
     * <p/>
     * 刷新时获取最新的数据 所以page应为1
     */
    @Override
    public void onRefresh() {
        if (!isRefresh) {
            Log.e(TAG, "isRefresh");
        } else {
            mPresenter.loadPage(1);
        }

    }


    /**
     * 销毁时取消订阅
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.unsubscribe();
    }
}
