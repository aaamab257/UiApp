package com.aaamab.uiapp.ui.users;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.aaamab.uiapp.R;
import com.aaamab.uiapp.adapters.UsersAdapter;
import com.aaamab.uiapp.databinding.ActivityUsersScreenBinding;
import com.aaamab.uiapp.room.AppDatabase;
import com.aaamab.uiapp.room.DataAccessObject;
import com.aaamab.uiapp.room.User;

import java.util.List;

public class UsersScreen extends AppCompatActivity {

    RecyclerView usersRec;
    List<User> users;
    UsersAdapter adapter;
    AppDatabase db;

    ActivityUsersScreenBinding binding;
    MyHandler handler;
    DataAccessObject dataAccessObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_users_screen);
        handler = new MyHandler(this);
        binding.setHandler(handler);

        initialViews();
        // 1
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "UIApp").allowMainThreadQueries().build();

        //2
        dataAccessObject = db.dataAccessObject();

        users = dataAccessObject.getAllUsers();



        /*users = new ArrayList<>();
        usersEmail = new ArrayList<>();
        users.add("Ahmed");
        users.add("Mohamed");
        users.add("Khalid");

        usersEmail.add("ahmed@yahoo.com");
        usersEmail.add("Mohamed@gmail.com");
        usersEmail.add("Khalid@yahoo.com");*/

        adapter = new UsersAdapter(users, UsersScreen.this);
        usersRec.setLayoutManager(new LinearLayoutManager(this));
        usersRec.setAdapter(adapter);
    }

    private void initialViews() {
        usersRec = findViewById(R.id.recUsers);
    }

    public class MyHandler {
        Context context;

        public MyHandler(Context context) {
            this.context = context;
        }

        public void add(View v) {
            String fname = binding.edFirstName.getText().toString();
            String lname = binding.edLastName.getText().toString();
            if (fname.isEmpty()) {
                binding.edFirstName.setError("Please Enter First name");
            } else if (lname.isEmpty()) {
                binding.edLastName.setError("Please Enter Last name");
            } else {
                User usr = new User();
                usr.setFirst_name(fname);
                usr.setLast_name(lname);
                dataAccessObject.insertAll(usr);
                adapter.addToList(usr);
                adapter.notifyDataSetChanged();
            }

        }
    }
}