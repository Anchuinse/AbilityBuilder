package com.creator.anchuinse.abilitybuilder.Pieces;

import android.os.Parcel;
import android.os.Parcelable;

import com.creator.anchuinse.abilitybuilder.PowerTypes.Power;

import java.util.ArrayList;

/**
 * Created by Matt on 5/28/18.
 */

public class Aspect implements Parcelable{
    //never build Aspects with more than eight parts otherwise you'll have to change the code in AspectActivity and its layout

    String name;
    String description;
    PiecePart selected;
    ArrayList<PiecePart> piece_parts = new ArrayList<PiecePart>();
    ArrayList<Aspect> sub_aspects = new ArrayList<Aspect>();
    int cost;
    boolean isComplex;

    public Aspect(String new_name) {
        name = new_name;
        isComplex = false;
    }

    //start of Parcelable chunk

    protected Aspect(Parcel in) {
        //same order as writeToParcel
        name = in.readString();
        description = in.readString();
        selected = in.readParcelable(PiecePart.class.getClassLoader());
        piece_parts = in.createTypedArrayList(PiecePart.CREATOR);
    }

    public static final Creator<Aspect> CREATOR = new Creator<Aspect>() {
        @Override
        public Aspect createFromParcel(Parcel in) {
            return new Aspect(in);
        }

        @Override
        public Aspect[] newArray(int size) {
            return new Aspect[size];
        }
    };

    //end of Parcelable chunk

    public static Aspect Physical_Use_Time(){
        ArrayList<PiecePart> parts = new ArrayList<PiecePart>();
        parts.add(new PiecePart("Full-Round Action",1));
        parts.add(new PiecePart("Standard Action",2));
        parts.add(new PiecePart("Instant",3));

        Aspect time = new Aspect("Use Time");
        time.setDescription("This aspect describes how fast the user can use the ability. The shorter the use time, the more expensive it is.");
        time.setPiece_parts(parts);
        time.setCheapestAsSelected();
        return time;
    }

    public static Aspect Physical_Range(){
        ArrayList<PiecePart> parts = new ArrayList<PiecePart>();
        parts.add(new PiecePart("Touch/Self",1));
        parts.add(new PiecePart("Short",2));
        parts.add(new PiecePart("Medium",3));
        parts.add(new PiecePart("Far",4));

        Aspect range = new Aspect("Range");
        range.setDescription("Pretty straight forward. This aspect denotes the range of the ability.");
        range.setPiece_parts(parts);
        range.setCheapestAsSelected();
        return range;
    }

    public static Aspect Physical_Duration(){
        ArrayList<PiecePart> parts = new ArrayList<PiecePart>();
        parts.add(new PiecePart("Instant",1));
        parts.add(new PiecePart("Short",2));
        parts.add(new PiecePart("Medium",3));
        parts.add(new PiecePart("Long",4));

        Aspect duration = new Aspect("Duration");
        duration.setDescription("This aspect denotes how long the effect lasts after the ability is used.");
        duration.setPiece_parts(parts);
        duration.setCheapestAsSelected();
        return duration;
    }

    public static Aspect Physical_Delay_Time(){
        ArrayList<PiecePart> parts = new ArrayList<PiecePart>();
        parts.add(new PiecePart("Immediate",1));
        parts.add(new PiecePart("Slight Delay",2));
        parts.add(new PiecePart("Medium Delay",3));
        parts.add(new PiecePart("Long Delay",4));

        Aspect activation = new Aspect("Delay Time");
        activation.setDescription("This aspect denotes how long after the ability is used that it actually takes effect.");
        activation.setPiece_parts(parts);
        activation.setCheapestAsSelected();
        return activation;
    }

    //-----------

    public static Aspect Effected_Target_Target_Type(){
        ArrayList<PiecePart> parts = new ArrayList<PiecePart>();
        parts.add(new PiecePart("Self",1));
        parts.add(new PiecePart("Others",2));
        parts.add(new PiecePart("Objects",3));

        Aspect target = new Aspect("Target Type");
        target.setDescription("This aspect describes what kinds of things can be targeted or effected by this ability.");
        target.setPiece_parts(parts);
        target.setCheapestAsSelected();
        return target;
    }

    public static Aspect Effected_Target_SoE(){
        ArrayList<PiecePart> parts = new ArrayList<PiecePart>();
        parts.add(new PiecePart("Point/Ray",1));
        parts.add(new PiecePart("Line",2));
        parts.add(new PiecePart("Cone",3));

        Aspect area = new Aspect("Shape of Effect");
        area.setDescription("This aspect describes the shape of the area of effect.");
        area.setPiece_parts(parts);
        area.setCheapestAsSelected();
        return area;
    }

    public static Aspect Resistance_Strength(){
        ArrayList<PiecePart> parts = new ArrayList<PiecePart>();
        parts.add(new PiecePart("None",1));
        parts.add(new PiecePart("Weak",2));
        parts.add(new PiecePart("Medium",3));
        parts.add(new PiecePart("Strong",4));
        parts.add(new PiecePart("Impossible",4));

        Aspect strength = new Aspect("Ability Strength");
        strength.setDescription("This aspect describes how easy (or difficult) the ability is to resist. The weaker the ability's strength " +
                "the easier it is to resist it's effects. Having an ability with Impossible strength means the power cannot be resisted.");
        strength.setPiece_parts(parts);
        strength.setCheapestAsSelected();
        return strength;
    }

    public static Aspect Resisting_Effect(){
        ArrayList<PiecePart> parts = new ArrayList<PiecePart>();
        parts.add(new PiecePart("Negates",1));
        parts.add(new PiecePart("Disbelief",2));
        parts.add(new PiecePart("Partial",3));

        Aspect resisting = new Aspect("Resisting Effect");
        resisting.setDescription("This aspect describes what happens when the power is resisted successfully.");
        resisting.setPiece_parts(parts);
        resisting.setCheapestAsSelected();
        return resisting;
    }

    //-----------

    public void refreshCost(){

        int piece_total = 0;
        piece_total = selected.getCost();
        for (int i = 0; i < sub_aspects.size(); i++) {
            piece_total = piece_total + getSubAspects().get(i).getSelected().getCost();
        }
        setCost(piece_total);
    }

    //-----------

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PiecePart getSelected() {
        return selected;
    }

    public void setSelected(PiecePart selected) {
        this.selected = selected;
        setCost(selected.getCost());
    }

    public ArrayList<PiecePart> getPiece_parts() {
        return piece_parts;
    }

    public void setPiece_parts(ArrayList<PiecePart> piece_parts) {
        this.piece_parts = piece_parts;
    }

    public void setCheapestAsSelected(){
        ArrayList<PiecePart> items = this.getPiece_parts();
        PiecePart cheapest = items.get(0);
        for (int i = 0; i < items.size(); i++){
            if (items.get(i).getCost() < cheapest.getCost())
            {
                cheapest = items.get(i);
            }
        }
        setSelected(cheapest);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCost(){
        return cost;
    }

    public void setCost(int input_cost) {
        this.cost = input_cost;
    }

    public boolean isComplex() {
        return isComplex;
    }

    public void setComplex(boolean complex) {
        isComplex = complex;
    }

    //-----------

    public ArrayList<Aspect> getSubAspects() {
        return sub_aspects;
    }

    public void setSub_aspects(ArrayList<Aspect> sub_aspects) {
        this.sub_aspects = sub_aspects;
    }

    public void addSubAspect(Aspect aspect){
        getSubAspects().add(aspect);
        refreshCost();
    }

    public void overwriteAspectWith(Aspect new_aspect){
        this.name = new_aspect.getName();
        this.description = new_aspect.getDescription();
        this.selected = new_aspect.getSelected();
        this.piece_parts = new_aspect.getPiece_parts();
        this.sub_aspects = new_aspect.getSubAspects();
        this.cost = new_aspect.getCost();
        this.isComplex = new_aspect.isComplex();
    }

    //start of Parcelable chunk

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        //same order as protected ...
        parcel.writeString(name);
        parcel.writeString(description);
        parcel.writeParcelable(selected, i);
        parcel.writeTypedList(piece_parts);
    }

    //end of Parcelable chunk
}
