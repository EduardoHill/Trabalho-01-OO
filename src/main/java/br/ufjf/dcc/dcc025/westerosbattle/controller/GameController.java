package br.ufjf.dcc.dcc025.westerosbattle.controller;

import br.ufjf.dcc.dcc025.westerosbattle.model.entities.*;
import br.ufjf.dcc.dcc025.westerosbattle.model.entities.Character;
import br.ufjf.dcc.dcc025.westerosbattle.model.enums.ActionType;
import br.ufjf.dcc.dcc025.westerosbattle.model.strategy.BotStrategy;
import br.ufjf.dcc.dcc025.westerosbattle.model.strategy.HumanStrategy;
import br.ufjf.dcc.dcc025.westerosbattle.model.strategy.PlayerAction;
import br.ufjf.dcc.dcc025.westerosbattle.model.strategy.PlayerStrategy;
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

    private List<PlayerStrategy> team1Strategies;
    private List<PlayerStrategy> team2Strategies;


    public GameController() {
        this.board = new Board();
        this.gameLog = new GameLog();
        this.currentTurn = 1;
        this.team1 = new ArrayList<>();
        this.team2 = new ArrayList<>();
        this.team1Strategies = new ArrayList<>();
        this.team2Strategies = new ArrayList<>();
        this.input = new InputView();
        this.menu = new MenuView(input);
    }

    public void startGame(){
        menu.showWelcome();


        outerStart:
        while (true) {
            int choice = menu.mainMenu();
            if (choice == 2) {
                input.closeScanner();
                System.out.println("...Saindo");
                return;
            }

            int gameMode = menu.chooseGameMode();
            setupStrategies(gameMode);
            teamCreate(gameMode);
            gameLoop();

            int winerTeam = determineWinerTeam();
            switch (winerTeam) {
                case 1 -> System.out.println("Vitoria do time 1");
                case 2 -> System.out.println("Vitoria do time 2");
                default -> System.out.println("Empate ou erro ao determinar ganhador");
            }

            while (true) {
                int post = menu.postVictoryMenu();
                switch (post) {
                    case 1 -> {
                        System.out.println("======== Historico do Game ========");
                        gameLog.printActions();
                    }
                    case 2 -> {
                        resetGameState();
                        continue outerStart;
                    }
                    case 3 -> {
                        input.closeScanner();
                        System.out.println("...Saindo");
                        return;
                    }
                    default -> System.out.println("Opcao invalida");
                }
            }
        }
    }

    private void resetGameState() {
        this.board = new Board();
        this.gameLog = new GameLog();
        this.currentTurn = 1;
        this.team1.clear();
        this.team2.clear();
        this.team1Strategies.clear();
        this.team2Strategies.clear();
    }

    private void setupStrategies(int gameMode) {
        team1Strategies.clear();
        team2Strategies.clear();

        switch (gameMode) {
            case 1 -> { // PvP
                for (int i = 0; i < 3; i++) {
                    team1Strategies.add(new HumanStrategy(menu));
                    team2Strategies.add(new HumanStrategy(menu));
                }
                System.out.println("Modo PvP selecionado!");
            }
            case 2 -> { // PvBot
                for (int i = 0; i < 3; i++) {
                    team1Strategies.add(new HumanStrategy(menu));
                    team2Strategies.add(new BotStrategy());
                }
                System.out.println("Modo PvBot selecionado! Você enfrenta o BOT!");
            }
            case 3 -> { // BotVsBot
                for (int i = 0; i < 3; i++) {
                    team1Strategies.add(new BotStrategy());
                    team2Strategies.add(new BotStrategy());
                }
                System.out.println("Modo BotVsBot selecionado! Assista a batalha!");
            }
        }
    }

    private void teamCreate(int gameMode){
        System.out.println("Escolha seus personagens");
        team2.clear();
        team1.clear();

        for (int i = 1; i <= 2 ; i++){
            boolean isBot = (i == 2 && gameMode == 2) || gameMode == 3;

            if (isBot) {
                System.out.println("\n=== Time " + i + " (BOT) ===");
            } else {
                System.out.println("\n=== Jogador " + i + " ===");
            }

            for (int j = 1; j <= 3 ; j++){
                System.out.println("Personagem [" + j + "]");

                String name;
                int house;

                if (isBot) {
                    // Bot escolhe aleatoriamente
                    name = "Bot" + i + "-" + j;
                    house = (int) (Math.random() * 3) + 1;
                    String houseName = switch (house) {
                        case 1 -> "Stark";
                        case 2 -> "Lannister";
                        case 3 -> "Targaryen";
                        default -> "Unknown";
                    };
                    System.out.println("BOT escolheu: " + name + " (" + houseName + ")");
                } else {
                    name = input.readString("Escolha o nome: ");
                    house = menu.chooseCharacter();
                }

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



    private void handeTurn(Character actor, List<Character> enemyTeam, PlayerStrategy strategy){
    System.out.println("\nTurno [" + currentTurn + "] - Jogador: " + actor.getName() + " (HP: " + String.format("%.2f", actor.getHealth()) + ")");

        PlayerAction action = strategy.decideTurn(actor, enemyTeam, board);

        if (action == null) {
            System.out.println(actor.getName() + " não conseguiu decidir uma ação. Vez perdida.");
            return;
        }

        executeAction(actor, action, enemyTeam);
    }

    private void executeAction(Character actor, PlayerAction action, List<Character> enemyTeam) {
        switch (action.getActionType()) {
            case MOVE -> {
                var dir = action.getDirection();
                boolean moved = board.move(actor, dir);

                if (moved) {
                    System.out.println(actor.getName() + " moveu para [" + dir + "] ");
                    gameLog.setActions(new Action(currentTurn, actor, " Moveu para " + dir + " ", ActionType.MOVE));
                    board.printBoard();
                } else {
                    System.out.println("Movimento invalido");
                }
            }

            case ATTACK -> {
                Character target = action.getTarget();
                int distance = board.distance(actor, target);

                if (distance <= actor.getRange()) {
                    actor.fight(target);
                    gameLog.setActions(new Action(currentTurn, actor, " Atacou - [ " + target.getName() + " ] e ele ficou com [ " + String.format("%.2f", target.getHealth()) + " ]", ActionType.ATTACK));
                    System.out.println(actor.getName() + " Atacou - " + target.getName());

                    if (!target.isAlive()) {
                        System.out.println("O ataque foi potente " + target.getName() + " nao esta mais entre nos");
                        board.removeChracter(target);
                    }
                } else {
                    System.out.println("O alvo esta muito longe (distância: " + distance + ", alcance: " + actor.getRange() + ")");
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
                    PlayerStrategy strategy = team1Strategies.get(i);
                    if (actor.isAlive()){
                        handeTurn(actor, team2, strategy);
                        if(isGameOver()) break outer;
                    }
                }

                if (i < team2.size()){
                    Character actor = team2.get(i);
                    PlayerStrategy strategy = team2Strategies.get(i);
                    if (actor.isAlive()){
                        handeTurn(actor, team1, strategy);
                        if(isGameOver()) break outer;
                    }
                }
            }
            currentTurn++;
        }
    }



}




















