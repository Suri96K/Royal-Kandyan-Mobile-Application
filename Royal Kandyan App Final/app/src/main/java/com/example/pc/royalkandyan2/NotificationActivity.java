package com.example.pc.royalkandyan2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.pc.royalkandyan2.Adapters.NotificationAdapter;
import com.example.pc.royalkandyan2.Models.Notification;
import com.example.pc.royalkandyan2.Support.AppConfig;
import com.example.pc.royalkandyan2.Support.Controller;
import com.example.pc.royalkandyan2.Support.SQLiteHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class NotificationActivity extends AppCompatActivity implements NotificationAdapter.NotificationAdapterListener{

    private static final String TAG = "NotificationActivity";

    private ArrayList<Notification> notifications;
    private ProgressDialog progressDialog;
    private RecyclerView recyclerView;
    NotificationAdapter adapter;
    private SQLiteHandler db;
    private String userId;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_notificatons);

        progressDialog = new ProgressDialog(getApplicationContext());
        progressDialog.setCancelable(false);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        notifications = new ArrayList<>();

        db = new SQLiteHandler(getApplicationContext());
        userId = db.getUserDetails().get("name");

        adapter = new NotificationAdapter(this, notifications, this);

        loadNotifications();

    }

    /**
     * function to load notifications from the sql
     * */
    private void loadNotifications() {
        // Tag used to cancel the request
        String tag_string_req = "req_notifications";

        progressDialog.setMessage("Loading...");
        showDialog();

        // Make request on other activties to load inventory and so on similar to this
        // Chnage the AppConfig.<URL_HERE> as needed
        final StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.URL_GET_MESSAGES, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Notification Response: " + response.toString());
                hideDialog();

                try {

                    JSONArray jsonArray = new JSONArray(response);
                    for(int i=0; i<jsonArray.length(); i++){

                        JSONObject jsonData = jsonArray.getJSONObject(i);
                        String ID =  jsonData.getString("ID");
                        String Sender = jsonData.getString("Sender");
                        String Receiver = jsonData.getString("Receiver");
                        String Message = jsonData.getString("Message");
                        String Reply = jsonData.getString("Reply");
                        String Received = jsonData.getString("Received");
                        Notification notification = new Notification(ID, Sender, Receiver, Message, Reply, Received);
                        notifications.add(notification);

                    }

                    recyclerView.setAdapter(adapter);

                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Notifications Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
                hideDialog();
            }
        }) {
            // Parameters sent to the php script
            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("Receiver", userId);
                return params;
            }
        };

        // Adding request to request queue
        Controller.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

    // Support functions to handle the progress dialog
    private void showDialog() {
        if (!progressDialog.isShowing())
            progressDialog.show();
    }

    private void hideDialog() {
        if (progressDialog.isShowing())
            progressDialog.dismiss();
    }

    @Override
    public void onItemClicked(int position) {
        Notification notification = notifications.get(position);
        Intent intent = new Intent(getApplicationContext(), NotificationReplyActivity.class);
        intent.putExtra("NOTI", notification);
        startActivity(intent);
    }
    public void OpenNotificationActivity (View view){
        Intent intent2 = new Intent(this, NotificationReplyActivity.class);
        startActivity(intent2);
    }
}
