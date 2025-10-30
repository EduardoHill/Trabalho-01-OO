package br.ufjf.dcc.dcc025.westerosbattle.view;

import br.ufjf.dcc.dcc025.westerosbattle.model.enums.Direction;
import br.ufjf.dcc.dcc025.westerosbattle.model.entities.Character;

import java.util.List;

public class MenuView {
    private final InputView input;


    public MenuView(InputView input) {
        this.input = input;
    }

    public void showWelcome(){
        System.out.println("\n======================================================");
        System.out.println("       A Batalha Tática das Casas de Westeros         ");
        System.out.println("======================================================");
    }

    public int mainMenu(){
        System.out.println("Menu Principal");
        System.out.println("[1] Novo jogo");
        System.out.println("[2] Sair");
        return input.readChoice("Escolha: " , 1, 2);
    }

    public int chooseGameMode(){
        System.out.println("\n=== Escolha o modo de jogo ===");
        System.out.println("[1] Jogador vs Jogador (PvP)");
        System.out.println("[2] Jogador vs Bot (PvBot)");
        System.out.println("[3] Bot vs Bot (Assistir)");
        return input.readChoice("Escolha o modo: ", 1, 3);
    }

    public int chooseCharacter(){
        System.out.println("Escolha um personagem: ");
        System.out.println("[1] Stark     (HP:60, DEF:10, ATK:20, RANGE: 1, EXP:DEF-Bruta ");
        System.out.println("[2] Lannister     (HP:50, DEF:10, ATK:20, RANGE: 2, EXP:ATK-LIQUIDO ");
        System.out.println("[3] Targaryen     (HP:45, DEF:10, ATK:20, RANGE: 3, EXP:ATK-BRUTO ");
        return input.readChoice("Escolher: ", 1, 3);
    }

    public int chooseAction(){
        System.out.println("Escolha uma ação: ");
        System.out.println("[1] Mover");
        System.out.println("[2] Atacar");
        return input.readChoice("Escolher: ", 1, 2);
    }

    public Direction chooseDirection(){
        return input.readDirection();
    }

    public Character chooseTarget(List<Character> targets){
        System.out.println("Escolha um alvo: ");
        for (int i = 0 ; i < targets.size(); i++){

            Character character = targets.get(i);
            System.out.println("[" + (i + 1) + "] " + character.getName() + " HP:" + String.format("%.2f", character.getHealth()) );
        }
        int aux = input.readChoice("Escolha seu personagem", 1, targets.size());
        return targets.get(aux-1);
    }

    public int postVictoryMenu(){
        System.out.println("\n=== Fim de Jogo ===");
        System.out.println("[1] Imprimir replay");
        System.out.println("[2] Novo jogo");
        System.out.println("[3] Sair");
        return input.readChoice("Escolha: ", 1, 3);
    }



}
