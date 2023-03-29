import java.util.Random;
import java.util.Scanner;

public class Interface {
    static final String
            RESET = "\u001B[0m", RED = "\u001B[31m", GREEN = "\u001B[32m", YELLOW = "\u001B[33m";
    static Scanner s = new Scanner(System.in);
    static Random r = new Random();

    public static void aide() {
        System.out.println(RED + "quitter" + RESET + " : ferme l'application.");
        System.out.println(RED + "aide" + RESET + " : affiche ce message.");
        System.out.println(RED + "random" + RESET + " : affiche un nombre aleatoire entre 0 et n.");
        System.out.println(RED + "attaque" + RESET + " : affiche l'attaque de P1 sur P2.");
        System.out.println(RED + "tactique" + RESET + " : affiche l'attaque escouade de P1 sur P2.");
        System.out.println(RED + "soin" + RESET + " : affiche le soin de P1 sur P2.");
        System.out.println();
    }

    public static void random() {
        System.out.print(GREEN + "n = " + RESET);
        System.out.println(RED + r.nextInt(Integer.parseInt(s.nextLine())) + RESET);
    }

    private static void interaction(int state) {
        Perso att = new Perso(), def = new Perso();
        System.out.print(GREEN + "Attaquant = " + RESET);
        String input = s.nextLine();
        for (Unit u : Main.units)
            if (u.name.equals(input))
                att.unit = u;
        System.out.print(GREEN + "Defenseur = " + RESET);
        input = s.nextLine();
        for (Unit u : Main.units)
            if (u.name.equals(input))
                def.unit = u;
        System.out.print(GREEN + "Arme de l'Attaquant = " + RESET);
        input = s.nextLine();
        for (Weapon w : Main.weapons)
            if (w.name.equals(input))
                att.weapon = w;
        System.out.print(GREEN + "Arme du Defenseur = " + RESET);
        input = s.nextLine();
        for (Weapon w : Main.weapons)
            if (w.name.equals(input))
                def.weapon = w;
        System.out.println(GREEN + "Effet sur l'Attaquant = " + RESET);
        String[] split = s.nextLine().split(",");
        for (int i = 1; i < split.length; i++) {
            boolean named = false;
            for (NameBonus n : Main.bonus)
                if (n.name.equals(split[i]))
                {
                    att.percent.mult(n.percent);
                    att.linear.add(n.linear);
                    named = true;
                }
            if (named) continue;

            char option = split[i].charAt(3);
            int val = Integer.parseInt(split[i].substring(4));
            if (option == '-') val *= -1;
            if (option == '%') att.percent.mult(split[i].substring(0, 3), val);
            else att.linear.add(split[i].substring(0, 3), val);
        }
        System.out.println(GREEN + "Effet sur le Defenseur = " + RESET);
        split = s.nextLine().split(",");
        for (int i = 1; i < split.length; i++) {
            boolean named = false;
            for (NameBonus n : Main.bonus)
                if (n.name.equals(split[i]))
                {
                    def.percent.mult(n.percent);
                    def.linear.add(n.linear);
                    named = true;
                }
            if (named) continue;

            char option = split[i].charAt(3);
            int val = Integer.parseInt(split[i].substring(4));
            if (option == '-') val *= -1;
            if (option == '%') def.percent.mult(split[i].substring(0, 3), val);
            else def.linear.add(split[i].substring(0, 3), val);
        }

        att = new Perso(att);
        def = new Perso(def);

        if (state == 1) att.attaque(def);
        if (state == 2) att.soin(def);
        if (state == 3) att.tactique(def);
    }

    public static void main() {
        System.out.println(GREEN + "Entrez une commande :");
        System.out.print("> " + RESET);
        String cmd = s.nextLine();

        try {
            if ("quitter".equals(cmd)) return;
            if ("aide".equals(cmd)) aide();
            if ("random".equals(cmd)) random();
            if ("attaque".equals(cmd)) interaction(1);
            if ("soin".equals(cmd)) interaction(2);
            if ("tactique".equals(cmd)) interaction(3);
        } catch (Exception e) {
            System.out.println(RED + "Erreur" + RESET);
        }

        main();
    }
}
