package ip.cynic.network.api;

import java.util.List;

import ip.cynic.bean.ZhuangBi;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by cynic on 2016/5/29.
 */
public interface ZhuangBiApi {

    /**
     * 返回被观察者对象
     * @param q
     * @return
     */
    @GET("search")
    Observable<List<ZhuangBi>> search(@Query("q")String q);

}
