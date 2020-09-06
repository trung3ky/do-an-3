package hqtrung.hqt.doan3real.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import hqtrung.hqt.doan3real.R;

public class Start_Activity extends AppCompatActivity {

    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_);

        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                isFirstTime();
            }
        }, 2000);
    }
    private void isFirstTime(){
        SharedPreferences preferences = getApplication().getSharedPreferences("onBoard", Context.MODE_PRIVATE);
        boolean isFirstTime = preferences.getBoolean("isFirstTime", true);
        if (isFirstTime){
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("isFirstTime", false);
            editor.apply();

            //start sigin
            startActivity(new Intent(Start_Activity.this, SigIn_Activity.class));
            finish();
        }else{
            startActivity(new Intent(Start_Activity.this, MainActivity.class));
            finish();

        }
    }
}
