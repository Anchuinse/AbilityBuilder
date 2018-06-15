package com.creator.anchuinse.abilitybuilder.PowerTypes;

import android.os.Parcel;
import android.os.Parcelable;

import com.creator.anchuinse.abilitybuilder.Pieces.ComplexAspect;
import com.creator.anchuinse.abilitybuilder.Pieces.Aspect;

import java.util.ArrayList;

/**
 * Created by Matt on 5/28/18.
 */

public class PhysicalPower extends Power implements Parcelable{

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

    //start of Parcelable chunk

    protected PhysicalPower(Parcel in) {
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

    public static final Creator<PhysicalPower> CREATOR = new Creator<PhysicalPower>() {
        @Override
        public PhysicalPower createFromParcel(Parcel in) {
            return new PhysicalPower(in);
        }

        @Override
        public PhysicalPower[] newArray(int size) {
            return new PhysicalPower[size];
        }
    };

    //end of Parcelable chunk

    public ArrayList<Aspect> getCategories() {
        return categories;
    }

}
