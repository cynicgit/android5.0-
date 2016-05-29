package ip.cynic.bean;

/**
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
