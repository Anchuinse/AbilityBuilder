package com.creator.anchuinse.abilitybuilder.PowerTypes;

import com.creator.anchuinse.abilitybuilder.PieceCategory;

import java.util.ArrayList;

/**
 * Created by Matt on 5/28/18.
 */

public class Power {

    String name;
    int total_cost;
    int current_cost;
    String description;
    ArrayList<PieceCategory> categories = new ArrayList<PieceCategory>();

    public Power(){
        name = "no name";
        total_cost = 100;
        current_cost = 0;
        description = "no description";
        ArrayList<PieceCategory> categories = new ArrayList<PieceCategory>();
    }

    public Power(String new_name) {
        name = new_name;
        total_cost = 100;
        current_cost = 0;
        description = "no description";
        ArrayList<PieceCategory> categories = new ArrayList<PieceCategory>();
    }

    public Power(String new_name, String new_description) {
        name = new_name;
        total_cost = 100;
        current_cost = 0;
        description = new_description;
        ArrayList<PieceCategory> categories = new ArrayList<PieceCategory>();
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

    public int getTotal_cost() {
        return total_cost;
    }

    public void setTotal_cost(int total_cost) {
        this.total_cost = total_cost;
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

    public ArrayList<PieceCategory> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<PieceCategory> categories) {
        this.categories = categories;
    }
}
