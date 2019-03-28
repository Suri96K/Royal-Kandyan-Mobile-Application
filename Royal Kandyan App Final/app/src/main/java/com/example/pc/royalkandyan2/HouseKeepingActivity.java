package com.example.pc.royalkandyan2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class HouseKeepingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_keeping);
    }
    public void openSendMessageActivity(View view) {
        Intent intent3 = new Intent(this, SendMessageActivity.class);
        startActivity(intent3);
    }

    public void openNotificationActivity(View view) {
        Intent intent3 = new Intent(this, NotificationActivity.class);
        startActivity(intent3);
    }

    public void openNotificationsForHouseKeeping(View view) {
        Intent intent3 = new Intent(this, NotificationsForHouseKeeping.class);
        startActivity(intent3);
    }

    public void openRoomStatusActivity(View view) {
        Intent intent3 = new Intent(this, RoomStatusActivity.class);
        startActivity(intent3);
    }

    public void openLaundryStatusActivity(View view) {
        Intent intent3 = new Intent(this, LaundryStatusActivity.class);
        startActivity(intent3);
    }

    public void openLaundrycheckActivity(View view) {
        Intent intent3 = new Intent(this, LaundrycheckActivity.class);
        startActivity(intent3);
    }
}
