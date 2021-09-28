package com.aaamab.uiapp.ui.login;

import android.content.Context;
import android.widget.Toast;

public class LoginPresenter {
    LoginListener login;
    Context context;

    public LoginPresenter(LoginListener login, Context context) {
        this.login = login;
        this.context = context;
    }

    public void login(String email, String password) {
        if (email.isEmpty()) {
            login.onGetData();
        } else if (password.isEmpty()) {
            Toast.makeText(context, "Please Enter your password", Toast.LENGTH_SHORT).show();
        } else {
            login.onLogin();
            //Toast.makeText(context, "Login Successfully", Toast.LENGTH_SHORT).show();
        }
    }

}
