package br.ufjf.dcc.dcc025.westerosbattle.model.entities;

public class Board {
    private int[][] board;
    private final int size = 10;

    public Board(int size) {
        board = new int[size][size];
    }

}
