package com.aaamab.uiapp.ui.test;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import com.aaamab.uiapp.R;
import com.aaamab.uiapp.ui.login.LoginScreen;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TestPresenter {
    TestInterface test;
    Context context;
    FirebaseDatabase database;
    DatabaseReference myRef;
    NotificationManager mNotificationManager;

    public TestPresenter(TestInterface test, Context context) {
        this.test = test;
        this.context = context;
        database = FirebaseDatabase.getInstance();
        mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
    }

    public void sendData(String msg) {
        //write in firebase realtime database
        myRef = database.getReference("message");
        myRef.setValue(msg);
        readData();
    }

    public void readData() {

        myRef.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String value = snapshot.getValue(String.class);
                test.onSuccessfully(value);
                showNotification(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(context, "Try again", Toast.LENGTH_SHORT).show();

            }
        });


    }


    public void showNotification(String msg) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        int notificationId = 1;
        String channelId = "channel-01";
        String channelName = "Channel Name";
        int importance = NotificationManager.IMPORTANCE_HIGH;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel(
                    channelId, channelName, importance);
            notificationManager.createNotificationChannel(mChannel);
        }
        Uri alarmSound = RingtoneManager
                .getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        long pattern[] = {0, 800, 200, 300, 400};

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, channelId)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Notification")
                .setContentText(msg)
                .setSound(alarmSound)
                .setAutoCancel(true)
                .setVibrate(pattern);

        Intent resultIntent = new Intent(context, LoginScreen.class);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack((Activity) context);
        stackBuilder.addNextIntent(resultIntent);

        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(
                0,
                PendingIntent.FLAG_UPDATE_CURRENT
        );
        mBuilder.setContentIntent(resultPendingIntent);

        notificationManager.notify(notificationId, mBuilder.build());
    }
}
