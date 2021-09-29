package com.aaamab.uiapp.ui.register;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.aaamab.uiapp.ui.login.LoginScreen;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterPresenter {
    RegisterLis register;
    Context context;
    FirebaseAuth auth;
    FirebaseFirestore db;

    public RegisterPresenter(RegisterLis register, Context context) {
        this.register = register;
        this.context = context;
        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
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
            registerWithFirebase(email, password, name);
            //register.onSuccessfully(email, name, password);

        } else {
            register.onPasswordsNotMatching();

        }
    }

    public void goToLogin() {
        Intent goToLogin = new Intent(context, LoginScreen.class);
        context.startActivity(goToLogin);
    }

    public void registerWithFirebase(String email, String password, String name) {

        Map<String, Object> user = new HashMap<>();
        user.put("name", name);


        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            String uid = auth.getCurrentUser().getUid();
                            //Toast.makeText(context, "Register Successfully", Toast.LENGTH_SHORT).show();
                            db.collection("users").document(uid)
                                    .set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {

                                    Toast.makeText(context, "User Created Successfully", Toast.LENGTH_SHORT).show();


                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(context, "User Created Failed", Toast.LENGTH_SHORT).show();
                                }
                            });


                        } else {
                            Toast.makeText(context, "Register Failed", Toast.LENGTH_SHORT).show();
                        }


                    }
                });
    }
}
