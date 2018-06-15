package com.creator.anchuinse.abilitybuilder.Pieces;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Matt on 5/28/18.
 */

public class PiecePart implements Parcelable{

    String name;
    int cost;

    public PiecePart(String new_name, int new_cost){
        name = new_name;
        cost = new_cost;
    }

    //------

    //start of Parcelable chunk

    protected PiecePart(Parcel in) {
        //needs to be the same order as writeToParcel
        name = in.readString();
        cost = in.readInt();
    }

    public static final Creator<PiecePart> CREATOR = new Creator<PiecePart>() {
        @Override
        public PiecePart createFromParcel(Parcel in) {
            return new PiecePart(in);
        }

        @Override
        public PiecePart[] newArray(int size) {
            return new PiecePart[size];
        }
    };

    //end of Parcelable chunk

    public static PiecePart emptyPart(){
        PiecePart empty_part = new PiecePart(" ", 0);
        return empty_part;
    }

    //------

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    //start of Parcelable chunk

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        //needs to be the same order as protected PiecePart(Parcel in)
        parcel.writeString(name);
        parcel.writeInt(cost);
    }

    //end of Parcelable chunk
}
