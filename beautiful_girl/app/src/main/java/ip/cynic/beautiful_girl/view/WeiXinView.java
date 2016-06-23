package ip.cynic.beautiful_girl.view;

import java.util.List;

import ip.cynic.beautiful_girl.bean.WeiXinTopic;

/**
 * Created by cynic on 2016/6/13.
 */
public interface WeiXinView {

    void showLoading();

    void hideLoading();

    void showFailError();

    void showSuccessPage(List<WeiXinTopic> datas);
}
