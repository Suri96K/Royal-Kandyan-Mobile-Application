package com.example.pc.royalkandyan2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Login2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
    }

    public void openHRActivity(View view) {
        Intent intent2 = new Intent(this, HRActivity.class);
        startActivity(intent2);
    }

    public void openAccoutingActivity(View view) {
        Intent intent2 = new Intent(this, AccountingActivity.class);
        startActivity(intent2);
    }

    public void openEmployeeActivity(View view) {
        Intent intent2 = new Intent(this, EmployeeActivity.class);
        startActivity(intent2);
    }

    public void openHouseKeepingActivity(View view) {
        Intent intent2 = new Intent(this, HouseKeepingActivity.class);
        startActivity(intent2);
    }

    public void openInvenActivity(View view) {
        Intent intent2 = new Intent(this, InvenActivity.class);
        startActivity(intent2);
    }

    public void openPosActivity(View view) {
        Intent intent2 = new Intent(this, POSActivity.class);
        startActivity(intent2);
    }

    public void openReceptionistActivity(View view) {
        Intent intent2 = new Intent(this, ReceptionistActivity.class);
        startActivity(intent2);
    }
}