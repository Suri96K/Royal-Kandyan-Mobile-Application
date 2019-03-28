package com.example.pc.royalkandyan2;

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

public class SendMessageActivity extends AppCompatActivity {

    String ServerURL="http://localhost/get_data.php";
    EditText Sender, Receiver, Message;
    Button button;
    String TempSender, TempReceiver, TempMessage ;



    public void GetData(){

        TempSender = Sender.getText().toString();
        TempReceiver = Receiver.getText().toString();
        TempMessage = Message.getText().toString();

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        Sender = (EditText)findViewById(R.id.EditText_Sender);
        Receiver = (EditText)findViewById(R.id.EditText_Receiver);
        Message=(EditText)findViewById(R.id.EditText_Message);

        button = (Button)findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetData();

                InsertData(TempSender, TempReceiver,TempMessage);
            }

        });

    }
    public void InsertData(final String Sender, final String Receiver, final String Message){

        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {

                String SenderHolder = Sender ;
                String ReceiverHolder = Receiver ;
                String MessageHolder = Message ;

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

                nameValuePairs.add(new BasicNameValuePair("Sender", SenderHolder));
                nameValuePairs.add(new BasicNameValuePair("Receiver", ReceiverHolder));
                nameValuePairs.add(new BasicNameValuePair("Message", MessageHolder));

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

                Toast.makeText(SendMessageActivity.this, "Data Submit Successfully", Toast.LENGTH_LONG).show();

            }
        }

        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();

        sendPostReqAsyncTask.execute(Sender, Receiver,Message);
    }

}
