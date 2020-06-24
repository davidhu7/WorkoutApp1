package com.example.workoutapp1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button savedBtn;
    private Button startBtn;

    private Workout[] workouts;

    public static final String EXTRA_WORKOUTS = "com.example1.workoutapp1.EXTRA_WORKOUTS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startBtn = findViewById(R.id.startNewButton);
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMainScreen();
            }
        });

        savedBtn = (Button) findViewById(R.id.savedButton);
        savedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSavedScreen();
            }


        });
    }

    public void openMainScreen() {
        Intent intentOpenMainScreen = new Intent(this, NewWorkoutActivity.class);
        startActivity(intentOpenMainScreen);
    }

    public void openSavedScreen() {
        Intent intentOpenSavedScreen = new Intent(this, SavedWorkoutsActivity.class);
        intentOpenSavedScreen.putExtra(EXTRA_WORKOUTS, workouts);

        startActivity(intentOpenSavedScreen);
    }

    //TODO: This method will give our array a length, based off the # of lines read in the file. for now, it is static.
    public void createArray() {
        workouts = new Workout[9];
    }

    //TODO: This method will fill up the Workout array from file read. For now, it is static.
    public void populateWorkoutArray () {
        for(int i = 0; i < workouts.length; i++ ) {
            workouts[i] = new Workout(i, i, i, i, i);
        }
    }

}
