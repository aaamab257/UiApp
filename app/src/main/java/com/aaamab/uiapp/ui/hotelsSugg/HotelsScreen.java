package com.aaamab.uiapp.ui.hotelsSugg;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.aaamab.uiapp.R;
import com.aaamab.uiapp.databinding.ActivityHotelsScreenBinding;

public class HotelsScreen extends AppCompatActivity implements HotelsInterface {


    ActivityHotelsScreenBinding binding ;
    MyHandler handler ;
    String city ;
    HotelsPresenter presenter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this , R.layout.activity_hotels_screen);
        handler = new MyHandler(this);
        binding.setHandler(handler);
        presenter = new HotelsPresenter(this , this);
    }


    @Override
    public void onSuccessfully(String id, int more) {
        binding.progressCircular.setVisibility(View.GONE);
        binding.trackingID.setText(id);
        binding.moreSugg.setText(more+"");
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