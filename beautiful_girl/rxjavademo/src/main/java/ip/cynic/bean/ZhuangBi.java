package ip.cynic.bean;

/**
 *
 * "id": 491,
 * "description": "110妖妖灵我劝你们别插手",
 * ......
 * "image_url": "http://zhuangbi.idagou.com/i/2016-01-03-0b56da7d2bffe0513d6fd93e1062030e.jpg",
 *
 *
 * Created by cynic on 2016/5/29.
 */
public class ZhuangBi {

    public String description;
    public String image_url;

    public ZhuangBi(String description, String image_url) {
        this.description = description;
        this.image_url = image_url;
    }

    @Override
    public String toString() {
        return "ZhuangBi{" +
                "description='" + description + '\'' +
                ", image_url='" + image_url + '\'' +
                '}';
    }
}
