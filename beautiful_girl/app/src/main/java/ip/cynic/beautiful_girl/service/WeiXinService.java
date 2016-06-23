package ip.cynic.beautiful_girl.service;

import ip.cynic.beautiful_girl.bean.json.WeiXinTopicJson;
import rx.Observable;

/**
 * Created by cynic on 2016/6/7.
 */
public interface WeiXinService {

    Observable<WeiXinTopicJson> getTopic(int page);

}
