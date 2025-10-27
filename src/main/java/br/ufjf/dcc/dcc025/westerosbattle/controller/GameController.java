package br.ufjf.dcc.dcc025.westerosbattle.controller;

import br.ufjf.dcc.dcc025.westerosbattle.model.entities.*;
import br.ufjf.dcc.dcc025.westerosbattle.model.entities.Character;
import br.ufjf.dcc.dcc025.westerosbattle.model.enums.ActionType;
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
        if (choice == 2){
            input.closeScanner();
            System.out.println("...Saindo");
            return;
        }
        teamCreate();
        gameLoop();

        int winerTeam = determineWinerTeam();
        switch (winerTeam){
            case 1 -> System.out.println("Vitoria do time 1");
            case 2 -> System.out.println("Vitoria do time 2");
            default -> System.out.println("Empate ou erro ao determinar ganhador");
        }
        System.out.println("======== Historico do Game ========");
        gameLog.printActions();
        input.closeScanner();

    }

    private void teamCreate(){
        System.out.println("Escolha seus personagens");
        team2.clear();
        team1.clear();

        for (int i = 1; i <= 2 ; i++){
            System.out.println("Jogador "+i);

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

    private Character creatCharacterByInt(int house, String name){
       return switch (house){
           case 1 -> new Stark(name,board);
           case 2 -> new Lannister(name, board);
           case 3 -> new Targaryen(name, board);
           default -> throw new IllegalArgumentException("Casa invalida: " + house);
       };
    }

    private int determineWinerTeam(){
        boolean team1Winer = teamAlive(team1);
        boolean team2Winer = teamAlive(team2);

        if(team1Winer && !team2Winer) return 1;
        if(!team1Winer && team2Winer) return 2;
        return 0;
    }

    private boolean teamAlive(List<Character> team){
        return team.stream().anyMatch(Character::isAlive);
    }

    private boolean isGameOver(){
        return !(teamAlive(team1) && teamAlive(team2));
    }

    private List<Character> getAliveEnimes(List<Character>team){
        return team.stream().filter(Character::isAlive).toList();
    }



    private void handeTurn(Character actor, List<Character> enemyTeam){
        System.out.println("\nTurno [" + currentTurn + "] - Jogador: " + actor.getName());

        int action = menu.chooseAction();
        switch (action){
            case 1 -> {
                var dir = menu.chooseDirection();
                boolean moved = board.move(actor,dir);

                if (moved){
                    System.out.println(actor.getName() + " moveu para [" + dir + "] ");
                    gameLog.setActions(new Action(currentTurn,actor," Moveu para " + dir + " ", ActionType.MOVE));
                    board.printBoard();
                }else {
                    System.out.println("Movimento invalido");
                }
            }

            case 2 ->{
                List<Character> enimies = getAliveEnimes(enemyTeam);
                if (enimies.isEmpty()) {
                    System.out.println("Nenhum inimigo vivo disponivel");
                    break;
                }
                Character target = menu.chooseTarget(enimies);
                int distance = board.distance(actor,target);
                if (distance <= actor.getRange()){
                    actor.fight(target);
                    gameLog.setActions(new Action(currentTurn,actor," Atacou - [ " + target.getName()+" ] e ele ficou com " + target.getHealth() + " ]", ActionType.ATTACK ));
                    System.out.println(actor.getName() + " Atacou - " + target.getName());

                    if (!target.isAlive()){
                        System.out.println("O ataque foi potente " + target.getName() + " nao esta mais entre nos");
                        board.removeChracter(target);
                    }
                }else {
                    System.out.println("O alvo esta muito longe");
                    System.out.println(actor.getName() + " perdeu a vez :(");
                }

            }

            default -> {
                System.out.println("Acao invalida, perdeu a vez!!!");
            }

        }
    }

    private void gameLoop(){
        outer:
        while (!isGameOver()){
            board.printBoard();
            for (int i = 0; i < 3 ; i++){
                if (i < team1.size()){
                    Character actor = team1.get(i);
                    if (actor.isAlive()){
                        handeTurn(actor,team2);
                        if(isGameOver()) break outer;
                    }
                }

                if (i < team2.size()){
                    Character actor = team2.get(i);
                    if (actor.isAlive()){
                        handeTurn(actor,team1);
                        if(isGameOver()) break outer;
                    }
                }
            }
            currentTurn++;
        }
    }



}




















