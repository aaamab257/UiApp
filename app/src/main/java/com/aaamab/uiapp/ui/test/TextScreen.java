package com.aaamab.uiapp.ui.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.aaamab.uiapp.R;
import com.aaamab.uiapp.databinding.ActivityTextScreenBinding;

public class TextScreen extends AppCompatActivity implements TestInterface {

    ActivityTextScreenBinding binding ;
    MyHandler handler ;
    TestPresenter presenter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_text_screen);
        handler = new MyHandler(this);
        binding.setHandler(handler);

        presenter = new TestPresenter(this, this );


    }

    @Override
    public void onSuccessfully(String value) {

        binding.txtMessage.setText(value);
    }

    public class MyHandler{
        Context context ;

        public MyHandler(Context context) {
            this.context = context;
        }

        public void onSend(View view){
            String message = binding.edMessage.getText().toString();
            if (message.isEmpty()){
                Toast.makeText(context, "Enter your message", Toast.LENGTH_SHORT).show();
            }else{
                presenter.sendData(message);
            }
        }
    }
}