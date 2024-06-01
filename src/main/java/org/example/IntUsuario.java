package org.example;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author unifai
 */
public class IntUsuario {
    
    Equipe CriarEquipe(String nome_usuario, int cod_usuario_humano) throws SQLException {
                Scanner ler = new Scanner(System.in);
                PersonagemFactory player_factory = new PersonagemFactory(1);

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
                            players.add(new Player(nome, player_factory.makeGuerreiro()));
                            break;
                        }
                        case 1: {
                            players.add(new Player(nome, player_factory.makeArqueiro()));
                            break;
                        }
                        case 2: {
                            players.add(new Player(nome, player_factory.makeMago()));
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
                + "id -> Entrar ID player\n");
        int option = ler.nextInt();
        if(option == -1)
            return null;
        else if(option == 0){
            System.out.println("Digite seu nome de usuario: ");
            String nome_usuario = ler.next();
            Main.db.write("Insert into usuario_humano(nome_usuario) values('"+nome_usuario+"');");
            ResultSet res = Main.db.consulta("Select * from usuario_humano order by cod_usuario desc limit 1;");
            res.next();
            int cod = Integer.parseInt(res.getString("cod_usuario"));
            System.out.println();
            return CriarEquipe(nome_usuario, cod);
        }
        // Entrar ID Player
        return null;
    }
}
