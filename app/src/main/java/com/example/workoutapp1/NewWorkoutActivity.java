package com.example.workoutapp1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewWorkoutActivity extends AppCompatActivity {
    /*
    public static final String Extra_work = "com.example.workoutapp1.work";
    public static final String Extra_rest = "com.example.workoutapp1.rest";
    public static final String Extra_cooldown = "com.example.workoutapp1.cooldown";
    public static final String Extra_sets = "com.example.workoutapp1.sets";
    public static final String Extra_cycles = "com.example.workoutapp1.cycles";
     */
    //Variable to pass to the next activity
    public static final String Extra_workout_pass = "com.example.workoutapp1.newWorkout";
    //Getting the numbers that the user inputs
    private EditText workTime;
    private EditText restTime;
    private EditText cycleTime;
    private EditText setsTime;
    private EditText cooldownTime;
    //Setting variables to pass onto the next activity
    private String id;
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
        //Makes sure all of the boxes are filled in before moving onto the next
        //activity
        workTime = findViewById(R.id.workNum);
        workTime.addTextChangedListener(checkInput);

        restTime = findViewById(R.id.restNum);
        restTime.addTextChangedListener(checkInput);

        cycleTime = findViewById(R.id.cycleNum);
        cycleTime.addTextChangedListener(checkInput);

        setsTime = findViewById(R.id.setsNum);
        setsTime.addTextChangedListener(checkInput);

        cooldownTime = findViewById(R.id.cooldownNum);
        cooldownTime.addTextChangedListener(checkInput);

        // totalTime = cooldownInt + setsInt + cycleInt + restInt + workInt;

        startButton = (Button) findViewById(R.id.startWorkout);
        //Opens the next activity by clicking start
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openWorkOut();
            }


        });


    }
    //This method makes sure the boxes all have an input before continuing
    private TextWatcher checkInput = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String testWorkout = workTime.getText().toString().trim();
            String testRestTime = restTime.getText().toString().trim();
            String testCycle = cycleTime.getText().toString().trim();
            String testSetsTime = setsTime.getText().toString().trim();
            String testCooldown = cooldownTime.getText().toString().trim();
            //If they are not empty then go to the next activity
            startButton.setEnabled(!testWorkout.isEmpty() && !testRestTime.isEmpty()
                    && !testCycle.isEmpty() && !testSetsTime.isEmpty() && !testCooldown.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    public void openWorkOut(){
        Intent intentOpenWorkout = new Intent(this, WorkoutActivity.class);
        //Gets the data ready to be sent to the next activity
        workInt = Integer.parseInt(workTime.getText().toString());
        restInt = Integer.parseInt(restTime.getText().toString());
        cycleInt = Integer.parseInt(cycleTime.getText().toString());
        setsInt = Integer.parseInt(setsTime.getText().toString());
        cooldownInt = Integer.parseInt(cooldownTime.getText().toString());

        id = "newWorkout";

        Workout myworkout = new Workout(id, workInt, restInt, cooldownInt, setsInt, cycleInt);
        /*
        intentOpenWorkout.putExtra(Extra_work,workInt);
        intentOpenWorkout.putExtra(Extra_rest,restInt);
        intentOpenWorkout.putExtra(Extra_cooldown,cooldownInt);
        intentOpenWorkout.putExtra(Extra_sets,setsInt);
        intentOpenWorkout.putExtra(Extra_cycles,cycleInt);
        */
        //Sends the data to the next activity
        intentOpenWorkout.putExtra(Extra_workout_pass, myworkout);
        startActivity(intentOpenWorkout);

        }

    }

