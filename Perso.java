import static java.lang.Math.max;

public class Perso {
    public Unit unit = null;
    public Weapon weapon = null;
    public Squad squad = null;
    public Bonus linear = new Bonus(0);
    public Bonus percent = new Bonus(100);

    public Perso() {}

    public Perso(Perso p) {
        try { unit = new Unit(p.unit); }
        catch (Exception e) { unit = null; }
        try { weapon = new Weapon(p.weapon); }
        catch (Exception e) { weapon = null; }
        linear = new Bonus(linear);
        percent = new Bonus(percent);
    }

    public boolean prepare(Perso def, boolean needSquad) {
        if (unit != null) squad = unit.s;
        if (def.unit != null) def.squad = def.unit.s;

        if (squad != null) Bonus.apply(squad.linear, squad.percent, unit, weapon, squad);
        if (weapon != null) Bonus.apply(weapon.linear, weapon.percent, unit, weapon, squad);
        Bonus.apply(linear, percent, unit, weapon, squad);
        if (def.squad != null) Bonus.apply(def.squad.linear, def.squad.percent, def.unit, def.weapon, def.squad);


        if (def.weapon != null) Bonus.apply(def.weapon.linear, def.weapon.percent, def.unit, def.weapon, def.squad);
        Bonus.apply(def.linear, def.percent, def.unit, def.weapon, def.squad);

        boolean error = false;
        System.out.print(Interface.RED);
        if (unit == null) {
            System.out.println("L'attaquant est invalide.");
            error = true;
        }
        if (def.unit == null) {
            System.out.println("Le defenseur est invalide.");
            error = true;
        }
        if (weapon == null && !needSquad) {
            System.out.println("L'arme de l'attaquant est invalide.");
            error = true;
        }
        if (squad == null && needSquad) {
            System.out.println("L'escouade de l'attaquant est invalide.");
            error = true;
        }

        System.out.print(Interface.YELLOW);

        if (weapon == null && needSquad) {
            System.out.println("L'attaquant est desarme.");
        }
        if (def.weapon == null) {
            System.out.println("Le defenseur est desarme.");
        }
        if (squad == null && !needSquad) {
            System.out.println("L'attaquant n'a pas d'escouade.");
        }
        if (def.squad == null) {
            System.out.println("Le defenseur n'a pas d'escouade.");
        }
        System.out.print(Interface.RESET);
        return error;
    }

    public void attaque(Perso def) {
        if (prepare(def, false))
            return;

        int speed1 = unit.speed + unit.strengh / 5 - weapon.weight;
        if (speed1 > unit.speed)
            speed1 = unit.speed;
        int speed2 = def.unit.speed + def.unit.strengh / 5 - (def.weapon != null ? def.weapon.weight : 0);
        if (speed2 > def.unit.speed)
            speed2 = def.unit.speed;

        int esquive1 = speed1;
        if (squad != null)
        {
            esquive1 *= squad.percent.esquive;
            esquive1 /= 100;
            esquive1 += squad.linear.esquive;
        }

        esquive1 *= weapon.percent.esquive;
        esquive1 /= 100;
        esquive1 += weapon.linear.esquive;

        esquive1 *= percent.esquive;
        esquive1 /= 100;
        esquive1 += linear.esquive;

        int esquive2 = speed2;
        if (def.squad != null)
        {
            esquive2 *= def.squad.percent.esquive;
            esquive2 /= 100;
            esquive2 += def.squad.linear.esquive;
        }
        if (def.weapon != null)
        {
            esquive2 *= def.weapon.percent.esquive;
            esquive2 /= 100;
            esquive2 += def.weapon.linear.esquive;
        }
        esquive2 *= def.percent.esquive;
        esquive2 /= 100;
        esquive2 += def.linear.esquive;

        // p1 stats
        int degats1; int precision1; int critique1; int nombre1 = 1;
        if (weapon.is_magic == 0)
            degats1 = weapon.damage + unit.strengh - def.unit.def;
        else
            degats1 = weapon.damage + unit.magic - def.unit.res;

        precision1 = weapon.precision + unit.tech - esquive2;
        critique1 = weapon.crit + (unit.tech + unit.luck)/2 - def.unit.luck;

        // p2 stats
        int degats2; int precision2; int critique2; int nombre2 = 1;
        if (def.weapon != null)
        {
            if (def.weapon.is_magic != 0)
                degats2 = def.weapon.damage + def.unit.strengh - unit.def;
            else
                degats2 = def.weapon.damage + def.unit.magic - unit.res;
        }
        else
            degats2 = 0;

        precision2 = def.weapon != null ? def.weapon.precision + def.unit.tech - esquive1 : 0;
        critique2 = def.weapon != null ? def.weapon.crit + (def.unit.tech + def.unit.luck)/2 - unit.luck : 0;

        int speed_diff = speed1 - speed2;
        if (speed_diff >= 4)
            nombre1 *= 2;
        else if (speed_diff <= -4)
            nombre2 *= 2;

        if (def.weapon == null)
            nombre2 = 0;

        if (squad != null)
        {
            nombre1 *= squad.percent.nombre;
            nombre1 /= 100;
            nombre1 += squad.linear.nombre;
        }

        nombre1 *= weapon.percent.nombre;
        nombre1 /= 100;
        nombre1 += weapon.linear.nombre;

        nombre1 *= percent.nombre;
        nombre1 /= 100;
        nombre1 += linear.nombre;

        if (def.squad != null)
        {
            nombre2 *= def.squad.percent.nombre;
            nombre2 /= 100;
            nombre2 += def.squad.linear.nombre;
        }
        if (def.weapon != null)
        {
            nombre2 *= def.weapon.percent.nombre;
            nombre2 /= 100;
            nombre2 += def.weapon.linear.nombre;
        }
        nombre2 *= def.percent.nombre;
        nombre2 /= 100;
        nombre2 += def.linear.nombre;

        precision1 = max(precision1,0);
        degats1 = max(degats1,0);
        nombre1 = max(nombre1,0);
        critique1 = max(critique1,0);
        precision2 = max(precision2,0);
        degats2 = max(degats2,0);
        nombre2 = max(nombre2,0);
        critique2 = max(critique2,0);

        System.out.println();
        System.out.println(Interface.YELLOW + unit.name + " :");
        System.out.println("\tPRC = " + precision1);
        System.out.println("\tDMG = " + degats1 + " x " + nombre1);
        System.out.println("\tCRT = " + critique1);
        System.out.println(def.unit.name + " :");
        System.out.println("\tPRC = " + precision2);
        System.out.println("\tDMG = " + degats2 + " x " + nombre2);
        System.out.println("\tCRT = " + critique2);
        System.out.println();
        System.out.println(Interface.GREEN + "Lancer la simulation ?");
        System.out.println("oui/non > " + Interface.RESET);
        String input = Interface.s.nextLine();
        if (input.charAt(0) == 'n') return;
        while (nombre1 > 0 || nombre2 > 0)
        {
            System.out.print(Interface.RED);
            if (nombre1 > 0)
            {
                int hit = true_hit();
                if (hit >= precision1)
                    System.out.println("Echec ! " + unit.name + " attaque " + def.unit.name + ", qui esquive.");
                else if (hit < critique1)
                    System.out.println("Critique ! " + unit.name + " attaque " + def.unit.name + " et inflige " + degats1 * 3 + ".");
                else
                    System.out.println(unit.name + " attaque " + def.unit.name + " et inflige " + degats1 + ".");
                nombre1--;
            }
            if (nombre2 > 0)
            {
                int hit = true_hit();
                if (hit >= precision2)
                    System.out.println("Echec ! " + def.unit.name + " attaque " + unit.name + ", qui esquive.\n");
                else if (hit < critique2)
                    System.out.println("Critique ! " + def.unit.name + " attaque " + unit.name + " et inflige " + degats2 * 3 + ".");
                else
                    System.out.println(def.unit.name + " attaque " + unit.name + " et inflige " + degats2 + ".");
                nombre2--;
            }
        }
        System.out.print(Interface.RESET);
    }

    private int true_hit() {
        int a = Interface.r.nextInt(100);
        int b = Interface.r.nextInt(100);
        return (a + b) / 2;
    }

    public void soin(Perso def) {
        System.out.println(Interface.YELLOW + "TODO" + Interface.RESET);
    }

    public void tactique(Perso def) {
        if (prepare(def, true))
            return;

        int attaque = squad.damage + 4 + unit.charism / 5;
        if (squad.is_magic != 0)
            attaque += unit.strengh - def.unit.def;
        else
            attaque += unit.magic - def.unit.res;

        int precision = squad.precision + 5 * (unit.charism - def.unit.charism) + 10;

        System.out.print(Interface.YELLOW);
        System.out.println();
        System.out.println(unit.name + " - " + squad.name + " :");
        System.out.println("\tPRC = " + precision);
        System.out.println("\tDMG = " + attaque);

        System.out.print(Interface.GREEN);
        System.out.println("Lancer la simulation ?");
        System.out.print("oui/non > " + Interface.RESET);

        String input = Interface.s.nextLine();
        if (input.charAt(0) == 'n') return;
        System.out.print(Interface.RED);

        if (true_hit() >= precision)
            System.out.println("Echec ! " + unit.name + " utilise " + squad.attack_name + " sur " + def.unit.name + ", qui esquive.");
        else
            System.out.println(unit.name + " utilise " + squad.attack_name + " sur " + def.unit.name + " et inflige " + attaque + ".");

        System.out.print(Interface.RESET);
    }
}
