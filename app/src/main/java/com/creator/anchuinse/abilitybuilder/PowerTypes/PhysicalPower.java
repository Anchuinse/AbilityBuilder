package com.creator.anchuinse.abilitybuilder.PowerTypes;

import com.creator.anchuinse.abilitybuilder.PieceCategory;

import java.util.ArrayList;

/**
 * Created by Matt on 5/28/18.
 */

public class PhysicalPower extends Power {

    ArrayList<PieceCategory> categories = new ArrayList<PieceCategory>();

    public PhysicalPower(){
        super();
        categories.add(PieceCategory.Physical_Activation_Time());
        categories.add(PieceCategory.Physical_Duration());
        categories.add(PieceCategory.Physical_Range());
        categories.add(PieceCategory.Physical_Saving_Throw());
        categories.add(PieceCategory.Physical_Use_Time());
    }

    public PhysicalPower(String new_name){
        super(new_name);
        categories.add(PieceCategory.Physical_Activation_Time());
        categories.add(PieceCategory.Physical_Duration());
        categories.add(PieceCategory.Physical_Range());
        categories.add(PieceCategory.Physical_Saving_Throw());
        categories.add(PieceCategory.Physical_Use_Time());
    }

    public ArrayList<PieceCategory> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<PieceCategory> categories) {
        this.categories = categories;
    }
}
