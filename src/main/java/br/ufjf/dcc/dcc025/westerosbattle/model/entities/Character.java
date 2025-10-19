package br.ufjf.dcc.dcc025.westerosbattle.model.entities;

public abstract class Character {
    protected String name;
    protected double health;
    protected int baseDefense, attack, range;

    public Character(String name, double health, int baseDefense, int attack, int range) {
        this.name = name;
        this.health = health;
        this.baseDefense = baseDefense;
        this.attack = attack;
        this.range = range;
    }

    public String getName() {
        return name;
    }

    public double getHealth() {
        return health;
    }

    public int getBaseDefense() {
        return baseDefense;
    }

    public int getAttack() {
        return attack;
    }

    public int getRange() {
        return range;
    }

    public boolean isAlive(){
        return this.health > 0;
    }

    public void reciveDamage(int damage) {
        this.health -= damage;
        if(this.health <= 0){ this.health = 0; }
    }
    public abstract void fight(Character character);
}
