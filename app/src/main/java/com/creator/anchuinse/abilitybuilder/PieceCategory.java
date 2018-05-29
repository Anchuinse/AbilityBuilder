package com.creator.anchuinse.abilitybuilder;

import java.util.ArrayList;

/**
 * Created by Matt on 5/28/18.
 */

public class PieceCategory {

    String name;
    PiecePart selected;
    ArrayList<PiecePart> piece_parts = new ArrayList<PiecePart>();

    public PieceCategory(String new_name) {
        name = new_name;
    }

    public PieceCategory(String new_name, ArrayList<PiecePart> parts_list) {
        name = new_name;
        piece_parts = parts_list;
        selected = parts_list.get(0);                                                               //change this later to select the part with the lowest cost
    }

    public PieceCategory(String new_name, ArrayList<PiecePart> parts_list, PiecePart new_selected) {
        name = new_name;
        piece_parts = parts_list;
        selected = new_selected;
    }

    //-----------

    public static PieceCategory Physical_Use_Time(){
        ArrayList<PiecePart> parts = new ArrayList<PiecePart>();
        parts.add(new PiecePart("Instant",1));
        parts.add(new PiecePart("Standard Action",2));
        parts.add(new PiecePart("Full-Round Action",3));

        PieceCategory time = new PieceCategory("Use Time");
        time.setPiece_parts(parts);
        time.setCheapest();
        return time;
    }

    public static PieceCategory Physical_Range(){
        ArrayList<PiecePart> parts = new ArrayList<PiecePart>();
        parts.add(new PiecePart("Touch/Self",1));
        parts.add(new PiecePart("Short",2));
        parts.add(new PiecePart("Medium",3));
        parts.add(new PiecePart("Long",4));

        PieceCategory range = new PieceCategory("Range");
        range.setPiece_parts(parts);
        range.setCheapest();
        return range;
    }

    public static PieceCategory Physical_Duration(){
        ArrayList<PiecePart> parts = new ArrayList<PiecePart>();
        parts.add(new PiecePart("Instant",1));
        parts.add(new PiecePart("Short",2));
        parts.add(new PiecePart("Medium",3));
        parts.add(new PiecePart("Long",4));

        PieceCategory duration = new PieceCategory("Duration");
        duration.setPiece_parts(parts);
        duration.setCheapest();
        return duration;
    }

    public static PieceCategory Physical_Activation_Time(){
        ArrayList<PiecePart> parts = new ArrayList<PiecePart>();
        parts.add(new PiecePart("Immediate",1));
        parts.add(new PiecePart("Slight Delay",2));
        parts.add(new PiecePart("Medium Delay",3));
        parts.add(new PiecePart("Long Delay",4));

        PieceCategory activation = new PieceCategory("Activation Time");
        activation.setPiece_parts(parts);
        activation.setCheapest();
        return activation;
    }

    public static PieceCategory Physical_Saving_Throw(){
        ArrayList<PiecePart> parts = new ArrayList<PiecePart>();
        parts.add(new PiecePart("Negates",1));
        parts.add(new PiecePart("Disbelief",2));
        parts.add(new PiecePart("Partial",3));
        parts.add(new PiecePart("None",4));

        PieceCategory saving = new PieceCategory("Saving Throw");
        saving.setPiece_parts(parts);
        saving.setCheapest();
        return saving;
    }

    //-----------

    public static PieceCategory Complex_Part_Target_Type(){
        ArrayList<PiecePart> parts = new ArrayList<PiecePart>();
        parts.add(new PiecePart("Self",1));
        parts.add(new PiecePart("Others",2));
        parts.add(new PiecePart("Objects",3));

        PieceCategory target = new PieceCategory("Target Type");
        target.setPiece_parts(parts);
        target.setCheapest();
        return target;
    }

    public static PieceCategory Complex_Part_AoE(){
        ArrayList<PiecePart> parts = new ArrayList<PiecePart>();
        parts.add(new PiecePart("None/Ray/Point",1));
        parts.add(new PiecePart("Line",2));
        parts.add(new PiecePart("Cone",3));

        PieceCategory area = new PieceCategory("Area of Effect");
        area.setPiece_parts(parts);
        area.setCheapest();
        return area;
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
    }

    public ArrayList<PiecePart> getPiece_parts() {
        return piece_parts;
    }

    public void setPiece_parts(ArrayList<PiecePart> piece_parts) {
        this.piece_parts = piece_parts;
    }

    public void setCheapest(){
        ArrayList<PiecePart> items = this.getPiece_parts();
        PiecePart cheapest = items.get(0);
        for (int i = 0; i < items.size(); i++){
            if (items.get(i).getCost() < cheapest.getCost())
            {
                cheapest = items.get(i);
            }
        }
        this.setSelected(cheapest);
    }
}
