package com.example.pc.royalkandyan2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class GuestActivty extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_activty);
    }


    public void openFeedBackActivity (View view){
        Intent intent2 = new Intent(this, FeedBackActivity.class);
        startActivity(intent2);
    }
    public void OpenTableReservationActivity (View view){
        Intent intent2 = new Intent(this, TableReservationActivity.class);
        startActivity(intent2);
    }
}
