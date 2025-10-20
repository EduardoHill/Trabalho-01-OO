package br.ufjf.dcc.dcc025.westerosbattle;

import br.ufjf.dcc.dcc025.westerosbattle.model.entities.*;
import br.ufjf.dcc.dcc025.westerosbattle.model.entities.Character;
import br.ufjf.dcc.dcc025.westerosbattle.model.enums.Direction;

public class Main {
    public static void main(String[] args) {
        Board board = new Board();

        Character jon = new Targaryen("Jon Snow", board);
        Character cersei = new Lannister("Cersei", board);

        board.setPosition(jon, true);
        board.setPosition(cersei, false);

        board.printBoard();
        System.out.println(cersei.getHealth());
        jon.fight(cersei);
        System.out.println(cersei.getHealth());

        board.move(jon, Direction.BAIXO);
        board.move(jon, Direction.ESQUERDA_BAIXO);
        board.printBoard();

        System.out.println("Distância entre Jon e Cersei: " + board.distance(jon, cersei));


    }
}
