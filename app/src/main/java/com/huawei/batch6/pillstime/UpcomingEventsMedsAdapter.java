package com.huawei.batch6.pillstime;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UpcomingEventsMedsAdapter  extends RecyclerView.Adapter<UpcomingEventsMedsAdapter.Viewholder>  {
    private Context context;
    private ArrayList<UpcomingEventsMedsModel> upcomingEventsMedsModel;


    // Constructor
    public UpcomingEventsMedsAdapter(Context context, ArrayList<UpcomingEventsMedsModel> upcomingEventsMedsModel) {
        this.context = context;
        this.upcomingEventsMedsModel = upcomingEventsMedsModel;
    }

    @NonNull
    @Override
    public UpcomingEventsMedsAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // to inflate the layout for each item of recycler view.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.meds_layout, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UpcomingEventsMedsAdapter.Viewholder holder, int position) {
        // to set data to textview and imageview of each card layout
        UpcomingEventsMedsModel model = upcomingEventsMedsModel.get(position);
        holder.brand.setText(model.getBrand());
        holder.medicine.setText("" + model.getMedicine());
        holder.dose.setText("" + model.getDose());
    }

    @Override
    public int getItemCount() {
        // this method is used for showing number
        // of card items in recycler view.
        return upcomingEventsMedsModel.size();
    }

    // View holder class for initializing of
    // your views such as TextView and Imageview.
    public class Viewholder extends RecyclerView.ViewHolder {
        private TextView brand, medicine,dose;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            brand = itemView.findViewById(R.id.brandInRV);
            medicine = itemView.findViewById(R.id.medInRV);
            dose = itemView.findViewById(R.id.doseInRV);
        }
    }
}
