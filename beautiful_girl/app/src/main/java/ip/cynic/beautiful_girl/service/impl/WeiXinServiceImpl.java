package ip.cynic.beautiful_girl.service.impl;

import java.util.ArrayList;
import java.util.List;

import ip.cynic.beautiful_girl.bean.WeiXinTopic;
import ip.cynic.beautiful_girl.bean.json.WeiXinTopicJson;
import ip.cynic.beautiful_girl.network.NetworkClient;
import ip.cynic.beautiful_girl.service.WeiXinService;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by cynic on 2016/6/7.
 */
public class WeiXinServiceImpl implements WeiXinService {

    private final static String APPKEY = "13942e403c7a0";
    private final static int SIZE = 20;
    private final static int CID = 13;


    @Override
    public Observable<WeiXinTopicJson> getTopic(int page) {
        return NetworkClient.getWenXinApi().getTopic(APPKEY, CID, page, SIZE);
    }



    public static class WeiXinResultFunc implements Func1<WeiXinTopicJson,List<WeiXinTopic>> {

        private static WeiXinResultFunc mWeiXinResultFunc = new WeiXinResultFunc();

        private WeiXinResultFunc(){}

        public static WeiXinResultFunc getInstance() {
            return mWeiXinResultFunc;
        }

        @Override
        public List<WeiXinTopic> call(WeiXinTopicJson weiXinTopicJson) {

            String msg = weiXinTopicJson.msg;
            if(!"success".equals(msg)) {
                return null;
            }

            WeiXinTopicJson.ResultBean result = weiXinTopicJson.result;
            List<WeiXinTopicJson.ResultBean.ListBean> list = result.list;
            List<WeiXinTopic> weiXinTopicList = new ArrayList<>(list.size());

            for (WeiXinTopicJson.ResultBean.ListBean bean : list) {
                weiXinTopicList.add(new WeiXinTopic(bean,result.curPage,result.total));
            }

            return weiXinTopicList;
        }
    }

}
