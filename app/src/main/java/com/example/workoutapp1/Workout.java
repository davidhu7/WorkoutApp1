package com.example.workoutapp1;

import android.app.Activity;

import androidx.cardview.widget.CardView;

public class Workout {
    //stringId, workTime resttime CooldownTime Sets Cycles
    private String name;
    private int workTime;
    private int restTime;
    private int cooldownTime;
    private int sets;
    private int cycles;


    public Workout(String name, int wTime, int rTime, int cTime, int sts, int cycls ) {
        this.name = name;
        workTime = wTime;
        restTime = rTime;
        cooldownTime = cTime;
        sets = sts;
        cycles = cycls;
    }

    public Workout(int w, int r, int c, int s, int cycls) {
        workTime = w;
        restTime = r;
        cooldownTime = c;
        sets = s;
        cycles = cycls;
    }




    public String getName() {
        return name;
    }

    public int getWorkTime() {
        return workTime;
    }
    public int getRestTime() {
        return restTime;
    }

    public int getCooldownTime() {
        return cooldownTime;
    }
    public int getSets() {
        return sets;
    }
    public int getCycles() {
        return cycles;
    }




}
