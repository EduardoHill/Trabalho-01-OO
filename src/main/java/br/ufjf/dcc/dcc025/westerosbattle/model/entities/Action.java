package br.ufjf.dcc.dcc025.westerosbattle.model.entities;

import br.ufjf.dcc.dcc025.westerosbattle.model.enums.ActionType;

public class Action {
    private int turn;
    private  Character player;
    private String details;

    private ActionType type;

    public Action(int turn, Character player, String details, ActionType type) {
        this.turn = turn;
        this.player = player;
        this.details = details;
        this.type = type;
    }

    public int getTurn() {
        return turn;
    }

    public Character getPlayer() {
        return player;
    }

    public String getDetails() {
        return details;
    }

    public ActionType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Actions{" +
                "turn=" + turn +
                ", player=" + player +
                ", details='" + details + '\'' +
                ", type=" + type +
                '}';
    }
}
