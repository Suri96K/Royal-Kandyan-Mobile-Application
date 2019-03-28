package com.example.pc.royalkandyan2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class DemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
    }



    public void openAboutDemoActivity (View view){
        Intent intent2 = new Intent(this, AboutDemoActivity.class);
        startActivity(intent2);
    }



    public void openMessageDemoActivity (View view){
        Intent intent2 = new Intent(this, MessageDemoActivity .class);
        startActivity(intent2);
    }
}
