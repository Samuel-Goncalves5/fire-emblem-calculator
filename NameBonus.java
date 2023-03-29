public class NameBonus {
    public Bonus linear = new Bonus(0);
    public Bonus percent = new Bonus(100);
    String name;

    public NameBonus(String[] split) {
        name = split[0];
        for (int i = 1; i < split.length; i++) {
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
}
