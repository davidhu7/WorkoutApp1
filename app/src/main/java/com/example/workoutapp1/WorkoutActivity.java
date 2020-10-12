package com.example.workoutapp1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class WorkoutActivity extends AppCompatActivity {
    private CountDownTimer countDownTimer;
    private TextView countdown;
    private TextView exercise;
    private Button start_pause;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

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
    private Workout workoutIn;
    private ArrayList<String> dataSet;
    //TODO: Add arrow buttons to be able to move to next activity / previous activity
    //TODO: Make the display more aesthetic? Add symbols
    //TODO: Notification bar and running in background once workout starts


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);
        Intent getWorkout = getIntent();
        workoutIn = getWorkout.getParcelableExtra(NewWorkoutActivity.EXTRA_WORKOUT);
        dataSet = workoutIn.workoutScheduleAsArray();


        recyclerView = findViewById(R.id.recycler_view);
        //set fixed size
        recyclerView.setHasFixedSize(true);
        // use linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        //specify adapter
        mAdapter = new WorkoutActivityAdapter(dataSet);
        recyclerView.setAdapter(mAdapter);

//        //creating ability to touch each item.
//        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(WorkoutActivity.this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                jumpToActivity(position);
//            }
//
//            @Override
//            public void onLongItemClick(View view, int position) {
//
//            }
//        }));


        workTime = workoutIn.getWorkTime();
        restTime = workoutIn.getRestTime();
        cooldownTime = workoutIn.getCooldownTime();
        sets = workoutIn.getSets();
        cycles = workoutIn.getCycles();
        countdown = findViewById(R.id.Timer);
        exercise = findViewById(R.id.Exercise);
        start_pause = findViewById(R.id.Start_Pause);

        timeLeft = workTime * 1000;
        updateTime();
        isWork = true;
        counter = sets * cycles;
        exerciseNum = 1;
        isSet = 1;

        //one time calls
        exercise.setText(dataSet.get(0));
        //nextActivity(); //called once to fit the schedule

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
        exercise.setText(dataSet.get(0)); //display next text
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
                    //dataIndex++; //go to the next data spot
                    nextActivity(); //goes to the next activity
                    startTime();

                } else if (counter == 0) {
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


    public void nextActivity() {
        if (dataSet.size() > 0) {
            dataSet.remove(0); //remove the first item in the list
            recyclerView.removeViewAt(0); //after each exercise, remove it
            mAdapter.notifyItemRemoved(0);
            mAdapter.notifyItemRangeChanged(0, dataSet.size());
            mAdapter.notifyDataSetChanged(); //update it
        }
    }

//    public void jumpToActivity(int position) {
//        try {
//            countDownTimer.cancel();
//        } catch (Exception e) { //this would only occur if the timer hasn't been started yet
//            e.printStackTrace();
//        }
//
//
//        for (int i = 0; i < position; i++) {
//            dataSet.remove(0);
//            recyclerView.removeViewAt(0);
//            mAdapter.notifyItemRemoved(0);
//            mAdapter.notifyItemRangeChanged(0, dataSet.size());
//            mAdapter.notifyDataSetChanged(); //update it
//            counter--;
//        }
//        //update the timeLeft
//        if (counter > 0) {
//            if (isWork) {
//                timeLeft = restTime * 1000;
//                isWork = false;
//            } else {
//                timeLeft = workTime * 1000;
//                isWork = true;
//            }
//            startTime();
//        } else if (counter == 0) {
//            timeLeft = cooldownTime * 1000;
//            exercise.setText("Cooldown");
//            updateTime();
//            startTime();
//            counter--;
//        }
//
//    }

}
