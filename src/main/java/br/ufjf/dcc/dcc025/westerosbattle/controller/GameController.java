package br.ufjf.dcc.dcc025.westerosbattle.controller;

import br.ufjf.dcc.dcc025.westerosbattle.model.entities.Board;
import br.ufjf.dcc.dcc025.westerosbattle.model.entities.Character;
import br.ufjf.dcc.dcc025.westerosbattle.model.entities.GameLog;

import java.util.ArrayList;
import java.util.List;

public class GameController {
    private Board board;
    private GameLog gameLog;
    private int currentTurn;
    private List<Character> characters;


    public GameController() {
        this.board = new Board();
        this.gameLog = new GameLog();
        this.currentTurn = 1;
        this.characters = new ArrayList<>();
    }
}
