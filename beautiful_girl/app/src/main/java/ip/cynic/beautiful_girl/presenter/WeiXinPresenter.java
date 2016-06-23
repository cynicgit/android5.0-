package ip.cynic.beautiful_girl.presenter;

import java.util.List;

import ip.cynic.beautiful_girl.bean.WeiXinTopic;
import ip.cynic.beautiful_girl.service.WeiXinService;
import ip.cynic.beautiful_girl.service.impl.WeiXinServiceImpl;
import ip.cynic.beautiful_girl.view.WeiXinView;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by cynic on 2016/6/13.
 */
public class WeiXinPresenter extends BasePresenter {

    private WeiXinView mWeiXinView;
    private WeiXinService mWeiXinService;

    private Observer<List<WeiXinTopic>> mObservable = new Observer<List<WeiXinTopic>>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            mWeiXinView.showFailError();
        }

        @Override
        public void onNext(List<WeiXinTopic> weiXinTopics) {
            mWeiXinView.showSuccessPage(weiXinTopics);
        }
    };


    public WeiXinPresenter(WeiXinView weiXinView) {
        mWeiXinView = weiXinView;
        mWeiXinService = new WeiXinServiceImpl();
    }

    public void subsrcibe(int page) {
        unsubscribe();
        mSubscription = mWeiXinService.getTopic(page)
                .map(WeiXinServiceImpl.WeiXinResultFunc.getInstance())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mObservable);
    }

}
