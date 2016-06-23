package ip.cynic.beautiful_girl.bean;

import ip.cynic.beautiful_girl.bean.json.WeiXinTopicJson;

/**
 * Created by cynic on 2016/6/6.
 */
public class WeiXinTopic {

    public String imgUrl;
    public String time;
    public String title;
    public String sourceUrl;

    public int curPage;
    public int total;


    public WeiXinTopic(String imgUrl, String time, String title, String sourceUrl, int curPage, int total) {
        this.imgUrl = imgUrl;
        this.time = time;
        this.title = title;
        this.sourceUrl = sourceUrl;
        this.curPage = curPage;
        this.total = total;
    }

    public WeiXinTopic(WeiXinTopicJson.ResultBean.ListBean bean, int curPage, int total) {
        this.imgUrl = bean.thumbnails;
        this.time = bean.pubTime;
        this.title = bean.title;
        this.sourceUrl = bean.sourceUrl;
        this.curPage = curPage;
        this.total = total;
    }


}
