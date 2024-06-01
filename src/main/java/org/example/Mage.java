package org.example;

import java.util.*;

class Mage extends PlayerClass {
    // Level Max(10) 220 vida, 21 Atk, 6 Def
    static final private Map<Integer, LevelStatus> level_map = Map.ofEntries(
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

    public Mage(int level) {
        super(100, 9, 1);
        levelup(1, level);
    }

    @Override
    public int id() {
        return 3;
    }
    @Override
    public void attack(ArrayList<Player> group, int selected_player) {
        group.get(selected_player).player_class.health -= Math.max(atk - group.get(selected_player).player_class.def, 1);

        for(int i = 0; i < group.size(); ++i) {
            if(i == selected_player)
                continue;
            group.get(i).player_class.health -= Math.max((atk/3) - group.get(i).player_class.def, 1);
        }
    }

    @Override
    public void levelup(int old_level, int new_level) {
        for (int i = old_level+1; i <= new_level; ++i) {
            LevelStatus status = level_map.get(i);
            this.health += status.health();
            this.atk += status.attack();
            this.def += status.defense();
        }
    }
}