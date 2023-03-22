package fr.epita.assistants.calculator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static List<Unit> units = new ArrayList<>();
    public static List<Weapon> weapons = new ArrayList<>();
    public static List<Squad> squads = new ArrayList<>();
    public static List<NameBonus> bonus = new ArrayList<>();

    public static void main(String[] args) {
        recupBonus();
        recupInfos();
        Interface.aide();
        Interface.main();
    }

    public static void recupBonus() {
        Path file = Paths.get("src/main/resources/modif.txt");
        List<String> bonuses = new ArrayList<>();
        try { bonuses = Files.readAllLines(file); }
        catch (IOException ignored) {
            System.out.println("Lecture de modif.txt : ERREUR.");
        }

        for (String b : bonuses)
            bonus.add(new NameBonus(b.split(",")));
    }

    public static void recupInfos() {
        Path file = Paths.get("src/main/resources/infos.txt");
        List<String> infos = new ArrayList<>();
        try { infos = Files.readAllLines(file); }
        catch (IOException ignored) {
            System.out.println("Lecture de infos.txt : ERREUR.");
        }

        int i = 0;
        for (String info : infos) {
            if (info.charAt(0) == '#') i++;
            else {
                switch (i) {
                    case 1 -> squads.add(new Squad(info.split(",")));
                    case 2 -> units.add(new Unit(info.split(",")));
                    case 3 -> weapons.add(new Weapon(info.split(",")));
                }
            }
        }
    }
}
