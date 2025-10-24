package br.ufjf.dcc.dcc025.westerosbattle.controller;

import br.ufjf.dcc.dcc025.westerosbattle.model.entities.*;
import br.ufjf.dcc.dcc025.westerosbattle.model.entities.Character;
import br.ufjf.dcc.dcc025.westerosbattle.view.InputView;
import br.ufjf.dcc.dcc025.westerosbattle.view.MenuView;

import java.util.ArrayList;
import java.util.List;

public class GameController {
    private Board board;
    private GameLog gameLog;
    private InputView input;
    private MenuView menu;
    private int currentTurn;


    private List<Character> team1;
    private List<Character> team2;


    public GameController() {
        this.board = new Board();
        this.gameLog = new GameLog();
        this.currentTurn = 1;
        this.team1 = new ArrayList<>();
        this.team2 = new ArrayList<>();
        this.input = new InputView();
        this.menu = new MenuView(input);
    }

    public void startGame(){
        menu.showWelcome();
        int choice = menu.mainMenu();
        if (choice == 1){
            input.closeScanner();
            System.out.println("...Saindo");
            return;
        }

    }

    public void teamCreate(){
        System.out.println("Escolha seus personagens");
        team2.clear();
        team1.clear();

        for (int i = 1; i <= 2 ; i++){
            System.out.println("Jogador ["+i+"]");

            for (int j = 1; j <= 3 ; j++){
                System.out.println("Personagen [" + j + "]");
                String name = input.readString("Escolha o nome: ");
                int house = menu.chooseCharacter();
                Character character = creatCharacterByInt(house,name);
                board.setPosition(character, i == 1);

                if(i == 1){
                    team1.add(character);

                }
                else {
                    team2.add(character);
                }

                System.out.println(character.getName() + " criado e adicionado no time [" + i + "]");
            }
        }
    }

    public Character creatCharacterByInt(int house, String name){
       return switch (house){
           case 1 -> new Stark(name,board);
           case 2 -> new Lannister(name, board);
           case 3 -> new Targaryen(name, board);
           default -> throw new IllegalArgumentException("Casa invalida: " + house);
       };
    }

    public boolean isGameOver(){
        boolean team1Alive = team1.stream().allMatch(Character::isAlive);
        boolean team2Alive = team2.stream().allMatch(Character::isAlive);

        return !(team1Alive && team2Alive);
    }

    public int determineWinerTeam(){
        boolean team1Alive = team1.stream().allMatch(Character::isAlive);
        boolean team2Alive = team2.stream().allMatch(Character::isAlive);

        if(team1Alive && !team2Alive) return 1;
        if(!team1Alive && team2Alive) return 2;
        return 0;
    }

    private List<Character> getAliveEnimes(List<Character>team){
        List<Character> alive = new ArrayList<>();
        for(Character c : team){
            if(c != null && c.isAlive()) alive.add(c);

        }
        return alive;
    }


}
