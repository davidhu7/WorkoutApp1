package com.example.workoutapp1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.Button;

public class PopUpTextActivity extends AppCompatActivity {

    Button confirmBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up_text);

        confirmBtn = findViewById(R.id.confirm_button);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = (int) (dm.widthPixels * 0.9);
        int height = (int) (dm.heightPixels * 0.3);

        getWindow().setLayout(width, height);
    }
}
