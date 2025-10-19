package br.ufjf.dcc.dcc025.westerosbattle.model.entities;

import br.ufjf.dcc.dcc025.westerosbattle.model.enums.Direction;

import java.util.Random;

public class Board {
    private Integer[][] board;
    private final int size = 10;

    public Board() {
        board = new Integer[size][size];
    }

    public void setPosition(Character character, boolean side) {
        Random rand = new Random();
        int line, column;
        do {
            line = rand.nextInt(size);
            column = side ? rand.nextInt(size / 2) : rand.nextInt(size / 2, size);
        } while (board[line][column] != null);
    }

    public void move(Character character, Direction direction) {

    }

}
