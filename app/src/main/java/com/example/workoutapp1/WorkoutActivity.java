package com.example.workoutapp1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class WorkoutActivity extends AppCompatActivity {
    private CountDownTimer countDownTimer;
    private TextView countdown;
    private TextView exercise;
    private Button start_pause;
    private boolean timerRunning;
    private long timeLeft;
    private int isSet;
    private int workTime;
    private int restTime;
    private int cooldownTime;
    private boolean isWork;
    private int counter;
    private int exerciseNum;
    private int sets;
    private int cycles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

        Intent getWorkout = getIntent();
        Workout newWorkout = (Workout)getWorkout.getParcelableExtra(NewWorkoutActivity.EXTRA_WORKOUT);

        workTime = newWorkout.getWorkTime();
        restTime = newWorkout.getRestTime();
        cooldownTime = newWorkout.getCooldownTime();
        sets = newWorkout.getSets();
        cycles = newWorkout.getCycles();
        countdown = findViewById(R.id.Timer);
        exercise = findViewById(R.id.Exercise);
        start_pause = findViewById(R.id.Start_Pause);

        timeLeft = workTime * 1000;
        updateTime();
        isWork = true;
        counter = sets * cycles;
        exerciseNum = 1;
        isSet = 1;
        exercise.setText("Exercise " + exerciseNum);

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
                Toast.makeText(WorkoutActivity.this, "Counter: " + counter, Toast.LENGTH_LONG).show();


                updateTime();
            }

            @Override
            public void onFinish() {
                start_pause.setText("Start");
                if(counter > 0) {
                    if(isWork) {
                        timeLeft = restTime * 1000;
                        isWork = false;
                    } else {
                        timeLeft = workTime * 1000;
                        isWork = true;
                        if(counter-1 > 0) {
                            if(isSet < sets) {
                                exerciseNum++;
                                exercise.setText("Exercise " + exerciseNum);
                                isSet++;
                            }
                            else{
                                isSet = 1;
                                exerciseNum = 1;
                                exercise.setText("Exercise " + exerciseNum);
                            }
                        }
                        counter--;
                    }

                    startTime();
                }

                if(counter == 0){
                    timeLeft = cooldownTime * 1000;
                    exercise.setText("Cooldown");
                    updateTime();
                    startTime();
                    counter--;
                }



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

        @SuppressLint("DefaultLocale") String timeLeftText = String.format("%02d:%02d",minutes,seconds);
        countdown.setText(timeLeftText);
    }


    private void createCountDownTimer() {

        countDownTimer = new CountDownTimer(timeLeft, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                timeLeft = millisUntilFinished;
                updateTime();

            }

            @Override
            public void onFinish() {

            }
        };
    }

}
