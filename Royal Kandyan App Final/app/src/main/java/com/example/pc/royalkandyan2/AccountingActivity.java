package com.example.pc.royalkandyan2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class AccountingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accounting);
    }
    public void OpenNotificationsForAccounting (View view){
        Intent intent2 = new Intent(this, NotificationsForAccounting .class);
        startActivity(intent2);
    }
    public void openSendMessageActivity(View view) {
        Intent intent3 = new Intent(this, SendMessageActivity.class);
        startActivity(intent3);
    }
}
