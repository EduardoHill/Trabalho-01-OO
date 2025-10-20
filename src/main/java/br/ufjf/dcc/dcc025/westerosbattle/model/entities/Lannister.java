package br.ufjf.dcc.dcc025.westerosbattle.model.entities;

public final class Lannister extends Character {


    public Lannister(String name, Board board) {

        super(name, 50, 10, 20, 2, board);
    }

    @Override
    public void fight(Character target) {
        if ( this.board.distance(this, target) > this.range ) {
            return;
        }
        int damage = (int) ((this.attack - target.baseDefense) * 1.15);
        target.receiveDamage(damage);
    }

    @Override
    public void receiveDamage(int damage) {
        super.receiveDamage(damage);
    }
}
