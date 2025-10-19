package br.ufjf.dcc.dcc025.westerosbattle.model.entities;

public final class Lannister extends Character {
    public Lannister(String name) {
        super(name, 50, 10, 20, 2);
    }

    @Override
    public void fight(Character character) {
        int damage = (int) ((this.attack - character.baseDefense) * 1.15);
        character.reciveDamage(damage);
    }

    @Override
    public void reciveDamage(int damage) {
        super.reciveDamage(damage);
    }
}
