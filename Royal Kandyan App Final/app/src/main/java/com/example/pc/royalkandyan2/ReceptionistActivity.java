package com.example.pc.royalkandyan2;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;

public class ReceptionistActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receptionist);
    }
    public void OpenNotificationActivity(View view) {
        Intent intent2 = new Intent(this, NotificationActivity.class);
        startActivity(intent2);
    }

    public void openSendMessageActivity(View view) {
        Intent intent2 = new Intent(this, SendMessageActivity.class);
        startActivity(intent2);
    }
    public void OpenShowFeedbackActivity(View view) {
        Intent intent7 = new Intent(this, ShowFeedbackActivity.class);
        startActivity(intent7);
    }

    public void OpenBookingsCheckActivity(View view) {
        Intent intent = new Intent(this, BookingsCheckActivity.class);
        startActivity(intent);
    }

    public void OpenNotificationsForReceptionist(View view) {
        Intent intent = new Intent(this, NotificationsForReceptionist.class);
        startActivity(intent);
    }

    public void OpenGuestsInHotelActivity(View view) {
        Intent intent = new Intent(this, GuestsInHotelActivity.class);
        startActivity(intent);
    }

}
