package com.creator.anchuinse.abilitybuilder.PowerTypes;

import android.os.Parcel;
import android.os.Parcelable;

import com.creator.anchuinse.abilitybuilder.Pieces.Aspect;

import java.util.ArrayList;

/**
 * Created by Matt on 5/28/18.
 */

public class Power implements Parcelable{

    String name;
    int current_cost;
    String description;
    ArrayList<Aspect> aspects = new ArrayList<Aspect>();

    public Power(){
        name = "no name";
        current_cost = 0;
        description = "no description";
        ArrayList<Aspect> categories = new ArrayList<Aspect>();
    }

    public Power(String new_name) {
        name = new_name;
        current_cost = 0;
        description = "no description";
        ArrayList<Aspect> categories = new ArrayList<Aspect>();
    }

    public Power(String new_name, String new_description) {
        name = new_name;
        current_cost = 0;
        description = new_description;
        ArrayList<Aspect> categories = new ArrayList<Aspect>();
    }

    //-------

    //start of Parcelable chunk

    protected Power(Parcel in) {
        name = in.readString();
        current_cost = in.readInt();
        description = in.readString();
        aspects = in.createTypedArrayList(Aspect.CREATOR);
    }

    public static final Creator<Power> CREATOR = new Creator<Power>() {
        @Override
        public Power createFromParcel(Parcel in) {
            return new Power(in);
        }

        @Override
        public Power[] newArray(int size) {
            return new Power[size];
        }
    };

    //end of Parcelable chunk

    public void refreshCurrentCost(){

        int piece_total = 0;
        for (int i = 0; i < aspects.size(); i++) {
            piece_total = piece_total + getAspects().get(i).getCost();
        }
        setCurrent_cost(piece_total);
    }

    //-------

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCurrent_cost() {
        return current_cost;
    }

    public void setCurrent_cost(int current_cost) {
        this.current_cost = current_cost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Aspect> getAspects() {
        return aspects;
    }

    public void setAspects(ArrayList<Aspect> categories) {
        this.aspects = categories;
    }

    public void overwritePowerWith(Power new_power){
        this.name = new_power.getName();
        this.current_cost = new_power.getCurrent_cost();
        this.description = new_power.getDescription();
        this.aspects = new_power.getAspects();
    }

    //start of Parcelable chunk

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeInt(current_cost);
        parcel.writeString(description);
        parcel.writeTypedList(aspects);
    }

    //end of Parcelable chunk
}
