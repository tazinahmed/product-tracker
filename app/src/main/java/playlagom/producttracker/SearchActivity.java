package playlagom.producttracker;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    private static final String TAG = "SearchActivity";
    SearchView searchView;
    DatabaseReference databaseReference;
    ArrayList<String> retrieveItems = new ArrayList<>();
    private static boolean matched;

    public static String searchQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_search);

        databaseReference = FirebaseDatabase.getInstance().getReference (getString(R.string.searchRef));
        final ListView listView = findViewById(R.id.lvSimpleList);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String itemName = (String) listView.getItemAtPosition(position);

                searchView.setQuery(itemName, false);
                // START new activity
                Intent intent = new Intent(SearchActivity.this, SearchResultActivity.class)
                        .putExtra("queryKey", itemName);
                startActivity(intent);
                retrieveItems.clear();
                finish();
            }
        });

        searchView = findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String s) {
                searchQuery = s;
                // customized approach
//                final Toast toast = Toast.makeText(getApplicationContext(), "Searching results for " + s, Toast.LENGTH_SHORT);
//                toast.show();
//
//                Handler handler = new Handler();
//                handler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        toast.cancel();
//                    }
//                }, 500);

                Intent intent = new Intent(SearchActivity.this, SearchResultActivity.class)
                        .putExtra("queryKey", s);
                startActivity(intent);
                retrieveItems.clear();
                return false;
            }

            @Override
            public boolean onQueryTextChange(final String queryText) {
                // built-in approach
                // Toast.makeText(getApplicationContext(), "text change: " + s, Toast.LENGTH_SHORT).show();

                // customized approach
                final Toast toast = Toast.makeText(getApplicationContext(), "Searching results for " + queryText, Toast.LENGTH_SHORT);
                toast.show();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        toast.cancel();
                    }
                }, 200);

                // todo: graphql
                // https://graphql.org/
                //  Ask for what you want
                //  Get predictable results

                // todo: Complex data sort, filter, index, performance
                // https://firebase.google.com/docs/database/android/lists-of-data
                // index: https://firebase.google.com/docs/database/security/indexing-data

                // todo: firestore
                // https://firebase.google.com/docs/firestore/
                // Key capabilities
                //   Flexibility
                //   Expressive querying
                //   Realtime updates
                //   Offline support
                //   Designed to scale

                // Expressive querying: multiple, chained filters and combine filtering and sorting, indexed, query performance
                // In Cloud Firestore, you can use queries to retrieve individual, specific documents or to retrieve all the documents in a collection that match your query parameters. Your queries can include multiple, chained filters and combine filtering and sorting. They're also indexed by default, so query performance is proportional to the size of your result set, not your data set.

                // https://stackoverflow.com/questions/46502281/how-to-use-searchview-toolbar-to-filter-database-firebase
                // https://stackoverflow.com/questions/39024702/firebase-querying-data
                // databaseReference.orderByChild("barName").startAt(searchText.toUpperCase()).endAt(searchText + "\uf8ff"))
                // Query query = databaseReference.orderByChild("1").equalTo(s);
                // lexicographically = https://firebase.google.com/docs/database/admin/retrieve-data

                // GREAT SUPPORT: https://stackoverflow.com/questions/38618953/how-to-do-a-simple-search-in-string-in-firebase-database
                Query query = databaseReference.orderByChild("name").startAt("" + queryText).endAt(queryText+"\uf8ff");
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot != null) {
                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                Log.d(TAG, "onQueryTextChange: specific_data = " + snapshot.child("name").getValue().toString());
                                String keyValue = snapshot.child("name").getValue().toString();
                                for (int i = 0; i < retrieveItems.size(); i++) {
                                    if (retrieveItems.get(i).equals(keyValue)) {
                                        matched = true;
                                    }
                                }
                                if (!matched) {
                                    retrieveItems.add(keyValue);
                                }
                                matched = false;
                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                ArrayAdapter adapter = new ArrayAdapter(SearchActivity.this,  android.R.layout.simple_list_item_1, retrieveItems);
                listView.setAdapter(adapter);
                adapter.getFilter().filter(queryText);
                return true;
            }
        });
    }
}