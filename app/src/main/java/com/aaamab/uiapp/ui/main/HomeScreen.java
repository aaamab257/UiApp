package com.aaamab.uiapp.ui.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.aaamab.uiapp.R;
import com.aaamab.uiapp.ui.Splash.SplashScreen;
import com.aaamab.uiapp.utils.StaticsMethod;

public class HomeScreen extends AppCompatActivity {

    TextView logOut ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        logOut = findViewById(R.id.logOut);
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences(StaticsMethod.MYPREF , Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean(StaticsMethod.isLoged , false);
                editor.commit();
                startActivity(new Intent(HomeScreen.this, SplashScreen.class));
            }
        });
    }
}