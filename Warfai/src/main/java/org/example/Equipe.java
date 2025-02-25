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
    private ArrayList<Personagem> party = new ArrayList<>();
    private boolean bot = false; 
    private int id = 0;
    /**
     * 
     */
    //private boolean ativa = true;

    public Equipe(ArrayList<Personagem> p, String nome) throws SQLException {
        for(int i = 0; i < p.size(); ++i) {
            party.add(p.get(i));
        }
    }

    public Equipe(ArrayList<Personagem> p, String nome, int cod_usuario_humano) throws SQLException {
        Main.db.write("Insert into equipe(nome, cod_usuario_humano) values ('"+ nome + "',"+ cod_usuario_humano +");");
        ResultSet res = Main.db.consulta("Select * from equipe order by cod_equipe desc limit 1;");
        res.next();
        this.id = Integer.parseInt(res.getString("cod_equipe"));
        
        for(int i = 0; i < p.size(); ++i) {
            System.out.println("Insert into player(nickname, cod_equipe, cod_classe) values ('" + p.get(i).Nome + "'," + this.id + "," + p.get(i).Cod_Classe + ");");
            Main.db.write("Insert into player(nickname, cod_equipe, cod_classe) values ('" + p.get(i).Nome + "'," + this.id + "," + p.get(i).Cod_Classe + ");");
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

    /**
     * @param p
     */
    public void add_player(Personagem p) {
        party.add(p);
    }

    /**
     * @param p
     */
    public void remove_player(Personagem p) {
        party.remove(p);
    }
    
    public boolean is_bot() { return bot; }
    public void set_bot(boolean bot) { this.bot = bot; } 
    /**
     * @param e
     */
    public void battle(Equipe e) throws SQLException {
        Equipe active1 = null;
        Equipe active2 = null;
        Random rng = new Random();
        if(rng.nextInt(0, 101) > 50) {
            active1 = new Equipe(this);
            active2 = new Equipe(e);
        }
        else {
            active2 = new Equipe(this);
            active1 = new Equipe(e);     
        }

        Main.db.write("INSERT INTO batalhas(cod_equipe1, cod_equipe2) VALUES(" + this.id + "," + e.id + ");");
        ResultSet res = Main.db.consulta("Select * from batalhas order by cod_batalha desc limit 1;");
        res.next();
        int cod = Integer.parseInt(res.getString("cod_batalha"));
        while(!active1.party.isEmpty() && !active2.party.isEmpty()) {
            int player_selected = rng.nextInt(0, active1.party.size());
            Personagem p = active1.party.get(player_selected);
            p.ataque(active2.party);
            active1.party.removeIf(v -> v.Vida <= 0);
            
            if(active1.party.isEmpty())
            {
                Main.db.write("INSERT INTO turno(cod_batalha) VALUES(" + cod + ");");
                break;
            }
            
            player_selected = rng.nextInt(0, active2.party.size());
            p = active2.party.get(player_selected);
            p.ataque(active1.party);
            active2.party.removeIf(v -> v.Vida <= 0);
            Main.db.write("INSERT INTO turno(cod_batalha) VALUES(" + cod + ");");
        }
        System.out.println(active1.party.isEmpty() ?
                "Equipe 2 Vitoriosa" : "Equipe 1 Vitoriosa");
    }

    /**
     * 
     */
    public void dump_info() {
        for(var p : party) {
            System.out.printf("Nome=%s%nClasse=%s%nLevel=%d%nVida=%d%nAtaque=%d%nDefesa=%d%n%n",p.Nome, p.getClass().getName(), p.Nivel, p.Vida, p.Ataque, p.Defesa);
        }
    }

    public static Equipe equipe_random() throws SQLException {
        Random rng = new Random();
        PersonagemFactory player_factory = new PersonagemFactory(1);
        ArrayList<Personagem> players = new ArrayList<>();
        for(int i = 0; i < 3; ++i) {
            int option = rng.nextInt(0, 3);
            switch(option) {
                case 0: {
                    players.add(player_factory.makeGuerreiro("CPU " + (i + 1)));
                    break;
                }
                case 1: {
                    players.add(player_factory.makeArqueiro("CPU " + (i+1)));
                    break;
                }
                case 2: {
                    players.add(player_factory.makeMago("CPU " + (i+1)));
                    break;
                }
            }
        }
       Equipe e = new Equipe(players, "CPU");
        e.set_bot(true); 
        return e;
    }
}