package br.ufjf.dcc.dcc025.westerosbattle.model.strategy;

import br.ufjf.dcc.dcc025.westerosbattle.model.entities.Board;
import br.ufjf.dcc.dcc025.westerosbattle.model.entities.Character;
import br.ufjf.dcc.dcc025.westerosbattle.model.enums.ActionType;
import br.ufjf.dcc.dcc025.westerosbattle.model.enums.Direction;
import br.ufjf.dcc.dcc025.westerosbattle.view.MenuView;

import java.util.List;

public class PlayerAction{
    private ActionType actionType;
    private Direction direction;
    private Character target;

    public PlayerAction(ActionType actionType, Direction direction) {
        this.actionType = actionType;
        this.direction = direction;
        this.target = null;
    }

    public PlayerAction(ActionType actionType, Character target) {
        this.actionType = actionType;
        this.target = target;
        this.direction = null;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public Direction getDirection() {
        return direction;
    }

    public Character getTarget() {
        return target;
    }
}

