package playlagom.producttracker.adapter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import playlagom.producttracker.R;
import playlagom.producttracker.cache.Product;

public class ProductListActivity extends Activity {

    public static final String FB_DATABASE_PATH = "image";
    DatabaseReference databaseReference;
    List<Product> productList;
    ProgressDialog progressDialog;

    private RecyclerView rvProduct;
    private ProductAdapter productAdpater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        // show progress dialog during loading products
        productList = new ArrayList<>();
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait, loading images...");
        progressDialog.show();

        databaseReference = FirebaseDatabase.getInstance().getReference(FB_DATABASE_PATH);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                progressDialog.dismiss();

                if (dataSnapshot != null) {
                    // fetch products
                    for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                        // Need default constructor
                        Product product = snapshot.getValue(Product.class);
                        productList.add(product);
                    }

                    // Init adapter
                    // Setup and Handover product to recyclerview
                    rvProduct = findViewById(R.id.rvProductList);
                    productAdpater = new ProductAdapter(ProductListActivity.this, productList);
                    rvProduct.setAdapter(productAdpater);
                    rvProduct.setLayoutManager(new LinearLayoutManager(ProductListActivity.this));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                progressDialog.dismiss();
            }
        });
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
