package com.example.android.valduezabudgetmonitoring;

import java.util.Date;
import java.util.UUID;

public class Expense {
    private UUID mId;
    private String mTitle;
    private Date mDate;
    private int mAmount;

    public Expense(){
        mId = UUID.randomUUID();
        mDate = new Date();
    }

    public UUID getmId() {
        return mId;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public Date getmDate() {
        return mDate;
    }

    public void setmDate(Date mDate) {
        this.mDate = mDate;
    }

    public void setmAmount(int amount){ this.mAmount = amount; }

    public int getmAmount(){ return mAmount; }
}