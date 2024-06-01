package org.example;

import java.util.*;

class Mage extends PlayerClass {
    // Level Max(10) 220 vida, 21 Atk, 6 Def
    static final private Map level_map = Map.ofEntries(
            Map.entry(1, new LevelStatus(100, 9, 1)),
            Map.entry(2, new LevelStatus(10, 1, 0)),
            Map.entry(3, new LevelStatus(10, 2, 0)),
            Map.entry(4, new LevelStatus(10, 1, 2)),
            Map.entry(5, new LevelStatus(10, 1, 0)),
            Map.entry(6, new LevelStatus(15, 2, 0)),
            Map.entry(7, new LevelStatus(15, 1, 1)),
            Map.entry(8, new LevelStatus(15, 1, 0)),
            Map.entry(9, new LevelStatus(15, 2, 0)),
            Map.entry(10, new LevelStatus(20, 1, 2))
    );
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