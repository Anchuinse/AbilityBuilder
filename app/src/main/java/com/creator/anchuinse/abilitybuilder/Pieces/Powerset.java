package com.creator.anchuinse.abilitybuilder.Pieces;

import android.os.Parcel;
import android.os.Parcelable;

import com.creator.anchuinse.abilitybuilder.PowerTypes.PhysicalPower;
import com.creator.anchuinse.abilitybuilder.PowerTypes.Power;

import java.util.ArrayList;

/**
 * Created by Matt on 5/30/18.
 */

public class Powerset implements Parcelable{

    String name;
    int max_cost;
    int current_cost;
    String description;
    ArrayList<Power> powers = new ArrayList<Power>();

    public Powerset(String new_name, int new_total) {

        name = new_name;
        max_cost = new_total;
        current_cost = 0;
        description = "no description";
    }

    public static Powerset examplePowerset(){
        Powerset examplePowerset = new Powerset("example",20);
        examplePowerset.getPowers().add(new PhysicalPower("Physical Example"));
        examplePowerset.refreshCurrentCost();
        return examplePowerset;
    }

    //start of Parcelable chunk

    protected Powerset(Parcel in) {
        name = in.readString();
        max_cost = in.readInt();
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

    public int getMaxCost() {
        return max_cost;
    }

    public void setMaxCost(int total_cost) {
        this.max_cost = total_cost;
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

    public int getCurrentCost() {
        return current_cost;
    }

    public void setCurrentCost(int current_cost) {
        this.current_cost = current_cost;
    }

    public void refreshCurrentCost() {
        int cost = 0;
        for (int i = 0; i < powers.size(); ++i) {
            cost = cost + powers.get(i).getCurrent_cost();
        }
        setCurrentCost(cost);
    }

    public void overwritePowersetWith(Powerset new_powerset){
        this.name = new_powerset.getName();
        this.max_cost = new_powerset.getMaxCost();
        this.current_cost = new_powerset.getCurrentCost();
        this.description = new_powerset.getDescription();
        this.powers = new_powerset.getPowers();
    }

    //start of Parcelable chunk

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeInt(max_cost);
        parcel.writeInt(current_cost);
        parcel.writeString(description);
        parcel.writeTypedList(powers);
    }

    //end of Parcelable chunk
}
