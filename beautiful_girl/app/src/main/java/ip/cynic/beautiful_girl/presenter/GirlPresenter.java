package ip.cynic.beautiful_girl.presenter;

import android.util.Log;

import java.util.List;

import ip.cynic.beautiful_girl.bean.GankGirl;
import ip.cynic.beautiful_girl.service.GirlService;
import ip.cynic.beautiful_girl.service.impl.GirlServiceImpl;
import ip.cynic.beautiful_girl.view.GirlView;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by cynic on 2016/6/2.
 */
public class GirlPresenter extends BasePresenter {

    private final static int NUMBER = 10;
    private static final String TAG = "GirlPresenter";

    private GirlView mGirlView;
    private GirlService mGirlService;

    private Observer<List<GankGirl>> mObserver = new Observer<List<GankGirl>>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            mGirlView.showFailError();
            Log.e(TAG, e.toString());
        }

        @Override
        public void onNext(List<GankGirl> gankGirls) {
            mGirlView.showSuccessPage(gankGirls);
        }
    };

    public GirlPresenter(GirlView girlView) {
        mGirlView = girlView;
        mGirlService = new GirlServiceImpl();
    }

    public void loadPage(int page) {
        mGirlView.showLoading();
        unsubscribe();
        mSubscription = mGirlService.getGankGils()
                .getGankGirls(NUMBER, page)
                .map(GirlServiceImpl.GankResultMapFunc.getInstance())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mObserver);

    }

    public void initData(int page) {
        loadPage(page);
        Log.i(TAG, "init data");
    }

}
