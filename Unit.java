package fr.epita.assistants.calculator;

public class Unit {
    public int life;
    public int strengh;
    public int magic;
    public int tech;
    public int speed;
    public int luck;
    public int res;
    public int def;
    public int charism;
    public int mouv;

    Squad s = null;

    String name;

    public Unit(String[] split) {
        name = split[0];
        life = Integer.parseInt(split[1]);
        strengh = Integer.parseInt(split[2]);
        magic = Integer.parseInt(split[3]);
        tech = Integer.parseInt(split[4]);
        speed = Integer.parseInt(split[5]);
        luck = Integer.parseInt(split[6]);
        def = Integer.parseInt(split[7]);
        res = Integer.parseInt(split[8]);
        charism = Integer.parseInt(split[9]);
        mouv = Integer.parseInt(split[10]);

        if (split.length == 11)
            return;

        for (Squad s : Main.squads)
            if (s.name.equals(split[11])) {
                this.s = s;
                return;
            }
    }

    public Unit(Unit unit) {
        name = unit.name;
        life = unit.life;
        strengh = unit.strengh;
        magic = unit.magic;
        tech = unit.tech;
        speed = unit.speed;
        luck = unit.luck;
        def = unit.def;
        res = unit.res;
        charism = unit.charism;
        mouv = unit.mouv;
        try { s = new Squad(unit.s); }
        catch (Exception e) { s = null; }
    }
}
