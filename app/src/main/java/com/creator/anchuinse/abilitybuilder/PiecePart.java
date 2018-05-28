package com.creator.anchuinse.abilitybuilder;

/**
 * Created by Matt on 5/28/18.
 */

public class PiecePart {

    String name;
    int cost;

    public PiecePart(String new_name, int new_cost){
        name = new_name;
        cost = new_cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
