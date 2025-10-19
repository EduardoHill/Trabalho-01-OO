package br.ufjf.dcc.dcc025.westerosbattle.model.entities;

public final class Stark extends Character {

    public Stark(String name) {
        super(name, 60, 10, 20, 1);
    }

    @Override
    public void fight(Character character) {
        int damage = this.attack - character.baseDefense;
        character.reciveDamage(damage);
    }

    @Override
    public void reciveDamage(int damage) {

        this.health -= (damage * 0.8);
        if(this.health <= 0){ this.health = 0; }
    }
}
