package com.aaamab.uiapp.ui.register;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.aaamab.uiapp.R;
import com.aaamab.uiapp.databinding.ActivityRegisterScreenBinding;
import com.aaamab.uiapp.ui.login.LoginScreen;

public class RegisterScreen extends AppCompatActivity {

    ActivityRegisterScreenBinding binding;

    MyHandler handler;

    String name , email , password , confirmPassword ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register_screen);
        handler = new MyHandler(this);
        binding.setHandler(handler);

    }

    public class MyHandler {
        Context context;

        public MyHandler(Context context) {
            this.context = context;
        }

        public void register(View view) {
            name = binding.edName.getText().toString() ;
            email = binding.edEmail.getText().toString();
            password = binding.edPassword.getText().toString();
            confirmPassword = binding.edConPassword.getText().toString();
            if (name.isEmpty()){
                Toast.makeText(context, "Please Enter your name", Toast.LENGTH_SHORT).show();
            }else if (email.isEmpty()){
                Toast.makeText(context, "Please Enter your email", Toast.LENGTH_SHORT).show();
            }else if (password.isEmpty()){
                Toast.makeText(context, "Please Enter your password", Toast.LENGTH_SHORT).show();
            }else if (confirmPassword.isEmpty()){
                Toast.makeText(context, "Please Enter Confirm password", Toast.LENGTH_SHORT).show();
            }else if (password.length() < 8 || confirmPassword.length() < 8){
                Toast.makeText(context, "The Password must be at least 8 digits", Toast.LENGTH_SHORT).show();
            }else if (password.equals(confirmPassword)){
                Toast.makeText(context, "You name : "+name +"\nyour email : "+email, Toast.LENGTH_LONG).show();
            }else {
                Toast.makeText(context, "Passwords Not matching", Toast.LENGTH_SHORT).show();
            }

        }

        public void goToLogin(View v){
            Intent goToLogin = new Intent(RegisterScreen.this , LoginScreen.class);
            startActivity(goToLogin);
        }

    }
}





