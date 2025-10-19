package br.ufjf.dcc.dcc025.westerosbattle;

import br.ufjf.dcc.dcc025.westerosbattle.model.entities.Board;
import br.ufjf.dcc.dcc025.westerosbattle.model.entities.Character;
import br.ufjf.dcc.dcc025.westerosbattle.model.entities.Lannister;
import br.ufjf.dcc.dcc025.westerosbattle.model.entities.Stark;
import br.ufjf.dcc.dcc025.westerosbattle.model.enums.Direction;

public class Main {
    public static void main(String[] args) {
        Board board = new Board();

        Character stark = new Stark("Stark");
        Character lannister = new Lannister("Lannister");

        board.setPosition(stark, true);
        board.setPosition(lannister, false);

        board.printBoard();

        board.move(stark, Direction.DIREITA);

        board.printBoard();

    }
}
