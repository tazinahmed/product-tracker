package playlagom.producttracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;

import playlagom.producttracker.auth.AuthLogin;
import playlagom.producttracker.auth.AuthSignUp;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        Thread thread = new Thread( new Runnable() {
            @Override
            public void run() {
                try {
                    Log.d("MainActivity", "=== try ====");
                    Thread.sleep(900);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    Log.d("MainActivity", "=== finally ====");
                    Intent intent = new Intent(MainActivity.this, AuthSignUp.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
        thread.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

//    public void productTracker(View view) {
//        Intent intent = new Intent(MainActivity.this, SearchActivity.class);
//        startActivity(intent);
//    }
}
