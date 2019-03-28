package com.example.pc.royalkandyan2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class HRActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hr);
    }

    public void OpenNotificationsForHRM(View view) {
        Intent intent2 = new Intent(this, NotificationsForHRM.class);
        startActivity(intent2);
    }

    public void openSendMessageActivity(View view) {
        Intent intent2 = new Intent(this, SendMessageActivity.class);
        startActivity(intent2);
    }



    public void OpenShowFeedbackActivity(View view) {
        Intent intent2 = new Intent(this, ShowFeedbackActivity.class);
        startActivity(intent2);
    }

    public void OpenLeaveReqCheckActiivity(View view) {
        Intent intent2 = new Intent(this, LeaveReqCheckActiivity.class);
        startActivity(intent2);
    }

    public void OpenEditShiftActivity(View view) {
        Intent intent2 = new Intent(this, EditShiftActivity.class);
        startActivity(intent2);
    }
}