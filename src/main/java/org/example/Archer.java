package org.example;


import java.util.*;

/**
 * 
 */
public class Archer extends PlayerClass {
    public Archer() {
        super(100, 7, 3);
    }

    @Override
    public int id() {
        return 1;
    }

    @Override
    public void attack(ArrayList<Player> rest, int selected_player) {
        rest.get(selected_player).player_class.health -= atk;
    }
}