package com.creator.anchuinse.abilitybuilder.Pieces;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Matt on 6/16/18.
 */

public class MasterData implements Parcelable{

    ArrayList<Powerset> powersets = new ArrayList<Powerset>();

    public MasterData(){
        this.powersets = new ArrayList<Powerset>();
    }

    //-----------

    public static MasterData exampleData() {
        MasterData example = new MasterData();
        example.getPowersets().add(Powerset.examplePowerset());
        return example;
    }

    public ArrayList<Powerset> getPowersets() {
        return powersets;
    }

    public void setPowersets(ArrayList<Powerset> powersets) {
        this.powersets = powersets;
    }


    //start of Parcelable chunk


    protected MasterData(Parcel in) {
        powersets = in.createTypedArrayList(Powerset.CREATOR);
    }

    public static final Creator<MasterData> CREATOR = new Creator<MasterData>() {
        @Override
        public MasterData createFromParcel(Parcel in) {
            return new MasterData(in);
        }

        @Override
        public MasterData[] newArray(int size) {
            return new MasterData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(powersets);
    }


    //end of Parcelable chunk
}
