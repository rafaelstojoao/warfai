package org.example;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class IntUsuario {
    
    Equipe CriarEquipe(String nome_usuario, int cod_usuario_humano) throws SQLException {
                Scanner ler = new Scanner(System.in);

                ArrayList<Player> players = new ArrayList<>();
                for(int i = 0; i < 3; i++) {
                    System.out.println("Digite seu nickname: ");
                    String nome = ler.next();
                    System.out.println("Selecione sua classe:\n"
                            + "0 -> Guerreiro\n"
                            + "1 -> Arqueiro\n"
                            + "2 -> Mago\n");
                    int player_class = ler.nextInt();
                    switch(player_class) {
                        case 0: {
                            players.add(new Player(nome, new Warrior()));
                            break;
                        }
                        case 1: {
                            players.add(new Player(nome, new Archer()));
                            break;
                        }
                        case 2: {
                            players.add(new Player(nome, new Mage()));
                            break;
                        }
                    }
                }    
            return new Equipe(players, nome_usuario, cod_usuario_humano);
    }
    /**
    * -1 -> Sair
    * 0 -> Criar equipe
    * id -> Entrar ID Player
    */
    public Equipe MenuEquipe() throws SQLException{
        Scanner ler = new Scanner(System.in);
        System.out.println("Selecione uma opção:\n"
                + "-1 -> Sair\n"
                + "0 -> Criar equipe\n"
                + "id -> Entrar ID Equipe\n");
        int option = ler.nextInt();
        if(option == -1)
            return null;
        else if(option == 0){
            System.out.println("Digite seu nome de usuario: ");
            String nome_usuario = ler.next();
            Main.db.write("Insert into usuario_humano(nome) values('"+nome_usuario+"');");
            ResultSet res = Main.db.consulta("Select * from usuario_humano order by id desc limit 1;");
            res.next();
            int cod = Integer.parseInt(res.getString("id"));
            Equipe e = CriarEquipe(nome_usuario, cod);
            System.out.println("O ID da sua equipe é: " + e.id());
            return e;
        }

        ResultSet res = Main.db.consulta("SELECT e.id, e.nome, p.id, p.nickname, p.nivel, p.exp, p.id_classe " +
                "FROM Equipe AS e " +
                "INNER JOIN Player AS p ON e.id = p.id_equipe " +
                "WHERE e.id = " + option + " AND id_usuario_humano IS NOT NULL;");
        res.next();
        int id = res.getInt("e.id");
        String nome_equipe = res.getString("e.nome");
        ArrayList<Player> players = new ArrayList<>();
        for(int i = 0; i < 3; ++i) {
            int p_id = res.getInt("p.id");
            String p_name = res.getString("p.nickname");
            int id_classe = res.getInt("p.id_classe");
            players.add(switch (id_classe) {
                case 1: {
                    Player p = new Player(p_name, new Warrior());
                    p.set_id(p_id);
                    yield p;
                }
                case 2: {
                    Player p = new Player(p_name, new Archer());
                    p.set_id(p_id);
                    yield p;
                }
                case 3: {
                    Player p = new Player(p_name, new Mage());
                    p.set_id(p_id);
                    yield p;
                }
                default: yield null;
            });
            res.next();
        }
        Equipe e = new Equipe(players, nome_equipe, id);
        // Entrar ID Player
        return e;
    }
}
