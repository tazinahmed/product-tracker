package playlagom.producttracker;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ProductListActivity extends Activity {

    private RecyclerView rvProduct;
    private ProductAdapter productAdpater;

    List<Product> product = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        for (int i = 0; i <= 100; i++) {
            Product p = new Product();
            p.tvShopName = "Shop Name";
            p.tvRatingValue = "4." + i;
            p.tvNumOfReview = "" + i*123;
            p.tvLocation = "LOCATIOIN " + i;
            p.tvPrice = "" + i*10 + " TK";
            p.tvServiceTime = "" + i*3 + " MIN";

            product.add(p);
        }
        // Setup and Handover product to recyclerview
        rvProduct = findViewById(R.id.rvProductList);
        productAdpater = new ProductAdapter(ProductListActivity.this, product);
        rvProduct.setAdapter(productAdpater);
        rvProduct.setLayoutManager(new LinearLayoutManager(ProductListActivity.this));
    }

    public void price(View view) {
        // customized approach
        final Toast toast = Toast.makeText(getApplicationContext(), "Price Clicked!", Toast.LENGTH_SHORT);
        toast.show();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                toast.cancel();
            }
        }, 200);
    }

    public void distance(View view) {
        // customized approach
        final Toast toast = Toast.makeText(getApplicationContext(), "Distance Clicked!", Toast.LENGTH_SHORT);
        toast.show();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                toast.cancel();
            }
        }, 200);
    }

    public void rating(View view) {
        // customized approach
        final Toast toast = Toast.makeText(getApplicationContext(), "Rating Clicked!", Toast.LENGTH_SHORT);
        toast.show();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                toast.cancel();
            }
        }, 200);
    }

    public void addDistance(View view) {
        // customized approach
        final Toast toast = Toast.makeText(getApplicationContext(), "Distance added!", Toast.LENGTH_SHORT);
        toast.show();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                toast.cancel();
            }
        }, 300);
    }

    public void subDistance(View view) {
        // customized approach
        final Toast toast = Toast.makeText(getApplicationContext(), "Distance subtracted!", Toast.LENGTH_SHORT);
        toast.show();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                toast.cancel();
            }
        }, 300);
    }

    public void editUnit(View view) {
        // customized approach
        final Toast toast = Toast.makeText(getApplicationContext(), "Edit Unit", Toast.LENGTH_SHORT);
        toast.show();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                toast.cancel();
            }
        }, 300);
    }
}
