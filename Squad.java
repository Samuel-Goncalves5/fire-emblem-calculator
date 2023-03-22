package fr.epita.assistants.calculator;

public class Squad {
    public int damage;
    public int precision;
    public String attack_name;
    public Bonus linear = new Bonus(0);
    public Bonus percent = new Bonus(100);
    public int is_magic;
    public String name;

    public Squad(String[] split) {
        name = split[0];
        damage = Integer.parseInt(split[1]);
        precision = Integer.parseInt(split[2]);
        is_magic = Integer.parseInt(split[3]);
        attack_name = split[4];
        for (int i = 5; i < split.length; i++) {
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

    public Squad(Squad s) {
        name = s.name;
        damage = s.damage;
        precision = s.precision;
        is_magic = s.is_magic;
        attack_name = s.attack_name;
        linear = new Bonus(s.linear);
        percent = new Bonus(s.percent);
    }
}
