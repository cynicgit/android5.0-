package ip.cynic.beautiful_girl.view;

import android.view.View;

/**
 * Created by cynic on 2016/6/2.
 */
public interface GirlView {

    void showLoading();

    void hideLoading();

    void showFailError(View v);

    void showSuccessPage();

}
