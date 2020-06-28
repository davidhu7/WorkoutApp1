package com.example.workoutapp1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class WorkoutActivity extends AppCompatActivity {
    private CountDownTimer countDownTimer;
    private TextView countdown;
    private Button start_pause;
    private boolean timerRunning;
    private long timeLeft;

    private int workTime;
    private int restTime;
    private int cooldownTime;
    private boolean isWork;
    private int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

        Intent getWorkout = getIntent();
        Workout newWorkout = getWorkout.getParcelableExtra(NewWorkoutActivity.EXTRA_WORKOUT);

        workTime = newWorkout.getWorkTime();
        restTime = newWorkout.getRestTime();
        cooldownTime = newWorkout.getCooldownTime();
        final int sets = newWorkout.getSets();
        final int cycles = newWorkout.getCycles();
        countdown = findViewById(R.id.Timer);
        start_pause = findViewById(R.id.Start_Pause);
        timeLeft = workTime * 1000;
        updateTime();
        isWork = true;
        counter = sets * cycles;

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
                        counter--;
                    }
                    startTime();
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
