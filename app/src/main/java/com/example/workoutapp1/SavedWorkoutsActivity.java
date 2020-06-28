package com.example.workoutapp1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TextView;
import android.widget.Toast;


import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class SavedWorkoutsActivity extends AppCompatActivity {

    private LinearLayout sLinearLayout;
    private CardView[] cardViews;
    private Workout[] workouts;
    private Toolbar toolbar;

    private LinearLayout.LayoutParams params;
    private ViewGroup.LayoutParams cardParams;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.saved_workouts);

        //get intent from mainactivity
        Intent intent = getIntent();
        //obtain the arrayList of workouts from the Main Activity
        ArrayList<Parcelable> parcelableArrayExtra = intent.getParcelableArrayListExtra(MainActivity.EXTRA_WORKOUTS);
        workouts = new Workout[parcelableArrayExtra.size()];
        //copy the ArrayList to our workouts array
        System.arraycopy(parcelableArrayExtra.toArray(), 0, workouts, 0, parcelableArrayExtra.size());
        //create a reference for our LinearLayout
        sLinearLayout = findViewById(R.id.viewList);
        //create a reference to our Toolbar
        toolbar = findViewById(R.id.toolbar);
        //set it as the supportActionBar
        setSupportActionBar(toolbar);






        // Set the CardView layoutParams
        params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        //set margins for the cardviews
        params.setMargins(0, 10, 0, 40);

        populateCardViews(); //fill the cardview array
        populateLayout();   //fill the layout with cardviews


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar, menu);
        return true;
    }

    private void populateLayout() {
        for (CardView cardView : cardViews) {
            sLinearLayout.addView(cardView);

        }

    }
    //TODO: Make the textView display more meaningful information
    private void populateCardViews() {
        cardViews = new CardView[workouts.length];
        for(int i = 0; i < workouts.length; i++) {
            CardView card = new CardView(this);
            card.setClickable(true);
            card.setLayoutParams(params);
            // Set CardView corner radius
            card.setRadius(20);
            // Set cardView content padding
            card.setContentPadding(20, 20, 20, 20);
            // Set a background color for CardView
            card.setCardBackgroundColor(Color.BLACK);
            // Set the CardView maximum elevation
            card.setMaxCardElevation(50);
            // Set CardView elevation
            card.setCardElevation(50);


            // Initialize new TextView to put in CardView

            TextView tv = new TextView(this);
            tv.setLayoutParams(params);
            tv.setText("Name: "+ workouts[i].getName());
            tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
            tv.setTextColor(Color.WHITE);
            tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

            card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = ((ViewGroup) view.getParent()).indexOfChild(view);
                    openWorkout(pos);
                }
            });

            card.addView(tv);
            cardViews[i] = card;
        }


    }
    public void openWorkout(int workoutIndex) {
        Intent intent = new Intent(this, WorkoutActivity.class);
        intent.putExtra(NewWorkoutActivity.EXTRA_WORKOUT, workouts[workoutIndex] );
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_newWorkout:
                Intent intentNewWorkoutScreen = new Intent(this, NewWorkoutActivity.class);
                startActivity(intentNewWorkoutScreen);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
