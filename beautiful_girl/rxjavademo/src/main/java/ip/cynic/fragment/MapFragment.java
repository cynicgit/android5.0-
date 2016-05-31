package ip.cynic.fragment;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ip.cynic.adapter.MapAdapter;
import ip.cynic.bean.BeautyGirl;
import ip.cynic.network.NetworkClient;
import ip.cynic.rxjavademo.R;
import ip.cynic.utils.GankResultMapFun;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by cynic on 2016/5/30.
 */
public class MapFragment extends BaseFragment {

    private final static int NUMBER = 10;

    private int page = 0;

    @Bind(R.id.tv_page_num)
    TextView mTvPageNum;
    @Bind(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @Bind(R.id.btn_previous)
    Button mBtnPrevious;

    Observer<List<BeautyGirl>> mObserver = new Observer<List<BeautyGirl>>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            mSwipeRefreshLayout.setRefreshing(false);
            Toast.makeText(getContext(), "加载失败", Toast.LENGTH_SHORT);
        }

        @Override
        public void onNext(List<BeautyGirl> beautyGirls) {
            mSwipeRefreshLayout.setRefreshing(false);
            mAdapter.setBeautyGirls(beautyGirls);
        }
    };
    private MapAdapter mAdapter;

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container) {
        View view = inflater.inflate(R.layout.map_rxjava_layout, container, false);
        ButterKnife.bind(this, view);

        RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mAdapter = new MapAdapter();
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(layoutManager);

        mSwipeRefreshLayout.setColorSchemeColors(Color.BLUE, Color.GREEN, Color.RED, Color.YELLOW);
        mSwipeRefreshLayout.setEnabled(false);

        return view;
    }

    @OnClick(R.id.btn_previous)
    void previous() {
        loadPage(page--);
        if (page == 1) {
            mBtnPrevious.setEnabled(false);
        }
    }

    @OnClick(R.id.btn_next)
    void next() {
        loadPage(page++);
        if (page > 1) {
            mBtnPrevious.setEnabled(true);
        }
    }

    private void loadPage(int page) {
        mSwipeRefreshLayout.setRefreshing(true);
        unsubscribe();
        mSubscription = NetworkClient.getGankApi()
                .getBeautyGirls(NUMBER, page)
                .map(GankResultMapFun.getInstance())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mObserver);
    }

}
