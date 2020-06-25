package com.example.workoutapp1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class SavedWorkoutsActivity extends AppCompatActivity {

    private LinearLayout sLinearLayout;
    private CardView[] cardViews;
    private Workout[] workouts;

    private ViewGroup.LayoutParams params;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.saved_workouts);
        Intent intent = getIntent();
        Parcelable[] parcelableArrayExtra = intent.getParcelableArrayExtra(MainActivity.EXTRA_WORKOUTS);
        workouts = new Workout[parcelableArrayExtra.length];
        System.arraycopy(parcelableArrayExtra, 0, workouts, 0, parcelableArrayExtra.length);
        sLinearLayout = findViewById(R.id.viewList);



//        sLinearLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int pos = ((ViewGroup) view.getParent()).indexOfChild(view);
//                Toast.makeText(SavedWorkoutsActivity.this, "You clicked on " + pos, Toast.LENGTH_SHORT).show();
//
//
//            }
//        });


        // Set the CardView layoutParams
        params = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );

        populateCardViews();
        populateLayout();


    }
    //TODO: this method will populate the linear layout, filling the list.
    private void populateLayout() {

        for (CardView cardView : cardViews) {
            sLinearLayout.addView(cardView);
        }

    }
    //TODO: this method will fill up the CardViews array with an array of workouts. Make it look nicer
    //TODO: Make the textView display more meaningful information
    private void populateCardViews() {
        cardViews = new CardView[workouts.length];
        for(int i = 0; i < workouts.length; i++) {
            CardView card = new CardView(this);
            card.setClickable(true);
            card.setLayoutParams(params);
            // Set CardView corner radius
            card.setRadius(9);
            // Set cardView content padding
            card.setContentPadding(15, 15, 15, 15);
            // Set a background color for CardView
            card.setCardBackgroundColor(Color.BLUE);
            // Set the CardView maximum elevation
            card.setMaxCardElevation(50);
            // Set CardView elevation
            card.setCardElevation(25);
            // Initialize a new TextView to put in CardView

            TextView tv = new TextView(this);
            tv.setLayoutParams(params);
            tv.setText("Workout WorkTime: " + workouts[i].getWorkTime());
            tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 30);
            tv.setTextColor(Color.WHITE);

            card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = ((ViewGroup) view.getParent()).indexOfChild(view);
                    Toast.makeText(SavedWorkoutsActivity.this, "You clicked on " + pos, Toast.LENGTH_SHORT).show();
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

}
