package com.creator.anchuinse.abilitybuilder;

import android.provider.ContactsContract;

import java.util.ArrayList;

/**
 * Created by Matt on 5/28/18.
 */

public class ComplexCategory extends PieceCategory {

    ArrayList<PieceCategory> simpler_pieces = new ArrayList<PieceCategory>();

    public ComplexCategory(String new_name) {
        super(new_name);
        selected = PiecePart.emptyPart();
    }

    public ComplexCategory(String new_name, ArrayList<PiecePart> parts_list) {
        super(new_name, parts_list);
    }

    public ComplexCategory(String new_name, ArrayList<PiecePart> parts_list, PiecePart new_selected) {
        super(new_name, parts_list, new_selected);
    }

    //---------

    public static ComplexCategory Physical_Effected_Target(){
        ComplexCategory effected_target = new ComplexCategory("Effected Target");

        effected_target.addPiece(PieceCategory.Complex_Part_Target_Type());
        effected_target.addPiece(PieceCategory.Complex_Part_AoE());

        return(effected_target);
    }

    //---------

    public ArrayList<PieceCategory> getSimpler_pieces() {
        return simpler_pieces;
    }

    public void setSimpler_pieces(ArrayList<PieceCategory> simpler_pieces) {
        this.simpler_pieces = simpler_pieces;
    }

    public void addPiece(PieceCategory simple_piece){
        this.getSimpler_pieces().add(simple_piece);
    }

}
