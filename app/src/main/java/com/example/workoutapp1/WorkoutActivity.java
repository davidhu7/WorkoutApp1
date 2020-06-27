package com.example.workoutapp1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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
    private long timeLeft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

        Intent getWorkout = getIntent();
        Workout newWorkout = (Workout)getWorkout.getParcelableExtra(NewWorkoutActivity.EXTRA_WORKOUT);

        final int workTime = newWorkout.getWorkTime();
        final int restTime = newWorkout.getRestTime();
        final int cooldownTime = newWorkout.getCooldownTime();
        final int sets = newWorkout.getSets();
        final int cycles = newWorkout.getCycles();
        countdown = findViewById(R.id.Timer);
        start_pause = findViewById(R.id.Start_Pause);
        timeLeft = workTime * 1000;
        updateTime();

        start_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (timerRunning){
                    stopTime();
                }
                else{
                    startTime();
                    //TotalWorkout(workTime,restTime,cooldownTime,sets,cycles);
                }
            }
        });


        timeLeft = cooldownTime * 1000;
        updateTime();

    }

    public void TotalWorkout(int workT, int restT, int cooldownT, int setsNum, int cyclesNum){
        workT = workT * 1000;
        restT = restT * 1000;
        cooldownT = cooldownT * 1000;
        for(int i = 0; i < cyclesNum * setsNum; i++){
            timeLeft = workT;
            updateTime();
            startTime();
            //timeLeft = restT;
            //updateTime();
            //startTime();
        }

    }

    public void startTime(){
        countDownTimer = new CountDownTimer(timeLeft, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeft = millisUntilFinished;
                updateTime();
            }

            @Override
            public void onFinish() {
                //start_pause.setText("Start");
            }
        }.start();
        timerRunning = true;
        start_pause.setText("Pause");
    }

    public void stopTime(){
        countDownTimer.cancel();
        start_pause.setText("Start");
        timerRunning = false;
    }

    public void updateTime(){
        int minutes = (int) (timeLeft/1000)/60;
        int seconds = (int) (timeLeft/1000) % 60000;
        /*
        String timeLeftText;
        timeLeftText = ""+ minutes;
        timeLeftText += ":";
        if(seconds < 10) {
            timeLeftText += "0";
        }
        timeLeftText += seconds;
        */

        String timeLeftText = String.format("%02d:%02d",minutes,seconds);
        countdown.setText(timeLeftText);
    }

}
