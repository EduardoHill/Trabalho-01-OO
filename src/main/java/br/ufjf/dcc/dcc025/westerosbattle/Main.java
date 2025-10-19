package br.ufjf.dcc.dcc025.westerosbattle;

import br.ufjf.dcc.dcc025.westerosbattle.model.entities.Board;
import br.ufjf.dcc.dcc025.westerosbattle.model.entities.Character;
import br.ufjf.dcc.dcc025.westerosbattle.model.entities.Lannister;
import br.ufjf.dcc.dcc025.westerosbattle.model.entities.Stark;
import br.ufjf.dcc.dcc025.westerosbattle.model.enums.Direction;

public class Main {
    public static void main(String[] args) {
        Board board = new Board();

        Character jon = new Stark("Jon Snow");
        Character cersei = new Lannister("Cersei");

        board.setPosition(jon, true);
        board.setPosition(cersei, false);

        board.printBoard();

        board.move(jon, Direction.BAIXO);
        board.move(jon, Direction.ESQUERDA_BAIXO);
        board.printBoard();

        System.out.println("Distância entre Jon e Cersei: " + board.distance(jon, cersei));


    }
}
