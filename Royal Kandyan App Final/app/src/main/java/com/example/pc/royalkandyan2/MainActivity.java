package com.example.pc.royalkandyan2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void openHotelActivity (View view){
        Intent intent2 = new Intent(this, HotelActivity.class);
        startActivity(intent2);
    }
    public void openGuestActivity (View view){
        Intent intent2 = new Intent(this, GuestActivty.class);
        startActivity(intent2);
    }

    public void openDemoActivity (View view){
        Intent intent2 = new Intent(this, DemoActivity.class);
        startActivity(intent2);
    }

}
