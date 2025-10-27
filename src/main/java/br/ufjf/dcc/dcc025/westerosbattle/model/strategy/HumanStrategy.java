package br.ufjf.dcc.dcc025.westerosbattle.model.strategy;

import br.ufjf.dcc.dcc025.westerosbattle.model.entities.Board;
import br.ufjf.dcc.dcc025.westerosbattle.model.entities.Character;
import br.ufjf.dcc.dcc025.westerosbattle.model.enums.ActionType;
import br.ufjf.dcc.dcc025.westerosbattle.model.enums.Direction;
import br.ufjf.dcc.dcc025.westerosbattle.view.MenuView;

import java.util.List;

public class HumanStrategy implements PlayerStrategy {
    private MenuView menu;

    public HumanStrategy(MenuView menu) {
        this.menu = menu;
    }


    @Override
    public PlayerAction decideTurn(Character player, List<Character> enemies, Board board) {
        int action = menu.chooseAction();

        if(action == 1){
            Direction dir = menu.chooseDirection();
            return new PlayerAction(ActionType.MOVE, dir);
        } else {
            List<Character> enemiesAlive = enemies.stream().filter(Character::isAlive).toList();

            if(enemiesAlive.isEmpty()){
                System.out.println("Nenhum inimigo vivo!");
                return null;
            }

            Character target = menu.chooseTarget(enemiesAlive);
            return new PlayerAction(ActionType.ATTACK, target);
        }
    }
}
