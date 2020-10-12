package com.example.workoutapp1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;




import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class SavedWorkoutsActivity extends AppCompatActivity {

    public static final String SIMPLE_WORKOUTS = "simple_workouts";


    private LinearLayout sLinearLayout;
    private CardView[] cardViews;
    private ArrayList<Workout> workouts = new ArrayList<>();


    private LinearLayout.LayoutParams params;
    private File file;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.saved_workouts);
        file = new File(this.getFilesDir(), SIMPLE_WORKOUTS);
        populateWorkoutArray();
        //get intent from mainactivity
//        Intent intent = getIntent();
//        //obtain the arrayList of workouts from the Main Activity
//        ArrayList<Parcelable> parcelableArrayExtra = intent.getParcelableArrayListExtra(MainActivity.EXTRA_WORKOUTS);
//        workouts = new Workout[parcelableArrayExtra.size()];
//        //copy the ArrayList to our workouts array
//        try {
//            System.arraycopy(Objects.requireNonNull(parcelableArrayExtra.toArray()), 0, workouts, 0, parcelableArrayExtra.size());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        //create a reference for our LinearLayout
        sLinearLayout = findViewById(R.id.viewList);
//        //create a reference to our Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        //set it as the supportActionBar
        //setSupportActionBar(toolbar);


        // Set the CardView layoutParams
        params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        //set margins for the cardviews
        params.setMargins(0, 10, 0, 40);
        populateCardViews(); //fill the cardview array
        populateLayout();   //fill the layout with cardviews


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar, menu);
        MenuItem item = menu.findItem(R.id.action_saveWorkout);
        item.setVisible(false);
        return super.onCreateOptionsMenu(menu);
    }

    private void populateLayout() {
        for (CardView cardView : cardViews) {
            sLinearLayout.addView(cardView);

        }

    }

    private void populateCardViews() {
        cardViews = new CardView[workouts.size()];
        for (int i = 0; i < workouts.size(); i++) {
            CardView card = new CardView(this);
            card.setClickable(true);
            card.setLayoutParams(params);
            // Set CardView corner radius
            card.setRadius(20);
            // Set cardView content padding
            card.setContentPadding(20, 20, 20, 20);
            // Set a background color for CardView
            card.setCardBackgroundColor(getColor(R.color.colorSecondary));
            // Set the CardView maximum elevation
            card.setMaxCardElevation(30);
            // Set CardView elevation
            card.setCardElevation(30);


            // Initialize new TextView to put in CardView

            TextView tv = new TextView(this);
            tv.setLayoutParams(params);
            tv.setText(workouts.get(i).getName());
            tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
            tv.setTextColor(Color.WHITE);
            tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

            card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = ((ViewGroup) view.getParent()).indexOfChild(view);
                    openWorkout(pos);
                }
            });

            card.addView(tv);
            cardViews[i] = card;
        }


    }

    public void populateWorkoutArray() {

        try {
            Scanner fileIn = new Scanner(file);
            String s;
            while (fileIn.hasNextLine()) {
                s = fileIn.nextLine();
                String[] split = s.split(",");
                Workout w = new Workout(split[0], Integer.parseInt(split[1]), Integer.parseInt(split[2]), Integer.parseInt(split[3]), Integer.parseInt(split[4]), Integer.parseInt(split[5]));
                workouts.add(w);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void openWorkout(int workoutIndex) {
        Intent intent = new Intent(this, WorkoutActivity.class);
        intent.putExtra(NewWorkoutActivity.EXTRA_WORKOUT, workouts.get(workoutIndex));
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_newWorkout:
                openNewWorkoutScreen();
                this.finish();
                return true;
            case R.id.action_saveWorkout:
                //do nothing, option should not be available
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public void openNewWorkoutScreen() {
        Intent intentNewWorkoutScreen = new Intent(this, NewWorkoutActivity.class);
        startActivity(intentNewWorkoutScreen);
    }
}
