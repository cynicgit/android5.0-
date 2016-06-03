package ip.cynic.beautiful_girl.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import ip.cynic.beautiful_girl.bean.GankGirl;
import ip.cynic.beautiful_girl.bean.json.GankIoJson;
import ip.cynic.beautiful_girl.network.NetworkClient;
import ip.cynic.beautiful_girl.network.api.GankApi;
import ip.cynic.beautiful_girl.service.GirlService;
import rx.functions.Func1;

/**
 * Created by cynic on 2016/6/2.
 */
public class GirlServiceImpl implements GirlService{

    @Override
    public GankApi getGankGils() {
        return NetworkClient.getGankApi();
    }



    public static class GankResultMapFunc implements Func1<GankIoJson,List<GankGirl>> {
        private  static GankResultMapFunc mGankResultMapFunc = new GankResultMapFunc();
        private GankResultMapFunc(){}

        public static GankResultMapFunc getInstance() {
            return mGankResultMapFunc;
        }

        @Override
        public List<GankGirl> call(GankIoJson gankIoJsons) {
            List<GankIoJson.BeautyResult> results = gankIoJsons.results;
            List<GankGirl> gankGirls = new ArrayList<>(results.size());

            SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SS'Z'", Locale.CHINA);
            SimpleDateFormat output = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);

            for (GankIoJson.BeautyResult result : results) {
                String imgUrl = result.url;
                String time = null;
                try {
                    Date date = input.parse(result.createdAt);
                    time = output.format(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                    time = "UnKnown Time";
                }

                gankGirls.add(new GankGirl(time,imgUrl));
            }

            return gankGirls;
        }
    }

}
