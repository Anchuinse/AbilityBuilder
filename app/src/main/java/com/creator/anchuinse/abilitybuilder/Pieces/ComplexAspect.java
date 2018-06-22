package com.creator.anchuinse.abilitybuilder.Pieces;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Matt on 5/28/18.
 */

public class ComplexAspect extends Aspect implements Parcelable{

    public ComplexAspect(String new_name) {
        super(new_name);
        selected = PiecePart.emptyPart();
        setComplex(true);
    }

    //---------

    //start of Parcelable chunk

    protected ComplexAspect(Parcel in) {
        super(in);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ComplexAspect> CREATOR = new Creator<ComplexAspect>() {
        @Override
        public ComplexAspect createFromParcel(Parcel in) {
            return new ComplexAspect(in);
        }

        @Override
        public ComplexAspect[] newArray(int size) {
            return new ComplexAspect[size];
        }
    };

    //end of Parcelable chunk

    public static ComplexAspect Physical_Effected_Target(){
        ComplexAspect effected_target = new ComplexAspect("Effected Target");

        effected_target.addSubAspect(Aspect.Effected_Target_Target_Type());
        effected_target.addSubAspect(Aspect.Effected_Target_SoE());

        return(effected_target);
    }

    public static ComplexAspect Physical_Resistance(){
        ComplexAspect resistance = new ComplexAspect("Resistance");

        resistance.addSubAspect(Aspect.Resistance_Strength());
        resistance.addSubAspect(Aspect.Resisting_Effect());

        return(resistance);
    }

    //---------
}
