package com.creator.anchuinse.abilitybuilder;

import android.os.Parcel;
import android.os.Parcelable;

import com.creator.anchuinse.abilitybuilder.PowerTypes.Power;

import java.util.ArrayList;

/**
 * Created by Matt on 5/30/18.
 */

public class Powerset implements Parcelable{

    String name;
    int total_cost;
    int current_cost;
    String description;
    ArrayList<Power> powers = new ArrayList<Power>();

    public Powerset(String new_name, int new_total) {

        name = new_name;
        total_cost = new_total;
        description = "no description";
    }

    //start of Parcelable chunk

    protected Powerset(Parcel in) {
        name = in.readString();
        total_cost = in.readInt();
        current_cost = in.readInt();
        description = in.readString();
        powers = in.createTypedArrayList(Power.CREATOR);
    }

    public static final Creator<Powerset> CREATOR = new Creator<Powerset>() {
        @Override
        public Powerset createFromParcel(Parcel in) {
            return new Powerset(in);
        }

        @Override
        public Powerset[] newArray(int size) {
            return new Powerset[size];
        }
    };

    //end of Parcelable chunk

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotal_cost() {
        return total_cost;
    }

    public void setTotal_cost(int total_cost) {
        this.total_cost = total_cost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Power> getPowers() {
        return powers;
    }

    public void setPowers(ArrayList<Power> powers) {
        this.powers = powers;
    }

    public int getCurrent_cost() {
        return current_cost;
    }

    public void setCurrent_cost(int current_cost) {
        this.current_cost = current_cost;
    }

    //start of Parcelable chunk

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeInt(total_cost);
        parcel.writeInt(current_cost);
        parcel.writeString(description);
        parcel.writeTypedList(powers);
    }

    //end of Parcelable chunk
}
