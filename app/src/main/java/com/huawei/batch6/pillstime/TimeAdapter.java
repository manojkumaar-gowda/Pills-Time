package com.huawei.batch6.pillstime;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TimeAdapter extends RecyclerView.Adapter<TimeAdapter.Viewholder> {

    private final ArrayList<TimeModel> timeModelArrayList;

    public TimeAdapter(ArrayList<TimeModel> timeModelArrayList) {
        this.timeModelArrayList = timeModelArrayList;
    }

    @NonNull
    @Override
    public TimeAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // to inflate the layout for each item of recycler view.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.add_time_layout, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TimeAdapter.Viewholder holder, int position) {
        // to set data to the recyclable card view layout

        TimeModel model = timeModelArrayList.get(position);
        String s = "" + model.getTime();
        holder.time.setText(s);
    }

    @Override
    public int getItemCount() {
        // this method is used for showing number
        // of card items in recycler view.
        return timeModelArrayList.size();
    }

    // View holder class for initializing of
    // your views such as TextView and Imageview.
    public static class Viewholder extends RecyclerView.ViewHolder {
        //Attributes of the Time Model
        private final TextView time;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            time = itemView.findViewById(R.id.timeView);
        }
    }
}
