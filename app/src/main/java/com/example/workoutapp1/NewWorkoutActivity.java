package com.example.workoutapp1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;



public class NewWorkoutActivity extends AppCompatActivity {
    /*
    public static final String Extra_work = "com.example.workoutapp1.work";
    public static final String Extra_rest = "com.example.workoutapp1.rest";
    public static final String Extra_cooldown = "com.example.workoutapp1.cooldown";
    public static final String Extra_sets = "com.example.workoutapp1.sets";
    public static final String Extra_cycles = "com.example.workoutapp1.cycles";
     */
    //Variable to pass to the next activity
    public static final String EXTRA_WORKOUT = "com.example.workoutapp1.workout";
    //Getting the numbers that the user inputs
    private EditText workTime;
    private EditText restTime;
    private EditText cycleTime;
    private EditText setsTime;
    private EditText cooldownTime;
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

        startButton = findViewById(R.id.startWorkout);
        //Opens the next activity by clicking start
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openWorkOut();
            }


        });

//        Button homeButton = findViewById(R.id.Home_button);
//        homeButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                openHomeScreen();
//
//            }
//        });

        Button menuButton = findViewById(R.id.Menu_button);
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSavedScreen();
            }
        });


        Toolbar toolbar = findViewById(R.id.toolbar);
        //set it as the supportActionBar
        //setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayShowTitleEnabled(false);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.e("TAG", "onCreateOptionsMenu called");
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar, menu);
        //get a reference to the adding new workout roundedbutton
        MenuItem newWorkoutItem = menu.findItem(R.id.action_newWorkout);
        //set it to invisible
        newWorkoutItem.setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_newWorkout:
                //roundedbutton should be hidden

                return true;
            case R.id.action_saveWorkout:
                //grab values
                try {
                    workInt = Integer.parseInt(workTime.getText().toString());
                    restInt = Integer.parseInt(restTime.getText().toString());
                    cycleInt = Integer.parseInt(cycleTime.getText().toString());
                    setsInt = Integer.parseInt(setsTime.getText().toString());
                    cooldownInt = Integer.parseInt(cooldownTime.getText().toString());
                    Workout myWorkout = new Workout(workInt, restInt, cooldownInt, setsInt, cycleInt);
                    openPopUpActivity(myWorkout);
                } catch (Exception e) {
                    Toast.makeText(this, R.string.input_warning, Toast.LENGTH_LONG).show();

                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void openSavedScreen() {
        Intent intentOpenSavedScreen = new Intent(this, SavedWorkoutsActivity.class);
//        intentOpenSavedScreen.putExtra(EXTRA_WORKOUTS, workouts);
        startActivity(intentOpenSavedScreen);
        this.finish(); //end current activity
    }

    public void openPopUpActivity(Workout myWorkout) {
        Intent intent = new Intent(NewWorkoutActivity.this, PopUpTextActivity.class);
        intent.putExtra(EXTRA_WORKOUT, myWorkout);
        startActivity(intent);
    }

    public void openHomeScreen() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        this.finish();
    }

//    public void writeToFile(String data, Context context) {
//        //String existing = readFromFile(context);
//        try (BufferedWriter fos = new BufferedWriter(new FileWriter(context.getFileStreamPath(MainActivity.SIMPLE_WORKOUTS), true))) {
//
//            fos.write(data + "\n");
//
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }


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
        //Setting variables to pass onto the next activity
        String id = "newWorkout";

        Workout myWorkout = new Workout(id, workInt, restInt, cooldownInt, setsInt, cycleInt);
        /*
        intentOpenWorkout.putExtra(Extra_work,workInt);
        intentOpenWorkout.putExtra(Extra_rest,restInt);
        intentOpenWorkout.putExtra(Extra_cooldown,cooldownInt);
        intentOpenWorkout.putExtra(Extra_sets,setsInt);
        intentOpenWorkout.putExtra(Extra_cycles,cycleInt);
        */
        //Sends the data to the next activity
        intentOpenWorkout.putExtra(EXTRA_WORKOUT, myWorkout);
        startActivity(intentOpenWorkout);

        }


}

