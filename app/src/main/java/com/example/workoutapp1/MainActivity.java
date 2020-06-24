package com.example.workoutapp1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button savedBtn;
    private Button startBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startBtn = findViewById(R.id.startNewButton);
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMainScreen();
            }
        });

        savedBtn = (Button) findViewById(R.id.savedButton);
        savedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSavedScreen();
            }


        });
    }

    public void openMainScreen() {
        Intent intentOpenMainScreen = new Intent(this, NewWorkoutActivity.class);
        startActivity(intentOpenMainScreen);
    }

    public void openSavedScreen() {
        Intent intentOpenSavedScreen = new Intent(this, SavedWorkoutsActivity.class);
        startActivity(intentOpenSavedScreen);
    }
}
