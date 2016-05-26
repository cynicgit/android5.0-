package ip.cynic.beautiful_girl.base;

import android.app.Application;

/**
 * Created by cynic on 2016/5/25.
 */
public class BaseApplication extends Application{

    private BaseLifecycleHandler mLifecycleHandler;

    @Override
    public void onCreate() {
        mLifecycleHandler = new BaseLifecycleHandler();
        registerActivityLifecycleCallbacks(mLifecycleHandler);
        super.onCreate();
    }

    public static boolean isBackground() {
        return BaseLifecycleHandler.isBackGround();
    }
}
