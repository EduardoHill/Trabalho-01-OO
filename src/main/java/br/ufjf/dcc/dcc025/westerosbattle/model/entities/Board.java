package br.ufjf.dcc.dcc025.westerosbattle.model.entities;

import br.ufjf.dcc.dcc025.westerosbattle.model.enums.Direction;

import java.util.Random;

public class Board {
    private Character[][] board;
    private final int size = 10;

    public Board() {
        board = new Character[size][size];
    }

    public void setPosition(Character character, boolean side) {
        Random rand = new Random();
        int line, column;
        do {
            line = rand.nextInt(size);
            column = side ? rand.nextInt(size / 2) : rand.nextInt(size / 2, size);
        } while (board[line][column] != null);
        board[line][column] = character;
    }

    public int[] getPositon(Character character) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == character) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    public boolean move(Character character, Direction direction) {
        int[] position = getPositon(character);
        if (position == null) return false;

        int line = position[0];
        int column = position[1];
        int newLine = line, newColumn = column;


        switch (direction) {
            case CIMA -> newLine--;
            case BAIXO -> newLine++;
            case DIREITA -> newColumn++;
            case ESQUERDA -> newColumn--;
            case DIREITA_CIMA -> { newLine--; newColumn++; }
            case DIREITA_BAIXO -> { newLine++; newColumn++; }
            case ESQUERDA_CIMA -> { newLine--; newColumn--; }
            case ESQUERDA_BAIXO -> { newLine++; newColumn--; }
        }
        if (!isInside(newLine, newColumn)) return false;
        if (board[newLine][newColumn] != null) return false;


        board[line][column] = null;
        board[newLine][newColumn] = character;
        return true;
    }

    public  int distance(Character character, Character target) {
        int[] positionCharacter = getPositon(character);
        int[] positionTarget = getPositon(target);

        if (positionTarget == null || positionCharacter == null) return 0;

        return Math.max(Math.abs(positionCharacter[0] - positionTarget[0]), Math.abs(positionCharacter[1] - positionTarget[1]));
    }


    private boolean isInside(int line, int column) {
        return line >=0 && line < size && column >= 0 && column < size;
    }

    public void printBoard() {
        System.out.println("\n======= TABULEIRO  =======");
        for (int i = 0; i < size; i++) {
            System.out.print(" ");
            for (int j = 0; j < size; j++) {
                if (board[i][j] == null) {
                    System.out.print(". ");
                } else {
                    char ch = board[i][j].getName().charAt(0);
                    System.out.print(ch + " ");
                }
            }
            System.out.println();

        }
        System.out.println("=============================\n");
    }

}
