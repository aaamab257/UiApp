package com.aaamab.uiapp.ui.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.aaamab.uiapp.R;
import com.aaamab.uiapp.databinding.ActivityLoginScreenBinding;
import com.aaamab.uiapp.ui.forgetPassword.ForgetPasswordScreen;

public class LoginScreen extends AppCompatActivity implements LoginListener {

    ActivityLoginScreenBinding binding ;
    MyHandler handler;

    String email , password ;

    LoginPresenter loginPresenter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this , R.layout.activity_login_screen);
        handler = new MyHandler(this);
        binding.setHandler(handler);
        loginPresenter = new LoginPresenter(this ,this);
    }

    @Override
    public void onGetData() {
        Toast.makeText(LoginScreen.this, "Please Enter your Email", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLogin() {

    }

    public class MyHandler {
        Context context ;

        public MyHandler(Context context) {
            this.context = context;
        }

        public void login(View v){
            email = binding.edEmail.getText().toString();
            password = binding.edPassword.getText().toString();
            loginPresenter.login(email , password);
        }
        public void forgetPassword(View v){
            Intent goToForgetScreen = new Intent(LoginScreen.this, ForgetPasswordScreen.class);
            startActivity(goToForgetScreen);
        }

    }
}