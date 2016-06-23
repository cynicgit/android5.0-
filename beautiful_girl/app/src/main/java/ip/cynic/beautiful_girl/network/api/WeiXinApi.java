package ip.cynic.beautiful_girl.network.api;

import ip.cynic.beautiful_girl.bean.json.WeiXinTopicJson;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by cynic on 2016/6/6.
 */
public interface WeiXinApi {

    @GET("wx/article/search")
    Observable<WeiXinTopicJson> getTopic(@Query("key") String key, @Query("cid") int cid,
                                         @Query("page") int page, @Query("size") int size);

}
