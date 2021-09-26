package com.aaamab.uiapp.ui.register;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.aaamab.uiapp.ui.login.LoginScreen;

public class RegisterPresenter {
    RegisterLis register;
    Context context;

    public RegisterPresenter(RegisterLis register, Context context) {
        this.register = register;
        this.context = context;
    }


    public void onRegister(String name, String email, String password, String conPassword) {
        if (name.isEmpty()) {
            register.onEmptyFields("Please Enter your name");

        } else if (email.isEmpty()) {
            register.onEmptyFields("Please Enter your email");

        } else if (password.isEmpty()) {
            register.onEmptyFields("Please Enter your password");

        } else if (conPassword.isEmpty()) {
            register.onEmptyFields("Please Enter Confirm password");

        } else if (password.length() < 8 || conPassword.length() < 8) {
            register.onPasswordsLength();

        } else if (password.equals(conPassword)) {
            register.onSuccessfully(email, name , password);

        } else {
            register.onPasswordsNotMatching();

        }
    }

    public void goToLogin() {
        Intent goToLogin = new Intent(context, LoginScreen.class);
        context.startActivity(goToLogin);
    }
}
