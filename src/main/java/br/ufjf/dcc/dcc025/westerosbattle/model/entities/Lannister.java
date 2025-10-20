package br.ufjf.dcc.dcc025.westerosbattle.model.entities;

public final class Lannister extends Character {


    public Lannister(String name, Board board) {

        super(name, 50, 10, 20, 2, board);
    }

    @Override
    public void fight(Character target) {
        super.fight(target);
    }

    @Override
    public int calculateDamage(Character target) {
        return (int) ((this.attack - target.baseDefense) * 1.15);
    }

    @Override
    public void receiveDamage(int damage) {
        super.receiveDamage(damage);
    }
}
