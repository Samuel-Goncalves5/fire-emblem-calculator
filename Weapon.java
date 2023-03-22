package fr.epita.assistants.calculator;

public class Weapon {
    public int damage;
    public int crit;
    public int weight;
    public int precision;
    public int is_magic;
    public int durability;
    public Bonus linear = new Bonus(0);
    public Bonus percent = new Bonus(100);
    public String name;

    public Weapon(String[] split) {
        name = split[0];
        damage = Integer.parseInt(split[1]);
        crit = Integer.parseInt(split[2]);
        weight = Integer.parseInt(split[3]);
        precision = Integer.parseInt(split[4]);
        is_magic = Integer.parseInt(split[5]);
        durability = Integer.parseInt(split[6]);
        for (int i = 7; i < split.length; i++) {
            boolean named = false;
            for (NameBonus n : Main.bonus)
                if (n.name.equals(split[i]))
                {
                    percent.mult(n.percent);
                    linear.add(n.linear);
                    named = true;
                }
            if (named) continue;

            char option = split[i].charAt(3);
            int val = Integer.parseInt(split[i].substring(4));
            if (option == '-') val *= -1;
            if (option == '%') percent.mult(split[i].substring(0, 3), val);
            else linear.add(split[i].substring(0, 3), val);
        }
    }

    public Weapon(Weapon weapon) {
        damage = weapon.damage;
        crit = weapon.crit;
        weight = weapon.weight;
        precision = weapon.precision;
        is_magic = weapon.is_magic;
        durability = weapon.durability;
        name = weapon.name;
        linear = new Bonus(weapon.linear);
        percent = new Bonus(weapon.percent);
    }
}
