package hqtrung.hqt.doan3real.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import hqtrung.hqt.doan3real.Fragment.Fragment_DanhMuc;
import hqtrung.hqt.doan3real.Fragment.Fragment_Home;
import hqtrung.hqt.doan3real.Fragment.Fragment_User;
import hqtrung.hqt.doan3real.R;

public class MainActivity extends AppCompatActivity {

   public static BottomNavigationView bottomNavigationView;


    public static SharedPreferences preferences;
    public static SharedPreferences.Editor editor;

    String user = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        String key = intent.getStringExtra("key");

        preferences = getSharedPreferences("User", MODE_PRIVATE);
        editor = preferences.edit();

        if (key == null){
            key = MainActivity.preferences.getString("key", "");
        }else{
            editor.putString("key", key);
            editor.apply();
        }




        bottomNavigationView = (BottomNavigationView) findViewById(R.id.NavigationBottom);



        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.Framlayout, new Fragment_DanhMuc()).commit();

        }
        bottomNavigationView.setSelectedItemId(R.id.Find);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Fragment fragment = null;
                switch (menuItem.getItemId()){
                    case R.id.home:
                        fragment = new Fragment_Home();
                        break;
                    case R.id.Find:
                        fragment = new Fragment_DanhMuc();
                        break;
                    case R.id.More:
                        fragment = new Fragment_User();
                        break;
                }
                fragmentTransaction.replace(R.id.Framlayout, fragment);
                fragmentTransaction.commit();
                return true;
            }
        });
    }
}
