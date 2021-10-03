package com.aaamab.uiapp.ui.main2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;

import com.aaamab.uiapp.R;
import com.aaamab.uiapp.databinding.ActivityMainScreen2Binding;
import com.aaamab.uiapp.services.BoundService;

public class MainScreen2 extends AppCompatActivity {


    ActivityMainScreen2Binding binding ;
    MyHandler handler ;
    BoundService mBoundService ;
    boolean mServiceBound = false ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main_screen2);
        handler = new MyHandler(this);
        binding.setHandler(handler);
    }

    public class MyHandler{
        Context context ;

        public MyHandler(Context context) {
            this.context = context;
        }

        public void printTime(View v){
            if (mServiceBound){
                binding.txtTime.setText(mBoundService.getTimestamp());
            }
        }
        public void StopTime(View v){
            if (mServiceBound){
                unbindService(mServiceConnection);
                mServiceBound = false ;
            }
            Intent intent = new Intent(MainScreen2.this,
                    BoundService.class);
            stopService(intent);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, BoundService.class);
        startService(intent);
        bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
    }
    @Override
    protected void onStop() {
        super.onStop();
        if (mServiceBound) {
            unbindService(mServiceConnection);
            mServiceBound = false;
        }
    }

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceDisconnected(ComponentName name) {
            mServiceBound = false;
        }
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            BoundService.MyBinder myBinder = (BoundService.MyBinder) service;
            mBoundService =  myBinder.getService();
            mServiceBound = true;
        }
    };
}