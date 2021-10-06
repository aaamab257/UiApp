package com.aaamab.uiapp.ui.login;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.aaamab.uiapp.utils.StaticsMethod;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginPresenter {
    LoginListener login;
    Context context;
    FirebaseAuth auth;

    public LoginPresenter(LoginListener login, Context context) {
        this.login = login;
        this.context = context;
        auth = FirebaseAuth.getInstance();
    }

    public void login(String email, String password) {
        if (email.isEmpty()) {
            login.onGetData();
        } else if (password.isEmpty()) {
            Toast.makeText(context, "Please Enter your password", Toast.LENGTH_SHORT).show();
        } else {
            loginWithFirebase(email , password);
            //login.onLogin();
            //Toast.makeText(context, "Login Successfully", Toast.LENGTH_SHORT).show();
        }
    }

    public void loginWithFirebase(String email, String password) {
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            StaticsMethod.UID = task.getResult().getUser().getUid();

                            Toast.makeText(context, "Login successfully", Toast.LENGTH_SHORT).show();
                            login.onLogin();

                        }else {
                            Toast.makeText(context, "Login Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}
