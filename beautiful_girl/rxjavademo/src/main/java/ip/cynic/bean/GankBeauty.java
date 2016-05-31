package ip.cynic.bean;

import java.util.List;

/**
 *
 * {
 "error": false,
 "results": [
 {
 "_id": "574b8e6e421aa910b7ff04a5",
 "createdAt": "2016-05-30T08:50:54.535Z",
 "desc": "5.30",
 "publishedAt": "2016-05-30T08:56:14.314Z",
 "source": "chrome",
 "type": "\u798f\u5229",
 "url": "http://ww1.sinaimg.cn/large/610dc034jw1f4d4iji38kj20sg0izdl1.jpg",
 "used": true,
 "who": "daimajia"
 }
 ]
 }
 *
 * Created by cynic on 2016/5/30.
 */
public class GankBeauty {

    public boolean error;
    public List<BeautyResult> results;


    public class BeautyResult {
        public String createdAt;
        public String url;
    }

}
