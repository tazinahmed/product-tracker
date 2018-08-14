package playlagom.producttracker.cache;

import android.widget.ImageView;

/**
 * Created by User on 8/9/2018.
 */

public class Product {

    public ImageView ivShopImage;
    public String tvShopName;
    public String tvRatingValue;
    public String tvNumOfReview;
    public String tvLocation;
    public String tvPrice;
    public String tvServiceTime;

    public boolean order = false;

    String name;
    String url;

    public Product() {

    }

    public Product(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
