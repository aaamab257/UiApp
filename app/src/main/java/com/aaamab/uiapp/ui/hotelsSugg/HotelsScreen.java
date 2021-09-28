package com.aaamab.uiapp.ui.hotelsSugg;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.aaamab.uiapp.R;
import com.aaamab.uiapp.adapters.SuggAdapter;
import com.aaamab.uiapp.data.SuggestionsObj;
import com.aaamab.uiapp.databinding.ActivityHotelsScreenBinding;

import java.util.ArrayList;

public class HotelsScreen extends AppCompatActivity implements HotelsInterface {


    ActivityHotelsScreenBinding binding ;
    MyHandler handler ;
    String city ;
    HotelsPresenter presenter ;
    SuggAdapter adapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this , R.layout.activity_hotels_screen);
        handler = new MyHandler(this);
        binding.setHandler(handler);
        presenter = new HotelsPresenter(this , this);
    }


    @Override
    public void onSuccessfully(ArrayList<SuggestionsObj> data) {
        binding.progressCircular.setVisibility(View.GONE);
        adapter = new SuggAdapter(data , this );
        binding.recSugg.setLayoutManager(new LinearLayoutManager(this));
        binding.recSugg.setAdapter(adapter);
    }

    @Override
    public void onEmptyFields() {

    }

    public class MyHandler{
        Context context ;

        public MyHandler(Context context) {
            this.context = context;
        }

        public void search(View v){
            binding.progressCircular.setVisibility(View.VISIBLE);
            city = binding.edCity.getText().toString();
            if (city.isEmpty()){
                binding.progressCircular.setVisibility(View.GONE);
                Toast.makeText(context, "Please Enter the city", Toast.LENGTH_SHORT).show();
            }else {
                presenter.getData(city , "en_US");
            }
        }
    }
}