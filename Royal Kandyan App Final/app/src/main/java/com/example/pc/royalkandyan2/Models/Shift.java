package com.example.pc.royalkandyan2.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class Shift implements Parcelable{

    String StaffID, Name, aTime, LTime, Department, type, date;

    public Shift(){

    }

    public Shift(String staffID, String name, String aTime, String LTime, String department, String type, String date) {
        StaffID = staffID;
        Name = name;
        this.aTime = aTime;
        this.LTime = LTime;
        Department = department;
        this.type = type;
        this.date = date;
    }

    protected Shift(Parcel in) {
        StaffID = in.readString();
        Name = in.readString();
        aTime = in.readString();
        LTime = in.readString();
        Department = in.readString();
        type = in.readString();
        date = in.readString();
    }

    public static final Creator<Shift> CREATOR = new Creator<Shift>() {
        @Override
        public Shift createFromParcel(Parcel in) {
            return new Shift(in);
        }

        @Override
        public Shift[] newArray(int size) {
            return new Shift[size];
        }
    };

    public String getStaffID() {
        return StaffID;
    }

    public void setStaffID(String staffID) {
        StaffID = staffID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getaTime() {
        return aTime;
    }

    public void setaTime(String aTime) {
        this.aTime = aTime;
    }

    public String getLTime() {
        return LTime;
    }

    public void setLTime(String LTime) {
        this.LTime = LTime;
    }

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String department) {
        Department = department;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(StaffID);
        dest.writeString(Name);
        dest.writeString(aTime);
        dest.writeString(LTime);
        dest.writeString(Department);
        dest.writeString(type);
        dest.writeString(date);
    }
}
