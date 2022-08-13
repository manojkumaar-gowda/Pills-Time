package com.huawei.batch6.pillstime;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AddDoseAdapter extends RecyclerView.Adapter<AddDoseAdapter.Viewholder> {

    private final ArrayList<AddDoseModel> addDoseModelArrayList;

    public AddDoseAdapter(ArrayList<AddDoseModel> addDoseModelArrayList) {
        this.addDoseModelArrayList = addDoseModelArrayList;
    }

    @NonNull
    @Override
    public AddDoseAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // to inflate the layout for each item of recycler view.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.add_dose_layout, parent, false);
        return new AddDoseAdapter.Viewholder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull AddDoseAdapter.Viewholder holder, int position) {
        // to set data to the recyclable card view layout
        AddDoseModel model = addDoseModelArrayList.get(position);
        String med = model.getFullMedicineName() + " - " + model.getDose() + " " + model.getDoseUnits();
        holder.medNameWithDistributorAndDose.setText(med);
        holder.instructions.setText(model.getInstructions());
    }

    @Override
    public int getItemCount() {
        // this method is used for showing number
        // of card items in recycler view.
        return addDoseModelArrayList.size();

    }

    // View holder class for initializing of
    // your views such as TextView and Imageview.
    public static class Viewholder extends RecyclerView.ViewHolder {
        //Attributes of the Dose Model
        private final TextView medNameWithDistributorAndDose;
        private final TextView instructions;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            medNameWithDistributorAndDose = itemView.findViewById(R.id.medNameWithDistributorAndDose);
            instructions = itemView.findViewById(R.id.instructions);
        }
    }
}
