package org.example;


import java.util.*;

public class Warrior extends PlayerClass {
    // Level Max(10) 300 Vida 9 Atk 18 Def
    static final private Map<Integer, LevelStatus> level_map = Map.ofEntries(
            Map.entry(1, new LevelStatus(100,3,7)),
            Map.entry(2, new LevelStatus(15,1,1)),
            Map.entry(3, new LevelStatus(15,0,1)),
            Map.entry(4, new LevelStatus(18,1,1)),
            Map.entry(5, new LevelStatus(18,1,1)),
            Map.entry(6, new LevelStatus(20,0,1)),
            Map.entry(7, new LevelStatus(20,0,1)),
            Map.entry(8, new LevelStatus(22,1,1)),
            Map.entry(9, new LevelStatus(23,1,1)),
            Map.entry(10, new LevelStatus(24,0,1))
    );
    public Warrior() {
        super(100, 3, 7);
    }

    @Override
    public int id() {
        return 1;
    }

    @Override
    public void attack(ArrayList<Player> rest, int selected_player) {
        rest.get(selected_player).player_class.health -=Math.max(atk - rest.get(selected_player).player_class.def, 1);
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