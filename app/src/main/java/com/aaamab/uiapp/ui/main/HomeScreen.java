package com.aaamab.uiapp.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.aaamab.uiapp.R;
import com.aaamab.uiapp.databinding.ActivityHomeScreenBinding;
import com.aaamab.uiapp.services.FourgroundService;
import com.aaamab.uiapp.ui.Splash.SplashScreen;
import com.aaamab.uiapp.utils.StaticsMethod;

public class HomeScreen extends AppCompatActivity {

    TextView logOut;
    ActivityHomeScreenBinding binding;
    MyHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home_screen);
        handler = new MyHandler(this);
        binding.setHandler(handler);
        /*logOut = findViewById(R.id.logOut);
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences(StaticsMethod.MYPREF , Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean(StaticsMethod.isLoged , false);
                editor.commit();
                startActivity(new Intent(HomeScreen.this, SplashScreen.class));
            }
        });*/
    }

    public class MyHandler {
        Context context;

        public MyHandler(Context context) {
            this.context = context;
        }

        public void onStart(View v) {

            startService();

        }

        public void onStop(View v) {

            stopService();

        }


    }

    public void startService() {
        /*Intent serviceIntent = new Intent(this, FourgroundService.class);
        serviceIntent.putExtra("inputExtra", "Foreground Service Example in Android");
        ContextCompat.startForegroundService(this, serviceIntent);*/

        startService(new Intent(this , FourgroundService.class));
    }

    public void stopService() {
        /*Intent serviceIntent = new Intent(this, FourgroundService.class);
        stopService(serviceIntent);*/
        stopService(new Intent(this , FourgroundService.class));
    }
}