package ip.cynic.beautiful_girl.network.api;

import ip.cynic.beautiful_girl.bean.json.WenXinTopicJson;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by cynic on 2016/6/6.
 */
public interface WenXinApi {

    @GET("wx/article/search")
    Observable<WenXinTopicJson>  getTopic(@Query("key") String key,@Query("cid") String cid,@Query("page") String page,@Query("size") String size);

}
