package ip.cynic.fragment;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import ip.cynic.adapter.GridAdapter;
import ip.cynic.bean.ZhuangBi;
import ip.cynic.network.NetworkClient;
import ip.cynic.rxjavademo.R;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by cynic on 2016/5/28.
 */
public class BasicFragment extends BaseFragment {

    private static final String TAG = "BasicFragment";

    @Bind(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;
    private GridAdapter mGridAdapter;


    Observer<List<ZhuangBi>> mSubscriber = new Observer<List<ZhuangBi>>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            Toast.makeText(getContext(), "error:" + e.toString(), Toast.LENGTH_SHORT).show();
            mSwipeRefreshLayout.setRefreshing(false);
        }

        @Override
        public void onNext(List<ZhuangBi> zhuangBis) {
            //设置数据 并刷新
            mGridAdapter.setZhuangBis(zhuangBis);
            mSwipeRefreshLayout.setRefreshing(false);
        }
    };

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container) {
        View view = inflater.inflate(R.layout.basic_rxjava_layout, container, false);
        ButterKnife.bind(this, view);

        mGridAdapter = new GridAdapter(getContext(), null);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        mRecyclerView.setAdapter(mGridAdapter);
        mRecyclerView.setLayoutManager(layoutManager);

        mSwipeRefreshLayout.setColorSchemeColors(Color.BLUE, Color.GREEN, Color.RED, Color.YELLOW);
        //默认禁止下拉
        mSwipeRefreshLayout.setEnabled(false);
        return view;

    }

    @OnCheckedChanged({R.id.radio1, R.id.radio2, R.id.radio3, R.id.radio4})
    void onTagChecked(RadioButton searchRb, boolean checked) {

        if (checked) {
            //取消上次的订阅
            unsubscribe();
            //将数据清空
            mGridAdapter.setZhuangBis(null);
            mSwipeRefreshLayout.setRefreshing(true);
            search(searchRb.getText().toString());
        }

    }

    private void search(String key) {

        try{
            Log.d(TAG, key);
            mSubscription = NetworkClient.getZhuangBiApi()
                    .search(key)
                    .subscribeOn(Schedulers.io()) //在io线程获取数据
                    .observeOn(AndroidSchedulers.mainThread()) //在主线程操作
                    .subscribe(mSubscriber);
        }catch (Throwable e) {
            e.printStackTrace();
        }


    }


}
