package com.example.pc.royalkandyan2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class EmployeeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);
    }

    public void openLeaveRequestActivity(View view) {
        Intent intent2 = new Intent(this, LeaveRequestActivity.class);
        startActivity(intent2);
    }

    public void openSendMessageActivity(View view) {
        Intent intent3 = new Intent(this, SendMessageActivity.class);
        startActivity(intent3);
    }
    public void OpenNotificationActivity(View view) {
        Intent intent2 = new Intent(this, NotificationActivity.class);
        startActivity(intent2);
    }
    public void OpenNotificationsForEmployees (View view){
        Intent intent2 = new Intent(this, NotificationsForEmployees.class);
        startActivity(intent2);
    }
}
