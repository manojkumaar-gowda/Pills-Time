package com.huawei.batch6.pillstime;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TimeAdapter extends RecyclerView.Adapter<TimeAdapter.Viewholder> {

    private final ArrayList<TimeModel> timeModelArrayList;

    // Constructor
    public TimeAdapter(ArrayList<TimeModel> timeModelArrayList) {
        this.timeModelArrayList = timeModelArrayList;
    }

    @NonNull
    @Override
    public TimeAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // to inflate the layout for each item of recycler view.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_card_view_trial, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TimeAdapter.Viewholder holder, int position) {
        // to set data to textview and imageview of each card layout
        TimeModel model = timeModelArrayList.get(position);
        holder.courseRatingTV.setText("" + model.getCourse_rating());
    }

    @Override
    public int getItemCount() {
        // this method is used for showing number
        // of card items in recycler view.
        return timeModelArrayList.size();
    }

    // View holder class for initializing of
    // your views such as TextView and Imageview.
    public class Viewholder extends RecyclerView.ViewHolder {
        private ImageView courseIV;
        private TextView courseNameTV, courseRatingTV;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            courseRatingTV = itemView.findViewById(R.id.timeView);
        }
    }
}
