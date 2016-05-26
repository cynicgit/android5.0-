package ip.cynic.beautiful_girl.base;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

/**
 * 判断应用是否在后台
 * <p/>
 * Created by cynic on 2016/5/25.
 */
public class BaseLifecycleHandler implements Application.ActivityLifecycleCallbacks {

    private static int state = 0;

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

    }

    @Override
    public void onActivityStarted(Activity activity) {
        state++;
    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {
        if (state == 0)
            return;
        state--;
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }

    /**
     * state 大于0 应用还在后台运行
     *
     * @return
     */
    public static boolean isBackGround() {
        return state != 0;
    }
}
