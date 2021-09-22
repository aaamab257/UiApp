package com.aaamab.uiapp.ui.forgetPassword;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.aaamab.uiapp.R;
import com.aaamab.uiapp.databinding.ActivityForgetPasswordScreenBinding;

public class ForgetPasswordScreen extends AppCompatActivity {

    ActivityForgetPasswordScreenBinding binding ;
    MyHandler handler ;
    String email ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil . setContentView(this , R.layout.activity_forget_password_screen);
        handler = new MyHandler(this);
        binding.setHandler(handler);
    }

    public class MyHandler{
        Context context ;

        public MyHandler(Context context) {
            this.context = context;
        }

        public void sendEmail(View view){
            email = binding.edEmail.getText().toString();
            if (email . isEmpty()){
                Toast.makeText(context, "Please Enter your email", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(context, "Please check your mail to reenter your password", Toast.LENGTH_SHORT).show();
            }

        }
        public void onBack(View view){
            onBackPressed();
        }
    }
}