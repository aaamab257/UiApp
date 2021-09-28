package com.aaamab.uiapp.ui.Splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.aaamab.uiapp.R;
import com.aaamab.uiapp.ui.login.LoginScreen;
import com.aaamab.uiapp.ui.main.HomeScreen;
import com.aaamab.uiapp.utils.StaticsMethod;

public class SplashScreen extends AppCompatActivity {

    private int DISPLAY_LENGTH = 3000 ;

    SharedPreferences sharedPreferences ;

    boolean isLoged = false ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        sharedPreferences = getSharedPreferences(StaticsMethod.MYPREF , Context.MODE_PRIVATE);
        isLoged = sharedPreferences.getBoolean(StaticsMethod.isLoged , false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isLoged){
                    Intent goToLogin = new Intent(SplashScreen.this , HomeScreen.class);
                    startActivity(goToLogin);
                    finish();
                }else {
                    Intent goToLogin = new Intent(SplashScreen.this , LoginScreen.class);
                    startActivity(goToLogin);
                    finish();
                }

            }
        },DISPLAY_LENGTH);
    }
}