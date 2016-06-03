package ip.cynic.beautiful_girl.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import rx.Subscription;

/**
 * Created by cynic on 2016/6/2.
 */
public abstract class BaseFragment extends Fragment {


    private static final String TAG = "BaseFragment";
    protected Subscription mSubscription;


    @Override
    public void onDestroyView() {
        super.onDestroy();
        ButterKnife.unbind(this);
       // unsubscribe();
        Log.e(TAG,"onDestroyView");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return initView(inflater, container);
    }

    public abstract View initView(LayoutInflater inflater, @Nullable ViewGroup container);

    /**
     * 取消订阅
     */
    protected void unsubscribe() {

        if (mSubscription != null && !mSubscription.isUnsubscribed()) {
            Log.e(TAG,"unsubscribe");
            mSubscription.unsubscribe();
        }

    }

}
