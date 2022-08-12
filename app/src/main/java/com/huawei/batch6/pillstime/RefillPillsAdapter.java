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

public class RefillPillsAdapter extends RecyclerView.Adapter<RefillPillsAdapter.Viewholder> {

        private final Context context;
        private final ArrayList<RefillPillsModel> refillModelArrayList;

        public RefillPillsAdapter(Context context, ArrayList<RefillPillsModel> refillModelArrayList) {
            this.context = context;
            this.refillModelArrayList = refillModelArrayList;
        }

        @NonNull
        @Override
        public RefillPillsAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            // to inflate the layout for each item of recycler view.
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.refill_meds_layout, parent, false);
            return new Viewholder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull RefillPillsAdapter.Viewholder holder, int position) {
            // to set data to the recyclable card view layout
            RefillPillsModel model = refillModelArrayList.get(position);
            holder.pillsLeft.setText(model.getPillsLeft());
            holder.supplier.setText(model.getSupplier());
            holder.pillName.setText(model.getPillName());
            holder.dose.setText(model.getDose());
            holder.color.setCardBackgroundColor(model.getColor());
        }

        @Override
        public int getItemCount() {
            // this method is used for showing number
            // of card items in recycler view.
            return refillModelArrayList.size();
        }

        // View holder class for initializing of
        // your views such as TextView and Imageview.
        public static class Viewholder extends RecyclerView.ViewHolder {

            //Attributes of the DetailedStatistics Model
            private final TextView pillsLeft;
            private final TextView supplier;
            private final TextView pillName;
            private final TextView dose;
            private final CardView color;
            public Viewholder(@NonNull View itemView) {
                super(itemView);
                pillsLeft = itemView.findViewById(R.id.pillsLeft);
                supplier = itemView.findViewById(R.id.distributor);
                pillName = itemView.findViewById(R.id.medicineName);
                dose = itemView.findViewById(R.id.dose);
                color = itemView.findViewById(R.id.pillsLeftContainer);
            }
        }

}
