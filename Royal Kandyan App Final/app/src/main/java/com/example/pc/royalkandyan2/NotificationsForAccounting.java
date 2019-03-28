package com.example.pc.royalkandyan2;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import org.apache.http.HttpResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import java.io.IOException;

public class NotificationsForAccounting extends AppCompatActivity {

    HttpResponse httpResponse;
    Button button;
    TextView textView;
    JSONObject jsonObject = null ;
    String StringHolder = "" ;
    ProgressBar progressBar;
    // Adding HTTP Server URL to string variable.
    String HttpURL = "http://localhost/NotificationsForAccounting.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications_for_accounting);

        // Assigning ID's to button, textView and progressbar.
        button = (Button)findViewById(R.id.button);
        textView = (TextView)findViewById(R.id.textView);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);

        // Adding click lister to button.
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Showing progress bar on button click.
                progressBar.setVisibility(View.VISIBLE);

                //Calling GetDataFromServerIntoTextView method to Set JSon MySQL data into TextView.
                new GetDataFromServerIntoTextView(NotificationsForAccounting.this).execute();

            }
        });
    }

    // Declaring GetDataFromServerIntoTextView method with AsyncTask.
    public class GetDataFromServerIntoTextView extends AsyncTask<Void, Void, Void>
    {
        // Declaring CONTEXT.
        public Context context;


        public GetDataFromServerIntoTextView(Context context)
        {
            this.context = context;
        }

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0)
        {

            HttpClient httpClient = new DefaultHttpClient();

            // Adding HttpURL to my HttpPost oject.
            HttpPost httpPost = new HttpPost(HttpURL);

            try {
                httpResponse = httpClient.execute(httpPost);

                StringHolder = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");

            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try{
                // Passing string holder variable to JSONArray.
                JSONArray jsonArray = new JSONArray(StringHolder);
                jsonObject = jsonArray.getJSONObject(0);


            } catch ( JSONException e) {
                e.printStackTrace();
            }

            catch (Exception e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }
        protected void onPostExecute(Void result)
        {
            try {

                // Adding JSOn string to textview after done loading.
                textView.setText(jsonObject.getString("TextViewServerData"));

            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            //Hiding progress bar after done loading TextView.
            progressBar.setVisibility(View.GONE);

        }
    }

}
