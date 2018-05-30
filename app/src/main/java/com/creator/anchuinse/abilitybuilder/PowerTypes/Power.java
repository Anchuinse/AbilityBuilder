package com.creator.anchuinse.abilitybuilder.PowerTypes;

import com.creator.anchuinse.abilitybuilder.Pieces.Aspect;

import java.util.ArrayList;

/**
 * Created by Matt on 5/28/18.
 */

public class Power {

    String name;
    int current_cost;
    String description;
    ArrayList<Aspect> categories = new ArrayList<Aspect>();

    public Power(){
        name = "no name";
        current_cost = 0;
        description = "no description";
        ArrayList<Aspect> categories = new ArrayList<Aspect>();
    }

    public Power(String new_name) {
        name = new_name;
        current_cost = 0;
        description = "no description";
        ArrayList<Aspect> categories = new ArrayList<Aspect>();
    }

    public Power(String new_name, String new_description) {
        name = new_name;
        current_cost = 0;
        description = new_description;
        ArrayList<Aspect> categories = new ArrayList<Aspect>();
    }

    //-------

    public void refreshCurrentCost(){

        int piece_total = 0;
        for (int i = 0; i < categories.size(); i++) {
            piece_total = piece_total + getCategories().get(i).getSelected().getCost();
        }
        setCurrent_cost(piece_total);
    }

    //-------

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCurrent_cost() {
        return current_cost;
    }

    public void setCurrent_cost(int current_cost) {
        this.current_cost = current_cost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Aspect> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<Aspect> categories) {
        this.categories = categories;
    }
}
