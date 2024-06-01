package org.example;

import java.util.*;

class Mage extends PlayerClass {
    public Mage() {
        super(100, 9, 1);
    }

    @Override
    public int id() {
        return 3;
    }
    @Override
    public void attack(ArrayList<Player> group, int selected_player) {
        group.get(selected_player).player_class.health -= Math.max(atk - group.get(selected_player).player_class.def, 0);

        for(int i = 0; i < group.size(); ++i) {
            if(i == selected_player)
                continue;
            group.get(i).player_class.health -= Math.max((atk/2) - group.get(i).player_class.def, 0);
        }
    }
}