package com.example.pc.royalkandyan2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.pc.royalkandyan2.Support.AppConfig;
import com.example.pc.royalkandyan2.Support.Controller;
import com.example.pc.royalkandyan2.Support.SQLiteHandler;
import com.example.pc.royalkandyan2.Support.Session;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LaundryStatusActivity extends AppCompatActivity {

    String ServerURL="http://localhost/laundry.php";
    EditText name, roomno, noofitems,itemdiscription;
    Button button;
    String Tempname, Temproomno, Tempnoofitems, Tempitemdiscription ;

    public void GetData(){

        Tempname = name.getText().toString();
        Temproomno = roomno.getText().toString();
        Tempnoofitems = noofitems.getText().toString();
        Tempitemdiscription = itemdiscription.getText().toString();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laundry_status);

        name = (EditText)findViewById(R.id.EditText_name);
        roomno = (EditText)findViewById(R.id.EditText_roomno);
        noofitems = (EditText)findViewById(R.id.EditText_noofitems);
        itemdiscription = (EditText)findViewById(R.id.EditText_Itemsdiscription);

        button = (Button)findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetData();

                InsertData(Tempname, Temproomno, Tempnoofitems,Tempitemdiscription);
            }

        });
    }

    public void InsertData(final String name, final String roomno, final String noofitems, final String itemdiscription ){

        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {

                String nameHolder = name ;
                String roomnoHolder = roomno ;
                String noofitemsHolder = noofitems;
                String itemdiscriptionHolder = itemdiscription;


                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

                nameValuePairs.add(new BasicNameValuePair("name", nameHolder));
                nameValuePairs.add(new BasicNameValuePair("roomno", roomnoHolder));
                nameValuePairs.add(new BasicNameValuePair("noofitems", noofitemsHolder));
                nameValuePairs.add(new BasicNameValuePair("itemdiscription", itemdiscriptionHolder));


                try {
                    HttpClient httpClient = new DefaultHttpClient();

                    HttpPost httpPost = new HttpPost(ServerURL);

                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                    HttpResponse httpResponse = httpClient.execute(httpPost);

                    HttpEntity httpEntity = httpResponse.getEntity();


                } catch (ClientProtocolException e) {

                } catch (IOException e) {

                }
                return "Data Inserted Successfully";
            }

            @Override
            protected void onPostExecute(String result) {

                super.onPostExecute(result);

                Toast.makeText(LaundryStatusActivity.this, "Data Submit Successfully", Toast.LENGTH_LONG).show();

            }
        }

        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();

        sendPostReqAsyncTask.execute(name, roomno, noofitems, itemdiscription);
    }

}
