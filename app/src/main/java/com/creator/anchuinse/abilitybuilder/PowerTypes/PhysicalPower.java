package com.creator.anchuinse.abilitybuilder.PowerTypes;

import com.creator.anchuinse.abilitybuilder.Pieces.ComplexAspect;
import com.creator.anchuinse.abilitybuilder.Pieces.Aspect;

import java.util.ArrayList;

/**
 * Created by Matt on 5/28/18.
 */

public class PhysicalPower extends Power {

    public PhysicalPower(){
        super();
        categories.add(Aspect.Physical_Delay_Time());
        categories.add(Aspect.Physical_Duration());
        categories.add(ComplexAspect.Physical_Effected_Target());
        categories.add(Aspect.Physical_Range());
        categories.add(ComplexAspect.Physical_Resistance());
        categories.add(Aspect.Physical_Use_Time());
        this.refreshCurrentCost();
    }

    public PhysicalPower(String new_name){
        super(new_name);
        categories.add(Aspect.Physical_Delay_Time());
        categories.add(Aspect.Physical_Duration());
        categories.add(ComplexAspect.Physical_Effected_Target());
        categories.add(Aspect.Physical_Range());
        categories.add(ComplexAspect.Physical_Resistance());
        categories.add(Aspect.Physical_Use_Time());
        this.refreshCurrentCost();
    }

    public ArrayList<Aspect> getCategories() {
        return categories;
    }

}
