package br.ufjf.dcc.dcc025.westerosbattle.model.entities;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private Board board;
    private GameLog gameLog;
    private int currentTurn;
    private List<Character> characters;


    public Game() {
        this.board = new Board();
        this.gameLog = new GameLog();
        this.currentTurn = 1;
        this.characters = new ArrayList<>();
    }
}
