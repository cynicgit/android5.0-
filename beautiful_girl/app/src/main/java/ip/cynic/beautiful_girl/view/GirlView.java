package ip.cynic.beautiful_girl.view;

import java.util.List;

import ip.cynic.beautiful_girl.bean.GankGirl;

/**
 * Created by cynic on 2016/6/2.
 */
public interface GirlView {

    void showLoading();

    void hideLoading();

    void showFailError();

    void showSuccessPage(List<GankGirl> datas);

}
