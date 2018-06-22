package com.creator.anchuinse.abilitybuilder.Pieces;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Matt on 5/28/18.
 */

public class ComplexAspect extends Aspect implements Parcelable{

    ArrayList<Aspect> sub_aspects = new ArrayList<Aspect>();

    public ComplexAspect(String new_name) {
        super(new_name);
        selected = PiecePart.emptyPart();
    }

    public ComplexAspect(String new_name, ArrayList<PiecePart> parts_list) {
        super(new_name, parts_list);
        refreshCost();
    }

    public ComplexAspect(String new_name, ArrayList<PiecePart> parts_list, PiecePart new_selected) {
        super(new_name, parts_list, new_selected);
        refreshCost();
    }



    //---------

    //start of Parcelable chunk

    protected ComplexAspect(Parcel in) {
        super(in);
        sub_aspects = in.createTypedArrayList(Aspect.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeTypedList(sub_aspects);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public void refreshCost(){

        int piece_total = 0;
        for (int i = 0; i < sub_aspects.size(); i++) {
            piece_total = piece_total + getSubAspects().get(i).getSelected().getCost();
        }
        setCost(piece_total);
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

    public ArrayList<Aspect> getSubAspects() {
        return sub_aspects;
    }

    public void setSub_aspects(ArrayList<Aspect> sub_aspects) {
        this.sub_aspects = sub_aspects;
    }

    public void addPiece(Aspect simple_piece){
        getSubAspects().add(simple_piece);
        refreshCost();
    }
}
