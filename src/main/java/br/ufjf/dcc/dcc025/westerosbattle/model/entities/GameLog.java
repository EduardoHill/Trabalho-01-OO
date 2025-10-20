package br.ufjf.dcc.dcc025.westerosbattle.model.entities;

import java.util.ArrayList;
import java.util.List;

public class GameLog {
    List<Action> actions;


    public GameLog() {
        this.actions = new ArrayList<>();
    }

    public void setActions(Action action) {
        actions.add(action);
    }

    public List<Action> getActions() {
        return new ArrayList<>(actions);
    }


    public void printActions() {
       actions.forEach(action -> System.out.println(action.toString()));
    }
}
