package com.aaamab.uiapp.ui.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.aaamab.uiapp.R;
import com.aaamab.uiapp.databinding.ActivityLoginScreenBinding;
import com.aaamab.uiapp.ui.forgetPassword.ForgetPasswordScreen;
import com.aaamab.uiapp.ui.main.HomeScreen;
import com.aaamab.uiapp.utils.StaticsMethod;

import static com.aaamab.uiapp.utils.StaticsMethod.MYPREF;
import static com.aaamab.uiapp.utils.StaticsMethod.isLoged;

public class LoginScreen extends AppCompatActivity implements LoginListener {

    ActivityLoginScreenBinding binding ;
    MyHandler handler;

    String email , password ;

    LoginPresenter loginPresenter ;
    SharedPreferences sharedPreferences ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this , R.layout.activity_login_screen);
        handler = new MyHandler(this);
        binding.setHandler(handler);
        loginPresenter = new LoginPresenter(this ,this);
        sharedPreferences = getSharedPreferences(MYPREF , Context.MODE_PRIVATE);
        binding.edEmail.setText(sharedPreferences.getString(StaticsMethod.email , ""));
    }

    @Override
    public void onGetData() {
        Toast.makeText(LoginScreen.this, "Please Enter your Email", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLogin() {

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(isLoged, true);
        editor.commit();

        startActivity(new Intent(LoginScreen.this , HomeScreen.class));
        finish();

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