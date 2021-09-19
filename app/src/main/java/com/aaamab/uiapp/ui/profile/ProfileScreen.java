package com.aaamab.uiapp.ui.profile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.aaamab.uiapp.R;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileScreen extends AppCompatActivity {

    CircleImageView img ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_screen);

        // https://i.pinimg.com/236x/08/1f/62/081f62c884544bb13373ce6122745c61.jpg
        initialViews();
        Picasso.get().load("https://i.pinimg.com/236x/08/1f/62/081f62c884544bb13373ce6122745c61.jpg").into(img);
    }

    private void initialViews() {
        img = findViewById(R.id.Img);
    }
}