package br.ufjf.dcc.dcc025.westerosbattle.model.entities;

public final class Stark extends Character {

    public Stark(String name, Board board) {
        super(name, 60, 10, 20, 1,  board);
    }

    @Override
    public void fight(Character target) {
        if ( this.board.distance(this, target) > this.range ) {
            return;
        }
        int damage = this.attack - target.baseDefense;
        target.receiveDamage(damage);
    }

    @Override
    public void receiveDamage(int damage) {

        this.health -= (damage * 0.8);
        if(this.health <= 0){ this.health = 0; }
    }
}
