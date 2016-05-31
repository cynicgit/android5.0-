package ip.cynic.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import ip.cynic.bean.BeautyGirl;
import ip.cynic.bean.GankBeauty;
import rx.functions.Func1;

/**
 * Created by cynic on 2016/5/30.
 */
public class GankResultMapFun implements Func1<GankBeauty, List<BeautyGirl>> {

    private static GankResultMapFun INSTANCE = new GankResultMapFun();

    private GankResultMapFun() {}

    public static GankResultMapFun getInstance() {
        return INSTANCE;
    }

    @Override
    public List<BeautyGirl> call(GankBeauty gankBeauties) {
        List<GankBeauty.BeautyResult> results = gankBeauties.results;
        List<BeautyGirl> beautyGirls = new ArrayList<>(results.size());
        SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SS'Z'", Locale.CHINA);
        SimpleDateFormat output = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);

        for (GankBeauty.BeautyResult beautyResult : results) {
            String url = beautyResult.url;
            String des = null;
            try {
                Date date = input.parse(beautyResult.createdAt);
                des = output.format(date);
            } catch (ParseException e) {
                e.printStackTrace();
                des = "UnKnown Time";
            }
            BeautyGirl beautyGirl = new BeautyGirl(des,url);
            beautyGirls.add(beautyGirl);
        }

        return beautyGirls;
    }
}
