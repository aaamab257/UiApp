package com.aaamab.uiapp.ui.entities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.aaamab.uiapp.R;
import com.aaamab.uiapp.adapters.EntitiesAdapter;
import com.aaamab.uiapp.databinding.ActivityEntitiesScreenBinding;
import com.aaamab.uiapp.utils.StaticsMethod;

public class EntitiesScreen extends AppCompatActivity {

    ActivityEntitiesScreenBinding binding ;
    MyHandler handler ;
    EntitiesAdapter adapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this ,R.layout.activity_entities_screen);
        handler = new MyHandler(this);
        binding.setHandler(handler);
        adapter = new EntitiesAdapter(StaticsMethod.entities , this);
        binding.recEntities.setLayoutManager(new LinearLayoutManager(this));
        binding.recEntities.setAdapter(adapter);
    }

    public class MyHandler{
        Context context ;

        public MyHandler(Context context) {
            this.context = context;
        }
        public void onBack(View v){
            onBackPressed();
        }
    }
}