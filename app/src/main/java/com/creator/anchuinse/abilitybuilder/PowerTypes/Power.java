package com.creator.anchuinse.abilitybuilder.PowerTypes;

/**
 * Created by Matt on 5/28/18.
 */

public class Power {

    String name;
    int total_cost;
    int current_cost;
    String description;

    public Power(){
        name = "no name";
        total_cost = 100;
        current_cost = 0;
        description = "no description";
    }

    public Power(String new_name) {
        name = new_name;
        total_cost = 100;
        current_cost = 0;
        description = "no description";
    }

    public Power(String new_name, String new_description) {
        name = new_name;
        total_cost = 100;
        current_cost = 0;
        description = new_description;
    }

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
}
