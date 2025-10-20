package br.ufjf.dcc.dcc025.westerosbattle.model.entities;

public final class Targaryen  extends Character {
    public Targaryen(String name, Board board) {
        super(name, 45, 10, 20, 3, board);
    }

    @Override
    public void fight(Character target) {
       super.fight(target);
    }

    @Override
    public int calculateDamage(Character target) {
        return this.attack;
    }

    @Override
    public void receiveDamage(int damage) {
        super.receiveDamage(damage);
    }
}
