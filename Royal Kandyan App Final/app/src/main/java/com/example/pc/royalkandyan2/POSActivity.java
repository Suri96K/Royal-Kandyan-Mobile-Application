package com.example.pc.royalkandyan2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class POSActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pos);
    }
    public void openSendMessageActivity(View view) {
        Intent intent3 = new Intent(this, SendMessageActivity.class);
        startActivity(intent3);
    }

    public void openNotificationActivity(View view) {
        Intent intent3 = new Intent(this, NotificationActivity.class);
        startActivity(intent3);
    }

    public void openNotificationsForPos(View view) {
        Intent intent3 = new Intent(this, NotificationsForPos.class);
        startActivity(intent3);
    }
}
