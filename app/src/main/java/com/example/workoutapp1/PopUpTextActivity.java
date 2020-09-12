package com.example.workoutapp1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class PopUpTextActivity extends AppCompatActivity {

    Button confirmBtn;
    EditText workoutNameEditText;
    private File file;
    private ArrayList<Workout> workouts = new ArrayList<>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up_text);
        Intent intent = getIntent();
        final Workout workoutIn = intent.getParcelableExtra(NewWorkoutActivity.EXTRA_WORKOUT);
        workoutNameEditText = findViewById(R.id.edit_query);
        workoutNameEditText.addTextChangedListener(checkInput);


        file = new File(this.getFilesDir(), SavedWorkoutsActivity.SIMPLE_WORKOUTS);



        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = (int) (dm.widthPixels * 0.9);
        int height = (int) (dm.heightPixels * 0.3);
        getWindow().setLayout(width, height);


        confirmBtn = findViewById(R.id.confirm_button);
        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = workoutNameEditText.getText().toString();
                workoutIn.setName(s); //give the workout its new name
                writeToFile(workoutIn.toString(), PopUpTextActivity.this); //put this workout in the saved workouts file
                Toast.makeText(PopUpTextActivity.this, "Workout Saved", Toast.LENGTH_SHORT).show();
                populateWorkoutArray();
                Intent intent = new Intent(PopUpTextActivity.this, SavedWorkoutsActivity.class);
                intent.putExtra(MainActivity.EXTRA_WORKOUTS, workouts);
                startActivity(intent);
                PopUpTextActivity.this.finish();
            }
        });
    }

    /**
     * Creating a textWatcher to check if a string is inputted.
     */
    private TextWatcher checkInput = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            String s = workoutNameEditText.getText().toString();
            confirmBtn.setEnabled(!s.isEmpty()); //enable roundedbutton if something is in the editText

        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };


    public void writeToFile(String data, Context context) {
        //String existing = readFromFile(context);
        try (BufferedWriter fos = new BufferedWriter(new FileWriter(context.getFileStreamPath(SavedWorkoutsActivity.SIMPLE_WORKOUTS), true))) {

            fos.write(data + "\n");


        } catch (IOException e) {
            e.printStackTrace();
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
}
