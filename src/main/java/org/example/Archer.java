package org.example;


import java.util.*;


public class Archer extends PlayerClass {
    // Level Max(10) 270 vida, 16 Atk, 8 Def
    static private Map<Integer, LevelStatus> level_map = Map.ofEntries(
            Map.entry(1, new LevelStatus(100, 7, 3)),
            Map.entry(2, new LevelStatus(10, 1, 0)),
            Map.entry(3, new LevelStatus(10, 1, 2)),
            Map.entry(4, new LevelStatus(15, 1, 0)),
            Map.entry(5, new LevelStatus(15, 1, 1)),
            Map.entry(6, new LevelStatus(20, 1, 0)),
            Map.entry(7, new LevelStatus(20, 1, 1)),
            Map.entry(8, new LevelStatus(25, 1, 0)),
            Map.entry(9, new LevelStatus(25, 1, 1)),
            Map.entry(10, new LevelStatus(30, 1, 0))
    );

    public Archer() {
        super(100, 7, 3);
    }

    public Archer(int level) {
        super(100, 7, 3);
        levelup(1, level);
    }

    @Override
    public int id() {
        return 2;
    }

    @Override
    public void attack(ArrayList<Player> rest, int selected_player) {
        rest.get(selected_player).player_class.health -= atk;
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