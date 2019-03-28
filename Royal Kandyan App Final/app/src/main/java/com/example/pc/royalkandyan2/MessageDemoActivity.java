package com.example.pc.royalkandyan2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.view.View.*;

public class MessageDemoActivity extends AppCompatActivity implements OnClickListener {

    SQLiteDatabase db;
    EditText editsearch, editempname, editempmail, editage;
    SQLiteDatabase sqlitedb;
    Button Add, Delete, Update, Searchall, Search;
    String name, mailid, age, search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_demo);

        //Create database,EmployeeDB database name
        db = openOrCreateDatabase("EmployeeDB", Context.MODE_PRIVATE, null);
        //create table Employee
        db.execSQL("CREATE TABLE IF NOT EXISTS Employee(EmpId INTEGER PRIMARY KEY AUTOINCREMENT,EmpName VARCHAR,EmpMail VARCHAR,EmpSalary VARCHAR);");
        editsearch = (EditText) findViewById(R.id.editText);
        editempname = (EditText) findViewById(R.id.editText2);
        editempmail = (EditText) findViewById(R.id.editText3);
        editage = (EditText) findViewById(R.id.editText4);
        Add = (Button) findViewById(R.id.button);
        Delete= (Button) findViewById(R.id.button5);
        Update= (Button) findViewById(R.id.button3);
        Search= (Button) findViewById(R.id. button4);
        Searchall=(Button) findViewById(R.id. button2);
        Add.setOnClickListener(this);
        Delete.setOnClickListener(this);
        Update.setOnClickListener(this);
        Search.setOnClickListener(this);
        Searchall.setOnClickListener(this);
    }

    public void onClick(View v) {

        // Insert data
        if (v.getId() == R.id.button) {

            if (editempname.getText().toString().trim().length() == 0 || editempmail.getText().toString().trim().length() == 0 || editage.getText().toString().trim().length() == 0) {
                Toast.makeText(this, "Fields cannot be Empty", Toast.LENGTH_SHORT).show();
                return;
            }
            db.execSQL("INSERT INTO Employee(EmpName,EmpMail,EmpSalary)VALUES('" + editempname.getText() + "','" + editempmail.getText() + "','" + editage.getText() + "');");
            Toast.makeText(this, "Sent", Toast.LENGTH_SHORT).show();
        }
        //Update data
        else if(v.getId()==R.id.button3)
        {
            //code for update data
            if(editsearch.getText().toString().trim().length()==0)
            {
                Toast.makeText(this, "Enter Employee  Name",Toast.LENGTH_SHORT).show();
                return;
            }
            Cursor c=db.rawQuery("SELECT * FROM Employee WHERE EmpName='"+ editsearch.getText()+"'", null);
            if(c.moveToFirst()) {
                db.execSQL("UPDATE Employee  SET EmpName ='"+ editempname.getText()+"', EmpMail='"+ editempmail.getText()+"',EmpSalary='"+ editage.getText()+"' WHERE EmpName ='"+editsearch.getText()+"'");
                Toast.makeText(this, "Record Modified",Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(this, "Invalid Employee Name",Toast.LENGTH_SHORT);
            }
        }
        //Delete data
        else if(v.getId()==R.id.button5)
        {
            //code for delete data
            if(editsearch.getText().toString().trim().length()==0)
            {
                Toast.makeText(this, " Please enter Employee  Name ",Toast.LENGTH_SHORT).show();
                return;
            }
            Cursor c=db.rawQuery("SELECT * FROM Employee WHERE EmpName ='"+ editsearch.getText()+"'", null);
            if(c.moveToFirst())
            {
                db.execSQL("DELETE FROM Employee WHERE EmpName ='"+ editsearch.getText()+"'");
                Toast.makeText(this, "Record Deleted",Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(this, "Invalid Employee Name ",Toast.LENGTH_SHORT).show();
            }
        }
        //Select all records
        else if (v.getId() == R.id.button2)
        {
            //code for select all data
            Cursor c=db.rawQuery("SELECT * FROM Employee", null);
            if(c.getCount()==0)
            {
                Toast.makeText(this, "No Message found",Toast.LENGTH_SHORT).show();
                return;
            }
            StringBuffer buffer=new StringBuffer();
            while(c.moveToNext())
            {
                buffer.append("Name: "+c.getString(1)+"\n");
                buffer.append("Receiver: "+c.getString(2)+"\n");
                buffer.append("Message: "+c.getString(3)+"\n\n");
            }
            Toast.makeText(this, buffer.toString(),Toast.LENGTH_SHORT).show();
        }

        //Select a particular record
        else if(v.getId()==R.id.button4)
        {
            //code for select particular data
            if(editsearch.getText().toString().trim().length()==0)
            {
                Toast.makeText(this, "Enter Employee Name",Toast.LENGTH_SHORT).show();
                return;
            }
            Cursor c=db.rawQuery("SELECT * FROM Employee WHERE EmpMail='"+editsearch.getText()+"'", null);
            if(c.moveToFirst())
            {
                editempname.setText(c.getString(1));
                editempmail.setText(c.getString(2));
                editage.setText(c.getString(3));
            }
            else
            {
                Toast.makeText(this, "Invalid Employee Name",Toast.LENGTH_SHORT).show();
            }
        }


    }
}
