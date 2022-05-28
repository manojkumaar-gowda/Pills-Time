package com.huawei.batch6.pillstime;

import androidx.cardview.widget.CardView;

public class UpcomingEventsModel {
    private String time;
    private CardView meds;

    public UpcomingEventsModel(String time, CardView meds) {
        this.time = time;
        this.meds = meds;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public CardView getMeds() {
        return meds;
    }

    public void setMeds(CardView meds) {
        this.meds = meds;
    }


}
