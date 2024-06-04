package org.example;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * 
 */
public class Equipe {

    /**
     * 
     */
    private ArrayList<Player> party = new ArrayList<>();
    private boolean bot = false; 
    private int id = 0;
    /**
     * 
     */
    //private boolean ativa = true;

    public Equipe(ArrayList<Player> p, String nome) {
        party.addAll(p);
    }

    public Equipe(ArrayList<Player> p, String nome, int cod_usuario_humano) throws SQLException {
        Main.db.write("Insert into Equipe(nome, id_usuario_humano) values ('"+ nome + "',"+ cod_usuario_humano +");");
        ResultSet res = Main.db.consulta("Select * from Equipe order by id desc limit 1;");
        res.next();
        this.id = Integer.parseInt(res.getString("id"));
        
        for(int i = 0; i < p.size(); ++i) {
            Main.db.write("Insert into Player(nickname, id_equipe, id_classe) values ('" + p.get(i).name + "'," + this.id + "," + p.get(i).class_id() + ");");
            ResultSet res2 = Main.db.consulta("Select * from Player order by id desc limit 1;");
            res2.next();
            p.get(i).set_id(res2.getInt("id"));
            party.add(p.get(i));
        }
    }
    
    public Equipe(Equipe e) {
        id = e.id;
        bot = e.bot;
        for(int i = 0; i < e.party.size(); ++i) {
            party.add(e.party.get(i));
        }
    }

    public void add_player(Player p) {
        party.add(p);
    }

    public void remove_player(Player p) {
        party.remove(p);
    }

    public int id() { return id; }
    public boolean is_bot() { return bot; }
    public void set_bot(boolean bot) { this.bot = bot; } 
    /**
     * @param e
     */
    public void battle(Equipe e) throws SQLException {
        Equipe active1 = new Equipe(this); // O primeiro nunca será um bot
        Equipe active2 = new Equipe(e);
        Random rng = new Random();
        Scanner in = new Scanner(System.in);
        Main.db.write("INSERT INTO Batalhas(id_equipe1, id_equipe2) VALUES(" + this.id + "," + e.id + ");");
        ResultSet res = Main.db.consulta("Select * from Batalhas order by id desc limit 1;");
        res.next();
        int cod = Integer.parseInt(res.getString("id"));
        int turno = 1;
        while(!active1.party.isEmpty() && !active2.party.isEmpty()) {
            System.out.println("Qual player irá atacar:");
            for(int i = 0; i < active1.party.size(); ++i) {
                System.out.println("    " + i + " -> " + active1.party.get(i).name);
            }
            int player_selected = in.nextInt();
            Player p = active1.party.get(player_selected);
            int id1 = p.id;
            p.attack(active2.party, 0);
            active1.party.removeIf(Player::is_dead);
            
            if(active1.party.isEmpty())
            {
                Main.db.write("INSERT INTO Turno(id, id_batalha, id_atacante) VALUES(" + turno + "," + cod + "," + id1 + ");");
                break;
            }

            int second_player_selected = 0;
            if(active2.is_bot()) {
                rng.nextInt(0, active2.party.size());
            }
            else {
                System.out.println("Qual player irá atacar:");
                for(int i = 0; i < active1.party.size(); ++i) {
                    System.out.println("    " + i + " -> " + active1.party.get(i).name);
                }
                second_player_selected = in.nextInt();
            }
            p = active2.party.get(second_player_selected);
            int id2 = p.id;
            p.attack(active1.party, 0);
            active2.party.removeIf(Player::is_dead);
            Main.db.write("INSERT INTO Turno(id, id_batalha, id_atacante) VALUES(" + turno + "," + cod + "," + id1 +");");
            if(!active2.is_bot()) {
                Main.db.write("INSERT INTO Turno(id, id_batalha, id_atacante) VALUES(" + turno + "," + cod + "," + id2 +");");
            }
            ++turno;
        }
        System.out.println(active1.party.isEmpty() ?
                "Equipe 2 Vitoriosa" : "Equipe 1 Vitoriosa");
    }

    public void dump_info() {
//        for(var p : party) {
//            System.out.printf("name=%s%nClasse=%s%nLevel=%d%nhealth=%d%nattack=%d%ndefense=%d%n%n",p.name, p.getClass().getName(), p.level, p.health, p.atk, p.def);
//        }
    }

    public static Equipe random_team(int level_min, int level_max) throws SQLException {
        Random rng = new Random();
        ArrayList<Player> players = new ArrayList<>();
        int base_level = rng.nextInt(level_min, level_max+1);
        for(int i = 0; i < 3; ++i) {
            int option = rng.nextInt(0, 3);
            int diff = rng.nextInt(-2, 2+1);
            int level = Math.max(Math.min(base_level+diff, 10), 1);
            players.add(switch(option) {
                case 0 -> new Player("CPU " + (i+1), new Warrior(level));
                case 1 -> new Player("CPU " + (i+1), new Archer(level));
                case 2 -> new Player("CPU " + (i+1), new Mage(level));
                default -> throw new RuntimeException("");
            });
        }
        Equipe e = new Equipe(players, "CPU");
        Main.db.write("Insert into Equipe(nome) values ('CPU');");
        ResultSet res = Main.db.consulta("Select * from Equipe order by id desc limit 1;");
        res.next();
        e.id = Integer.parseInt(res.getString("id"));
        for(var player: players) {
            Main.db.write("Insert into Player(nickname, id_equipe, id_classe) values ('" + player.name + "'," + e.id + "," + player.class_id() + ");");
            ResultSet res2 = Main.db.consulta("Select * from Player order by id desc limit 1;");
            res2.next();
            player.set_id(res2.getInt("id"));
        }
        e.set_bot(true);
        return e;
    }
}