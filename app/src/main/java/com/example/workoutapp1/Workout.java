package com.example.workoutapp1;

import android.app.Activity;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

import androidx.cardview.widget.CardView;

public class Workout implements Parcelable {
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


    protected Workout(Parcel in) {
        name = in.readString();
        workTime = in.readInt();
        restTime = in.readInt();
        cooldownTime = in.readInt();
        sets = in.readInt();
        cycles = in.readInt();
    }

    public static final Creator<Workout> CREATOR = new Creator<Workout>() {
        @Override
        public Workout createFromParcel(Parcel in) {
            return new Workout(in);
        }

        @Override
        public Workout[] newArray(int size) {
            return new Workout[size];
        }
    };

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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(workTime);
        dest.writeInt(restTime);
        dest.writeInt(cooldownTime);
        dest.writeInt(sets);
        dest.writeInt(cycles);
    }
}
