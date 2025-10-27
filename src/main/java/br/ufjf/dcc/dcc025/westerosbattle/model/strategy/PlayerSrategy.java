package br.ufjf.dcc.dcc025.westerosbattle.model.strategy;

import br.ufjf.dcc.dcc025.westerosbattle.model.entities.Board;

import java.util.List;

public interface PlayerSrategy {
    PlayerAction decideTurn(Character player, List<Character> enemies, Board board);

    PlayerAction decideTurn(br.ufjf.dcc.dcc025.westerosbattle.model.entities.Character player, List<br.ufjf.dcc.dcc025.westerosbattle.model.entities.Character> enemies, Board board);
}
