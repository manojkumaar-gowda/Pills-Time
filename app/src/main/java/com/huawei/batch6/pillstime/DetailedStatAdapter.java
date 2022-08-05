package com.huawei.batch6.pillstime;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DetailedStatAdapter extends RecyclerView.Adapter<DetailedStatAdapter.Viewholder> {
    private Context context;
    private ArrayList<DetailedStatModel> detailedStatModels;

    // Constructor
    public DetailedStatAdapter(Context context, ArrayList<DetailedStatModel> detailedStatModels) {
        this.context = context;
        this.detailedStatModels = detailedStatModels;
    }

    @NonNull
    @Override
    public DetailedStatAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // to inflate the layout for each item of recycler view.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.detailed_stat_events_layout, parent, false);
        return new Viewholder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull DetailedStatAdapter.Viewholder holder, int position) {
        // to set data to textview and imageview of each card layout
        DetailedStatModel model = detailedStatModels.get(position);
        holder.time.setText(model.getTime());
        holder.date.setText(model.getDate());
        holder.takenMeds.setText(model.getTakenMeds());
        holder.leftMeds.setText(model.getLeftMeds());
        //holder.meds.setBackgroundColor(model.getColor());

    }

    @Override
    public int getItemCount() {
        // this method is used for showing number
        // of card items in recycler view.
        return detailedStatModels.size();
    }

    // View holder class for initializing of
    // your views such as TextView and Imageview.
    public class Viewholder extends RecyclerView.ViewHolder {
        private TextView time;
        private TextView date;
        private TextView takenMeds;
        private TextView leftMeds;
        private CardView color;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            time = itemView.findViewById(R.id.timeRV1);
            date = itemView.findViewById(R.id.dateRV1);
            takenMeds = itemView.findViewById(R.id.takenMedsRV1);
            leftMeds = itemView.findViewById(R.id.leftMedsRV1);
            color = itemView.findViewById(R.id.upcoming_events);
        }
    }
}
