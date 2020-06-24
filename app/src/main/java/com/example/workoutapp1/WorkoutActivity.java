package com.example.workoutapp1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class WorkoutActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

        Intent getWorkout = getIntent();
        Workout newWorkout = (Workout)getWorkout.getSerializableExtra(NewWorkoutActivity.Extra_workout_pass);
    }


}
