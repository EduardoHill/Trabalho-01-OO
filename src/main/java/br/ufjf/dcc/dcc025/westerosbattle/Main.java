package br.ufjf.dcc.dcc025.westerosbattle;

import br.ufjf.dcc.dcc025.westerosbattle.controller.GameController;
import br.ufjf.dcc.dcc025.westerosbattle.model.entities.*;
import br.ufjf.dcc.dcc025.westerosbattle.model.entities.Character;
import br.ufjf.dcc.dcc025.westerosbattle.model.enums.Direction;

public class Main {
    public static void main(String[] args) {
       new GameController().startGame();

    }
}
