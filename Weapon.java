package fr.epita.assistants.calculator;

public class Weapon {
    public int damage;
    public int crit;
    public int weight;
    public int precision;
    public int is_magic;
    public int durability;
    public String name;

    public Weapon(String[] split) {
        name = split[0];
        damage = Integer.parseInt(split[1]);
        crit = Integer.parseInt(split[2]);
        weight = Integer.parseInt(split[3]);
        precision = Integer.parseInt(split[4]);
        is_magic = Integer.parseInt(split[5]);
        durability = Integer.parseInt(split[6]);
    }

    public Weapon(Weapon weapon) {
        damage = weapon.damage;
        crit = weapon.crit;
        weight = weapon.weight;
        precision = weapon.precision;
        is_magic = weapon.is_magic;
        durability = weapon.durability;
        name = weapon.name;
    }
}
