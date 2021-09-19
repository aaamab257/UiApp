package com.aaamab.uiapp.ui.Users;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.aaamab.uiapp.R;
import com.aaamab.uiapp.adapters.UsersAdapter;

import java.util.ArrayList;

public class UsersScreen extends AppCompatActivity {

    RecyclerView usersRec ;
    ArrayList<String> users , usersEmail ;
    UsersAdapter adapter ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_screen);

        initialViews();
        users = new ArrayList<>();
        usersEmail = new ArrayList<>();
        users.add("Ahmed");
        users.add("Mohamed");
        users.add("Khalid");

        usersEmail.add("ahmed@yahoo.com");
        usersEmail.add("Mohamed@gmail.com");
        usersEmail.add("Khalid@yahoo.com");

        adapter = new UsersAdapter(users , usersEmail,UsersScreen.this);
        usersRec.setLayoutManager(new LinearLayoutManager(this));
        usersRec.setAdapter(adapter);
    }

    private void initialViews() {
        usersRec = findViewById(R.id.recUsers);
    }
}