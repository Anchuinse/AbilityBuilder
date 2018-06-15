package com.creator.anchuinse.abilitybuilder.Pieces;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Matt on 5/28/18.
 */

public class ComplexAspect extends Aspect implements Parcelable{

    ArrayList<Aspect> simpler_pieces = new ArrayList<Aspect>();

    public ComplexAspect(String new_name) {
        super(new_name);
        selected = PiecePart.emptyPart();
    }

    public ComplexAspect(String new_name, ArrayList<PiecePart> parts_list) {
        super(new_name, parts_list);
    }

    public ComplexAspect(String new_name, ArrayList<PiecePart> parts_list, PiecePart new_selected) {
        super(new_name, parts_list, new_selected);
    }

    //---------

    //start of Parcelable chunk

    protected ComplexAspect(Parcel in) {
        super(in);
        simpler_pieces = in.createTypedArrayList(Aspect.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeTypedList(simpler_pieces);
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

        effected_target.addPiece(Aspect.Effected_Target_Target_Type());
        effected_target.addPiece(Aspect.Effected_Target_SoE());

        return(effected_target);
    }

    public static ComplexAspect Physical_Resistance(){
        ComplexAspect resistance = new ComplexAspect("Resistance");

        resistance.addPiece(Aspect.Resistance_Strength());
        resistance.addPiece(Aspect.Resisting_Effect());

        return(resistance);
    }

    //---------

    public ArrayList<Aspect> getSimpler_pieces() {
        return simpler_pieces;
    }

    public void setSimpler_pieces(ArrayList<Aspect> simpler_pieces) {
        this.simpler_pieces = simpler_pieces;
    }

    public void addPiece(Aspect simple_piece){
        this.getSimpler_pieces().add(simple_piece);
    }

}
