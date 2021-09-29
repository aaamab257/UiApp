package com.aaamab.uiapp.ui.register;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.aaamab.uiapp.R;
import com.aaamab.uiapp.data.RegisterData;
import com.aaamab.uiapp.databinding.ActivityRegisterScreenBinding;
import com.aaamab.uiapp.ui.login.LoginScreen;
import com.aaamab.uiapp.utils.StaticsMethod;
import com.google.firebase.auth.FirebaseAuth;

import static com.aaamab.uiapp.utils.StaticsMethod.MYPREF;

public class RegisterScreen extends AppCompatActivity implements RegisterLis {

    ActivityRegisterScreenBinding binding;

    MyHandler handler;

    String name, email, password, confirmPassword;

    RegisterPresenter presenter;

    RegisterData userData;

    SharedPreferences sharedpreferences;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register_screen);
        handler = new MyHandler(this);
        binding.setHandler(handler);
        presenter = new RegisterPresenter(this, this);
        sharedpreferences = getSharedPreferences(MYPREF, Context.MODE_PRIVATE);


    }

    @Override
    public void onEmptyFields(String msg) {
        Toast.makeText(RegisterScreen.this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPasswordsLength() {
        Toast.makeText(this, "The Password must be at least 8 digits", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPasswordsNotMatching() {
        Toast.makeText(this, "The Passwords not matching", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccessfully(String email, String name, String password) {
        userData = new RegisterData(name, email, password);
        //userData.setName("Mohamed");
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(StaticsMethod.email , userData.getEmail());
        editor.putString(StaticsMethod.name , userData.getName());

        editor.commit();


        Toast.makeText(this, "Your name : " + userData.getName() + "\nYour Email : " + userData.getEmail(), Toast.LENGTH_SHORT).show();
    }

    public class MyHandler {
        Context context;

        public MyHandler(Context context) {
            this.context = context;
        }

        public void register(View view) {
            name = binding.edName.getText().toString();
            email = binding.edEmail.getText().toString();
            password = binding.edPassword.getText().toString();
            confirmPassword = binding.edConPassword.getText().toString();


            presenter.onRegister(name, email, password, confirmPassword);
        }

        public void goToLogin(View v) {
            presenter.goToLogin();
        }

    }
}





