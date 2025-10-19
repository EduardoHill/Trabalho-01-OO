package br.ufjf.dcc.dcc025.westerosbattle.model.entities;

public final class Targaryen  extends Character {
    public Targaryen(String name) {
        super(name, 45, 10, 20, 3);
    }

    @Override
    public void fight(Character character ) {
        int damage = this.attack;
        character.reciveDamage(damage);
    }

    @Override
    public void reciveDamage(int damage) {
        super.reciveDamage(damage);
    }
}
