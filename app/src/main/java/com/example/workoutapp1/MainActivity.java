package com.example.workoutapp1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.view.MenuItem;

import androidx.appcompat.widget.Toolbar;


import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    //TODO: Create complex workout

    Toolbar toolbar;

    public ArrayList<Workout> workouts = new ArrayList<>();

    public File file;

    public static final String EXTRA_WORKOUTS = "com.example1.workoutapp1.EXTRA_WORKOUTS";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //start a new workout roundedbutton
        Button startBtn = findViewById(R.id.startNewButton);
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(MainActivity.this, view);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.new_complex_workout:
                                //open a complex workout creation screen
                                return true;
                            case R.id.new_simple_workout:
                                openNewWorkoutScreen();

                                return true;
                            default:
                                return false;
                        }
                    }
                });
                popupMenu.inflate(R.menu.new_workout_menu);
                popupMenu.show();

                //  openNewWorkoutScreen();
            }
        });
        //saved workouts roundedbutton
        Button savedBtn = findViewById(R.id.savedButton);
        savedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSavedScreen();
            }


        });
        //read in saved workouts from internal storage
        toolbar = findViewById(R.id.toolbar);
        //set it as the supportActionBar
        //setSupportActionBar(toolbar);
    }


//    //launch navigation bar
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.bottom_nav_bar, menu);
//        return super.onCreateOptionsMenu(menu);
//    }

//    /**
//     * Popup Menu
//     * @param item
//     * @return
//     */
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.new_complex_workout:
//                //open a complex workout creation screen
//                return true;
//            case R.id.new_simple_workout:
//                openNewWorkoutScreen();
//
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }


    /**
     * Open a simple new workout creation screen.
     */
    public void openNewWorkoutScreen() {

        Intent intentNewWorkoutScreen = new Intent(this, NewWorkoutActivity.class);
        startActivity(intentNewWorkoutScreen);

    }

    public void openSavedScreen() {
        Intent intentOpenSavedScreen = new Intent(this, SavedWorkoutsActivity.class);
//        intentOpenSavedScreen.putExtra(EXTRA_WORKOUTS, workouts);
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





}
