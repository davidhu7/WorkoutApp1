package com.example.workoutapp1;

import android.graphics.Color;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class SavedWorkoutsAdapter extends RecyclerView.Adapter<SavedWorkoutsAdapter.MyViewHolder>{
    private Workout[] myWorkouts;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public CardView card;
        public MyViewHolder (CardView c) {
            super(c);
            card = c;
        }
    }


    //provide a constructor for the adapter
    public SavedWorkoutsAdapter(Workout[] workouts) {
        myWorkouts = workouts;
    }


    //New CardViews
    @Override
    public SavedWorkoutsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView card = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.saved_workouts, parent, false);
        MyViewHolder vh = new MyViewHolder(card);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.card.setClickable(true);
    }



    @Override
    public int getItemCount() {
        return 0;
    }
}
