package com.huawei.batch6.pillstime;


import android.content.Intent;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class DashboardFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    //User Details
    private String accountHolderName;

    //Floating action Button
    private FloatingActionButton floatingActionButton;

    //Refill Medicines Recycler View
    private RecyclerView refillMedsRV;

    //Upcoming Events Recycler View
    private RecyclerView upcomingEvents;

    //Locate hospitals and pharmacies
    private TextView locateHospitalsAndPharmacies;

    //user name
    private TextView userName;

    //no_refill_reminders_container
    private ConstraintLayout no_refill_reminders_container;
    //meds_to_recycle_container
    private LinearLayout meds_to_recycle_container;
    //upcoming_events_container
    private ConstraintLayout upcoming_events_container;

    //Default constructor
    public DashboardFragment() {
        // Required empty public constructor
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


        //Locate hospitals and pharmacies
        locateHospitalsAndPharmacies = v.findViewById(R.id.locate_hospitals_and_pharmacy);
        //Locate hospitals and pharmacies event handling
        locateHospitalsAndPharmacies.setOnClickListener(view -> locateHospitalsAndPharmaciesFunction());


        //set user details
        setUserDetails();

        //setUserName
        userName = v.findViewById(R.id.Name);
        userName.setText(accountHolderName);

        //Floating action Button
        floatingActionButton = v.findViewById(R.id.fab);
        //FloatingActionButton event handling
        floatingActionButton.setOnClickListener(view -> floatingActionButtonFunction());


        //Refill Medicines and upcoming events
        refillMedsAndUpcomingEventsRecyclerViewFunction(v);


        return v;
    }

    private void setUserDetails() {
        accountHolderName = "Michella";
    }

    //Locate hospitals and pharmacies Functionality
    private void locateHospitalsAndPharmaciesFunction() {
        Toast.makeText(getContext(), "Locate Hospitals and Pharamacies", Toast.LENGTH_SHORT).show();
    }


    //Floating action Button functionality
    private void floatingActionButtonFunction() {
        Intent send = new Intent(getActivity(), AddNewMedication.class);
        startActivity(send);
    }

    //Refill Medicines Recycler View functionality
    private void refillMedsAndUpcomingEventsRecyclerViewFunction(View v) {
        //link containers
        no_refill_reminders_container = v.findViewById(R.id.no_refill_reminders_container);
        meds_to_recycle_container = v.findViewById(R.id.meds_to_recycle_container);
        upcoming_events_container = v.findViewById(R.id.upcoming_events_container);

        //Refill meds
        refillMedsRV = v.findViewById(R.id.pillsLeftRV);
        ArrayList<RefillPillsModel> refillMedsModelArrayList;
        // here we have created new array list and added data to it.
        refillMedsModelArrayList = new ArrayList<>();
        refillMedsModelArrayList.add(new RefillPillsModel("8\nLeft", "Biocon", "Tacrolimus", "750mg", getRandomColor(DashboardFragment.this)));
        refillMedsModelArrayList.add(new RefillPillsModel("8\nLeft", "Biocon", "Prograf", "750mg", getRandomColor(DashboardFragment.this)));
        refillMedsModelArrayList.add(new RefillPillsModel("8\nLeft", "Biocon", "Wysolone", "750mg", getRandomColor(DashboardFragment.this)));
        refillMedsModelArrayList.add(new RefillPillsModel("8\nLeft", "Biocon", "Cellcept", "750mg", getRandomColor(DashboardFragment.this)));
        //refillMedsModelArrayList.clear();
        // we are initializing our adapter class and passing our arraylist to it.
        RefillPillsAdapter refillPillsAdapter = new RefillPillsAdapter(null, refillMedsModelArrayList);

        // below line is for setting a layout manager for our recycler view.
        // here we are creating horizontal list so we will provide orientation as horizontal
        LinearLayoutManager linearLayoutManagerForRefillMeds = new LinearLayoutManager(null, LinearLayoutManager.HORIZONTAL, false);

        // in below two lines we are setting layoutmanager and adapter to our recycler view.
        refillMedsRV.setLayoutManager(linearLayoutManagerForRefillMeds);
        refillMedsRV.setAdapter(refillPillsAdapter);


        //upcoming events
        upcomingEvents = v.findViewById(R.id.medsCardView);
        ArrayList<UpcomingEventsModel> upcomingEventsModelArrayList;
        // here we have created new array list and added data to it.
        upcomingEventsModelArrayList = new ArrayList<>();
        upcomingEventsModelArrayList.add(new UpcomingEventsModel("08.00 AM", getTodaysDate(), "Biocon-Tacrolimus-750mg\nBiocon-Tacrolimus-750mg\nBiocon-Tacrolimus-750mg", getRandomColor(DashboardFragment.this)));
        upcomingEventsModelArrayList.add(new UpcomingEventsModel("08.10 AM", getTodaysDate(), "Biocon-Tacrolimus-750mg\nBiocon-Tacrolimus-750mg\nBiocon-Tacrolimus-750mg", getRandomColor(DashboardFragment.this)));
        upcomingEventsModelArrayList.add(new UpcomingEventsModel("03.00 PM", getTodaysDate(), "Biocon-Tacrolimus-750mg\nBiocon-Tacrolimus-750mg\nBiocon-Tacrolimus-750mg", getRandomColor(DashboardFragment.this)));
        upcomingEventsModelArrayList.add(new UpcomingEventsModel("10.00 PM", getTodaysDate(), "Biocon-Tacrolimus-750mg\nBiocon-Tacrolimus-750mg\nBiocon-Tacrolimus-750mg", getRandomColor(DashboardFragment.this)));
        //upcomingEventsModelArrayList.clear();
        // we are initializing our adapter class and passing our arraylist to it.
        UpcomingEventsAdapter upcomingEventsAdapter = new UpcomingEventsAdapter(null, upcomingEventsModelArrayList);

        // below line is for setting a layout manager for our recycler view.
        // here we are creating vertical list so we will provide orientation as vertical
        LinearLayoutManager linearLayoutManagerForUpcomingEvents = new LinearLayoutManager(null, LinearLayoutManager.HORIZONTAL, false);

        // in below two lines we are setting layoutmanager and adapter to our recycler view.
        upcomingEvents.setLayoutManager(linearLayoutManagerForUpcomingEvents);
        upcomingEvents.setAdapter(upcomingEventsAdapter);

        //both not empty - display refill and upcoming recycler view
        if (!refillMedsModelArrayList.isEmpty() && !upcomingEventsModelArrayList.isEmpty()) {
            no_refill_reminders_container.setVisibility(View.GONE);
            meds_to_recycle_container.setVisibility(View.VISIBLE);
            upcoming_events_container.setVisibility(View.VISIBLE);
        }//refill meds empty - display  upcoming recycler view alone
        else if (refillMedsModelArrayList.isEmpty() && !upcomingEventsModelArrayList.isEmpty()) {
            no_refill_reminders_container.setVisibility(View.GONE);
            meds_to_recycle_container.setVisibility(View.GONE);
            upcoming_events_container.setVisibility(View.VISIBLE);
        }//upcoming events empty - display refill recycler view alone
        else if (!refillMedsModelArrayList.isEmpty() && upcomingEventsModelArrayList.isEmpty()) {
            no_refill_reminders_container.setVisibility(View.GONE);
            meds_to_recycle_container.setVisibility(View.VISIBLE);
            upcoming_events_container.setVisibility(View.GONE);
        }//both empty - hide refill and upcoming recycler view and display the nothing to show image
        else {
            no_refill_reminders_container.setVisibility(View.VISIBLE);
            meds_to_recycle_container.setVisibility(View.GONE);
            upcoming_events_container.setVisibility(View.GONE);
        }

    }


    //get Current Date
    private String getTodaysDate() {
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        return df.format(c);
    }


    //getRandomColor functionality
    private static int getRandomColor(DashboardFragment context) {
        int[] colors;
        if (Math.random() >= 0.6) {
            colors = context.getResources().getIntArray(R.array.note_accent_colors);
        } else {
            colors = context.getResources().getIntArray(R.array.note_neutral_colors);
        }
        return colors[((int) (Math.random() * colors.length))];
    }


}