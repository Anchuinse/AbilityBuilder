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
