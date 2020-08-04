package com.example.workoutapp1;

import android.app.Activity;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

import androidx.cardview.widget.CardView;

public class Workout implements Parcelable {
    //stringId, workTime resttime CooldownTime Sets Cycles
    private String name;
    private int workTime;
    private int restTime;
    private int cooldownTime;
    private int sets;
    private int cycles;


    Workout(String name, int wTime, int rTime, int cTime, int sts, int cycls) {
        this.name = name;
        workTime = wTime;
        restTime = rTime;
        cooldownTime = cTime;
        sets = sts;
        cycles = cycls;
    }

    Workout(int w, int r, int c, int s, int cycls) {
        workTime = w;
        restTime = r;
        cooldownTime = c;
        sets = s;
        cycles = cycls;
    }


    private Workout(Parcel in) {
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

    String getName() {
        return name;
    }

    int getWorkTime() {
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

    public String toString() {
        return name + "," + workTime + "," + restTime + "," + cooldownTime + "," + sets + "," + cycles;
    }

    public void setName(String n) {
        name = n;
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

    /**
     * This method is meant for displaying the full schedule of the workout as an Array of Strings.
     */
    public ArrayList<String> workoutScheduleAsArray() {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < sets; i++) {
            for (int j = 0; j < cycles; j++) { //repeat for the amount of cycles in a set
                list.add("Exercise: " + workTime); //display the time to workout
                list.add("Rest: " + restTime); //add the time to rest afterwards
            }
            list.add("Rest between sets: " + cooldownTime); //after each set, add the cooldown time.
        }
//        String[] s = new String[list.size()];
//        for (int i = 0; i < list.size(); i++) { //convert to Array of strings.
//            s[i] = list.get(i);
//        }
        return list;
    }
}
