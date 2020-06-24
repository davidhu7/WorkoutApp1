package com.example.workoutapp1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewWorkoutActivity extends AppCompatActivity {

    private EditText workTime;
    private EditText restTime;
    private EditText cycleTime;
    private EditText setsTime;
    private EditText cooldownTime;
    private int totalTime;
    private int workInt;
    private int restInt;
    private int cycleInt;
    private int setsInt;
    private int cooldownInt;
    private Button startButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_workouts);

        workTime = findViewById(R.id.workNum);
        // workInt = Integer.parseInt(workTime.getText().toString());

        restTime = findViewById(R.id.restNum);
        // restInt = Integer.parseInt(restTime.getText().toString());

        cycleTime = findViewById(R.id.cycleNum);
        // cycleInt = Integer.parseInt(cycleTime.getText().toString());

        setsTime = findViewById(R.id.setsNum);
        //  setsInt = Integer.parseInt(setsTime.getText().toString());

        cooldownTime = findViewById(R.id.cooldownNum);
        // cooldownInt = Integer.parseInt(cooldownTime.getText().toString());

        // totalTime = cooldownInt + setsInt + cycleInt + restInt + workInt;

        startButton = (Button) findViewById(R.id.startNewButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openWorkOut();
            }
        });
    }

    public void openWorkOut(){
        Intent intentOpenWorkout = new Intent(this, Workout.class);
        startActivity(intentOpenWorkout);
        }

    }

