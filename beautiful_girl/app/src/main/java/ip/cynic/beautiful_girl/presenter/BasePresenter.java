package ip.cynic.beautiful_girl.presenter;

import android.util.Log;

import rx.Subscription;

/**
 * Created by cynic on 2016/6/3.
 */
public class BasePresenter {

    private static final String TAG = "BasePresenter";
    protected Subscription mSubscription;

    /**
     * 取消订阅
     */
    public void unsubscribe() {

        if (mSubscription != null && !mSubscription.isUnsubscribed()) {
            Log.e(TAG,"unsubscribe");
            mSubscription.unsubscribe();
        }

    }

}
