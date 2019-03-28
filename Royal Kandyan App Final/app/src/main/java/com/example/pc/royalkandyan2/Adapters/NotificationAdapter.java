package com.example.pc.royalkandyan2.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.pc.royalkandyan2.Models.Notification;
import com.example.pc.royalkandyan2.Models.Shift;
import com.example.pc.royalkandyan2.R;
import com.example.pc.royalkandyan2.Support.AppConfig;
import com.example.pc.royalkandyan2.Support.Controller;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NotificationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "NotificationAdapter";

    private Context context;
    private List<Notification> notificationList;
    private NotificationAdapterListener listener;
    View listItem;

    public NotificationAdapter(Context context, List<Notification> notifications, NotificationAdapterListener listener){
        this.context = context;
        this.notificationList = notifications;
        this.listener = listener;
    }

    public class ListItemHolder extends RecyclerView.ViewHolder{

        TextView sender, message;

        public ListItemHolder(View view){
            super(view);
            listItem = view;
            sender = view.findViewById(R.id.notification_sender);
            message = view.findViewById(R.id.notification_message);

        }

        public void bindView(Notification notification){
            String _sender;
            if(notification.getRecieved().equals("0")){
                _sender = "New Message from " + notification.getSender();
                makeAsReceived(notification.getId());
            }else {
                _sender = "Message from " + notification.getSender();
            }
            sender.setText(_sender);
            message.setText(notification.getMessage());
            applyClickEvents(getAdapterPosition());
        }

    }

    private void makeAsReceived(final String id) {
        String tag_string_req = "req_mark_as_read";

        // Make request on other activties to load inventory and so on similar to this
        // Chnage the AppConfig.<URL_HERE> as needed
        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.URL_MARK_MESSAGE_AS_READ, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                Log.d(TAG, "Notification Read Response: " + response.toString());

                try {

                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");

                    // Check for error node in json
                    if (!error) {

                    } else {
                        // Error in login. Get the error message
                        String errorMsg = jObj.getString("error_msg");
                        Toast.makeText(context,
                                errorMsg, Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Login Error: " + error.getMessage());
            }
        }) {

            // Parameters sent to the php script
            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("ID", id);
                return params;
            }

        };

        // Adding request to request queue
        Controller.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

    private void applyClickEvents(final int position) {
        // apply all the event listeners in here
        // add a new action to do in the interface and call it
        // implement the interface and pass it as a argument when initializing this adapter
        listItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClicked(position);
            }
        });

    }

    public interface NotificationAdapterListener {
        void onItemClicked(int position);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_list_item, parent, false);
        return new ListItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        Notification notification = notificationList.get(i);
        ((ListItemHolder) viewHolder).bindView(notification);
    }

    @Override
    public int getItemCount() {
        return notificationList.size();
    }
}
