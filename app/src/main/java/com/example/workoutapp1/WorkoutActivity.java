package com.example.workoutapp1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class WorkoutActivity extends AppCompatActivity {
    private CountDownTimer countDownTimer;
    private TextView countdown;
    private Button start_pause;
    private boolean timerRunning;
    private long input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

        Intent getWorkout = getIntent();
        Workout newWorkout = (Workout)getWorkout.getParcelableExtra(NewWorkoutActivity.Extra_workout_pass);

        int workTime = newWorkout.getWorkTime();
        int restTime = newWorkout.getRestTime();
        int cooldownTime = newWorkout.getCooldownTime();
        int sets = newWorkout.getSets();
        int cycles = newWorkout.getCycles();
        countdown = findViewById(R.id.workNum);
        input = workTime;
        start_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startStop();
            }
        });

    }

    public void startStop(){
        if (timerRunning){
            stopTime();
        }
        else{
            startTime();
        }
    }

    public void startTime(){
        countDownTimer = new CountDownTimer(input, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                input = millisUntilFinished;
                updateTime();
            }

            @Override
            public void onFinish() {

            }
        }.start();
        timerRunning = true;
    }

    public void stopTime(){
        countDownTimer.cancel();
        timerRunning = false;
    }

    public void updateTime(){
        int minutes = (int) input/60000;
        int seconds = (int) input % 60000 /1000;

        String timeLeftText;
        timeLeftText = ""+ minutes;
        timeLeftText += ":";
        if(seconds < 10) {
            timeLeftText += "0";
        }
        timeLeftText += seconds;

        countdown.setText(timeLeftText);
    }

}
