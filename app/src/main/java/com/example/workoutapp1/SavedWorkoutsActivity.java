package com.example.workoutapp1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class SavedWorkoutsActivity extends AppCompatActivity {

    private LinearLayout sLinearLayout;
    private CardView[] cardViews;
    private Workout[] workouts;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.saved_workouts);
        Intent intent = getIntent();



        sLinearLayout = findViewById(R.id.viewList);
        CardView card = new CardView(this);

        // Set the CardView layoutParams
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );


    }
    //TODO: this method will populate the linear layout, filling the list.
    private void populateLayout() {

    }
    //TODO: this method will fill up the CardViews array with an array of workouts.
    private void populateCardViews(Workout[] workouts) {


    }

}
