package com.example.pc.royalkandyan2.Models;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

@SuppressLint("ParcelCreator")
public class Notification implements Parcelable{

    String id, sender, reciever, message, reply, recieved;

    public Notification(){

    }

    public Notification(String id, String sender, String reciever, String message, String reply, String recieved) {
        this.id = id;
        this.sender = sender;
        this.reciever = reciever;
        this.message = message;
        this.reply = reply;
        this.recieved = recieved;
    }

    protected Notification(Parcel in) {
        id = in.readString();
        sender = in.readString();
        reciever = in.readString();
        message = in.readString();
        reply = in.readString();
        recieved = in.readString();
    }

    public static final Creator<Notification> CREATOR = new Creator<Notification>() {
        @Override
        public Notification createFromParcel(Parcel in) {
            return new Notification(in);
        }

        @Override
        public Notification[] newArray(int size) {
            return new Notification[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReciever() {
        return reciever;
    }

    public void setReciever(String reciever) {
        this.reciever = reciever;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public String getRecieved() {
        return recieved;
    }

    public void setRecieved(String recieved) {
        this.recieved = recieved;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(sender);
        dest.writeString(reciever);
        dest.writeString(message);
        dest.writeString(reply);
        dest.writeString(recieved);
    }
}
