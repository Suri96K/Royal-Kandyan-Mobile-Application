package com.example.pc.royalkandyan2;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.pc.royalkandyan2.Models.Notification;
import com.example.pc.royalkandyan2.Support.AppConfig;
import com.example.pc.royalkandyan2.Support.Controller;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class NotificationReplyActivity extends AppCompatActivity {

    private static final String TAG = "EditShiftActivity";

    TextView txtSender, txtMessage;
    EditText reply;
    Button btnReply;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_reply);

        progressDialog = new ProgressDialog(getApplicationContext());
        progressDialog.setCancelable(false);

        Notification notification = (Notification) getIntent().getExtras().get("NOTI");
        final String id = notification.getId();

        txtSender = findViewById(R.id.message_sender) ;
        txtMessage = findViewById(R.id.message);
        reply = findViewById(R.id.reply);
        btnReply = findViewById(R.id.btn_send_reply);

        txtSender.setText("Sender : " + notification.getSender());
        txtMessage.setText(notification.getMessage());

        btnReply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str_reply = reply.getText().toString().trim();
                if (str_reply.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Please type areply", Toast.LENGTH_SHORT).show();
                }else {
                    sendReply(id, str_reply);
                }
            }
        });

    }

    private void sendReply(final String id, final String str_reply) {

        String tag_string_req = "req_reply_message";

        progressDialog.setMessage("Sending Reply...");
        showDialog();

        // Make request on other activties to load inventory and so on similar to this
        // Chnage the AppConfig.<URL_HERE> as needed
        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.URL_UPDATE_MESSAGE, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Reply Message Response: " + response.toString());
                hideDialog();

                try {

                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");

                    // Check for error node in json
                    if (!error) {
                        Toast.makeText(getApplicationContext(),
                                "Sent!", Toast.LENGTH_LONG).show();

                    } else {
                        // Error. Get the error message
                        String errorMsg = jObj.getString("error_msg");
                        Toast.makeText(getApplicationContext(),
                                errorMsg, Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Reply Message Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
                hideDialog();
            }
        }) {

            // Parameters sent to the php script
            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to shift update url
                Map<String, String> params = new HashMap<String, String>();
                params.put("ID", id);
                params.put("Reply", str_reply);
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

}
