package br.ufjf.dcc.dcc025.westerosbattle.model.entities;

import java.util.List;

public class GameLog {
    List<Actions> actions;

    public void setActions(Actions action) {
        actions.add(action);
    }


    public String getActions() {
        for(Actions action : actions) {
            System.out.println(action.toString());
        }
    }
}
