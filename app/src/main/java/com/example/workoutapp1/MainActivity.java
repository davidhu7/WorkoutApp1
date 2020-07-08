package com.example.workoutapp1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.widget.Toolbar;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    private Button savedBtn;
    private Button startBtn;
    public static final String SIMPLE_WORKOUTS = "simple_workouts";
    private int numLines;
    Toolbar toolbar;

    private ArrayList<Workout> workouts = new ArrayList<>();

    File file;

    public static final String EXTRA_WORKOUTS = "com.example1.workoutapp1.EXTRA_WORKOUTS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //start a new workout button
        startBtn = findViewById(R.id.startNewButton);
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNewWorkoutScreen();
            }
        });


        //saved workouts button
        savedBtn = findViewById(R.id.savedButton);
        savedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSavedScreen();
            }


        });


        //TODO: Eventually this needs to be deleted, and the files should be written to upon saving a workout instead.
        file = new File(this.getFilesDir(), SIMPLE_WORKOUTS);
        writeToFile("Test1,20,10,5,2,1", this);
        writeToFile("Test2,10,5,2,1,5", this);
        writeToFile("Test3,1,1,2,3,5", this);


        //read in saved workouts from internal storage
        populateWorkoutArray();

        toolbar = findViewById(R.id.toolbar);
        //set it as the supportActionBar
        setSupportActionBar(toolbar);
    }

    public void openNewWorkoutScreen() {
        Intent intentNewWorkoutScreen = new Intent(this, NewWorkoutActivity.class);
        startActivity(intentNewWorkoutScreen);
    }

    public void openSavedScreen() {
        Intent intentOpenSavedScreen = new Intent(this, SavedWorkoutsActivity.class);
        intentOpenSavedScreen.putExtra(EXTRA_WORKOUTS, workouts);

        startActivity(intentOpenSavedScreen);
    }



    public void populateWorkoutArray () {

        try {
            Scanner fileIn = new Scanner(file);
            String s;
            while(fileIn.hasNextLine()) {
                s = fileIn.nextLine();
                String[] split = s.split(",");
                Workout w = new Workout(split[0], Integer.parseInt(split[1]), Integer.parseInt(split[2]), Integer.parseInt(split[3]), Integer.parseInt(split[4]), Integer.parseInt(split[5]));
                workouts.add(w);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }



    }

    public void writeToFile(String data, Context context) {
        //String existing = readFromFile(context);
        try (BufferedWriter fos = new BufferedWriter(new FileWriter(context.getFileStreamPath(SIMPLE_WORKOUTS), true))) {

            fos.write(data + "\n");


        } catch (IOException e) {
            e.printStackTrace();
        }
    }













}
