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


    private final Context context;
    private final ArrayList<DetailedStatModel> detailedStatModels;


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
        // to set data to the recyclable card view layout
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
    public static class Viewholder extends RecyclerView.ViewHolder {
        //Attributes of the DetailedStatistics Model
        private final TextView time;
        private final TextView date;
        private final TextView takenMeds;
        private final TextView leftMeds;
        private final CardView color;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            time = itemView.findViewById(R.id.timeRV);
            date = itemView.findViewById(R.id.dateRV);
            takenMeds = itemView.findViewById(R.id.takenMedsRV);
            leftMeds = itemView.findViewById(R.id.leftMedsRV);
            color = itemView.findViewById(R.id.upcoming_events);
        }
    }
}
