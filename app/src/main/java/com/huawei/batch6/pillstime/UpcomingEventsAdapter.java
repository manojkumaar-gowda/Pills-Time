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

public class UpcomingEventsAdapter extends RecyclerView.Adapter<UpcomingEventsAdapter.Viewholder> {
    private final Context context;
    private final ArrayList<UpcomingEventsModel> upcomingEventsModels;


    public UpcomingEventsAdapter(Context context, ArrayList<UpcomingEventsModel> upcomingEventsModels) {
        this.context = context;
        this.upcomingEventsModels = upcomingEventsModels;
    }

    @NonNull
    @Override
    public UpcomingEventsAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // to inflate the layout for each item of recycler view.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.upcoming_events_layout, parent, false);
        return new Viewholder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull UpcomingEventsAdapter.Viewholder holder, int position) {
        // to set data to the recyclable card view layout
        UpcomingEventsModel model = upcomingEventsModels.get(position);
        holder.time.setText(model.getTime());
        holder.date.setText(model.getDate());
        holder.meds.setText(model.getMeds());
        //holder.meds.setBackgroundColor(model.getColor());

    }

    @Override
    public int getItemCount() {
        // this method is used for showing number
        // of card items in recycler view.
        return upcomingEventsModels.size();
    }

    // View holder class for initializing of
    // your views such as TextView and Imageview.
    public static class Viewholder extends RecyclerView.ViewHolder {
        //Attributes of the Upcoming Events Model
        private final TextView time;
        private final TextView date;
        private final TextView meds;
        private final CardView color;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            time = itemView.findViewById(R.id.timeRV);
            date = itemView.findViewById(R.id.dateRV);
            meds = itemView.findViewById(R.id.medsRV);
            color = itemView.findViewById(R.id.upcoming_events);
        }
    }
}
