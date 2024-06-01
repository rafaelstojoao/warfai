package org.example;


import java.util.*;

public class Warrior extends PlayerClass {
    public Warrior() {
        super(100, 3, 7);
    }

    @Override
    public int id() {
        return 0;
    }
    @Override
    public void attack(ArrayList<Player> rest, int selected_player) {
        rest.get(selected_player).player_class.health -=Math.max(atk - rest.get(selected_player).player_class.def, 1);
    }
}