package com.huawei.batch6.pillstime;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class DashboardFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    public DashboardFragment() {

    }


    public static DashboardFragment newInstance(String param1, String param2) {
        DashboardFragment fragment = new DashboardFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            String mParam1 = getArguments().getString(ARG_PARAM1);
            String mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_dashboard, container, false);
        FloatingActionButton fab = v.findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Intent send = new Intent(getActivity(),AddNewMedication.class);
            startActivity(send);
        });


        //Pills
        RecyclerView refillMedsRV = v.findViewById(R.id.pillsLeftRV);
        ArrayList<RefillPillsModel> refillMedsModelArrayList;
        // here we have created new array list and added data to it.
        refillMedsModelArrayList = new ArrayList<>();
        refillMedsModelArrayList.add(new RefillPillsModel("8\nLeft","Biocon","Tacrolimus","750mg",getRandomColor(DashboardFragment.this)));
        refillMedsModelArrayList.add(new RefillPillsModel("8\nLeft","Biocon","Prograf","750mg",getRandomColor(DashboardFragment.this)));
        refillMedsModelArrayList.add(new RefillPillsModel("8\nLeft","Biocon","Wysolone","750mg",getRandomColor(DashboardFragment.this)));
        refillMedsModelArrayList.add(new RefillPillsModel("8\nLeft","Biocon","Cellcept","750mg",getRandomColor(DashboardFragment.this)));
        refillMedsModelArrayList.add(new RefillPillsModel("8\nLeft","Biocon","Tacrolimus","750mg",getRandomColor(DashboardFragment.this)));
        refillMedsModelArrayList.add(new RefillPillsModel("8\nLeft","Biocon","Prograf","750mg",getRandomColor(DashboardFragment.this)));
        refillMedsModelArrayList.add(new RefillPillsModel("8\nLeft","Biocon","Wysolone","750mg",getRandomColor(DashboardFragment.this)));
        refillMedsModelArrayList.add(new RefillPillsModel("8\nLeft","Biocon","Cellcept","750mg",getRandomColor(DashboardFragment.this)));


        // we are initializing our adapter class and passing our arraylist to it.
        RefillPillsAdapter courseAdapter = new RefillPillsAdapter(null, refillMedsModelArrayList);

        // below line is for setting a layout manager for our recycler view.
        // here we are creating vertical list so we will provide orientation as vertical
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(null, LinearLayoutManager.HORIZONTAL, false);

        // in below two lines we are setting layoutmanager and adapter to our recycler view.
        refillMedsRV.setLayoutManager(linearLayoutManager);
        refillMedsRV.setAdapter(courseAdapter);


        //Pills
        refillMedsRV = v.findViewById(R.id.medsCardView);
        ArrayList<UpcomingEventsModel> upcomingEventsModels;
        // here we have created new array list and added data to it.
        upcomingEventsModels = new ArrayList<>();
        upcomingEventsModels.add(new UpcomingEventsModel("8.00 AM","Biocon-Tacrolimus-750mg\nBiocon-Tacrolimus-750mg\nBiocon-Tacrolimus-750mg",getRandomColor(DashboardFragment.this)));
        upcomingEventsModels.add(new UpcomingEventsModel("8.10 AM","Biocon-Tacrolimus-750mg\nBiocon-Tacrolimus-750mg\nBiocon-Tacrolimus-750mg",getRandomColor(DashboardFragment.this)));
        upcomingEventsModels.add(new UpcomingEventsModel("3.00 PM","Biocon-Tacrolimus-750mg\nBiocon-Tacrolimus-750mg\nBiocon-Tacrolimus-750mg",getRandomColor(DashboardFragment.this)));
        upcomingEventsModels.add(new UpcomingEventsModel("10.00 PM","Biocon-Tacrolimus-750mg\nBiocon-Tacrolimus-750mg\nBiocon-Tacrolimus-750mg",getRandomColor(DashboardFragment.this)));


        // we are initializing our adapter class and passing our arraylist to it.
        UpcomingEventsAdapter upcomingEventsAdapter = new UpcomingEventsAdapter(null, upcomingEventsModels);

        // below line is for setting a layout manager for our recycler view.
        // here we are creating vertical list so we will provide orientation as vertical
        linearLayoutManager = new LinearLayoutManager(null, LinearLayoutManager.HORIZONTAL, false);

        // in below two lines we are setting layoutmanager and adapter to our recycler view.
        refillMedsRV.setLayoutManager(linearLayoutManager);
        refillMedsRV.setAdapter(upcomingEventsAdapter);



        return v;
    }


    private static int getRandomColor(DashboardFragment context) {
        int[] colors;
        if (Math.random() >= 0.6) {
            colors = context.getResources().getIntArray(R.array.note_accent_colors);
        } else {
            colors = context.getResources().getIntArray(R.array.note_accent_colors);
        }
        return colors[((int) (Math.random() * colors.length))];
    }


}