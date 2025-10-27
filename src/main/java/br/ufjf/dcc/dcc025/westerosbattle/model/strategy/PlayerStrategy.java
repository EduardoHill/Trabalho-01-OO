package br.ufjf.dcc.dcc025.westerosbattle.model.strategy;


import br.ufjf.dcc.dcc025.westerosbattle.model.entities.Board;
import br.ufjf.dcc.dcc025.westerosbattle.model.entities.Character;

import java.util.List;

public interface PlayerStrategy {

    PlayerAction decideTurn(Character player, List<Character> enemies, Board board);

}
