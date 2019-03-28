package com.example.pc.royalkandyan2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v4.widget.CircularProgressDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.pc.royalkandyan2.Support.AppConfig;
import com.example.pc.royalkandyan2.Support.Controller;
import com.example.pc.royalkandyan2.Support.SQLiteHandler;
import com.example.pc.royalkandyan2.Support.Session;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class InventoryActivity extends AppCompatActivity {

    private static final String TAG = "InventoryActivity";

    private Session session;
    private EditText editText_item_id;
    private Button btnSearch;
    private TextView txtCode, txtCost, txtCategory; // add the rest here
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);

        session = new Session(getApplicationContext());

        progressDialog = new ProgressDialog(getApplicationContext());
        progressDialog.setCancelable(false);

        editText_item_id = findViewById(R.id.edit_text_item_id);
        btnSearch = findViewById(R.id.btn_query_inventory);
        txtCode = findViewById(R.id.txt_item_code);
        txtCost = findViewById(R.id.txt_item_cost);
        txtCategory = findViewById(R.id.txt_item_category);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!editText_item_id.getText().toString().trim().isEmpty()){
                    queryDB(editText_item_id.getText().toString().trim());
                }
            }
        });


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

    private void queryDB(final String id) {

        // Tag used to cancel the request
        String tag_string_req = "req_query_inventory";

        progressDialog.setMessage("Loading");
        showDialog();

        // Make request on other activties to load inventory and so on similar to this
        // Chnage the AppConfig.<URL_HERE> as needed
        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.URL_QUERY_INVENTORY, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Query Response: " + response.toString());
                hideDialog();

                try {

                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");

                    // Check for error node in json
                    if (!error) {

                        JSONObject item = jObj.getJSONObject("item");
                        String itemCode = item.getString("code");
                        String itemCost = item.getString("cost");
                        String itemCategory = item.getString("category");

                        txtCode.setText(itemCode);
                        txtCost.setText(itemCost);
                        txtCategory.setText(itemCategory);

                        // TODO do as above for the remaining fields

                    } else {
                        // Error in Inventory. Get the error message
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
                Log.e(TAG, "Query Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
                hideDialog();
            }
        }) {

            // Parameters sent to the php script
            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to query inventory url
                Map<String, String> params = new HashMap<String, String>();
                params.put("itemId", id);
                return params;
            }

        };

        // Adding request to request queue
        Controller.getInstance().addToRequestQueue(strReq, tag_string_req);

    }
}
