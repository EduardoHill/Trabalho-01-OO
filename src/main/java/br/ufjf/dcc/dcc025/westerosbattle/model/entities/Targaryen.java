package br.ufjf.dcc.dcc025.westerosbattle.model.entities;

public final class Targaryen  extends Character {
    public Targaryen(String name, Board board) {
        super(name, 45, 10, 20, 3, board);
    }

    @Override
    public void fight(Character target) {
        if ( this.board.distance(this, target) > this.range ) {
            return;
        }

        int damage = this.attack;
        target.receiveDamage(damage);
    }

    @Override
    public void receiveDamage(int damage) {
        super.receiveDamage(damage);
    }
}
