package fr.epita.assistants.calculator;

public class Bonus {
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

    public int damage;
    public int crit;
    public int weight;
    public int precision;

    public int squad_damage;
    public int squad_precision;

    public int esquive;
    public int nombre;

    public Bonus(int n) {
        this.life = n;
        this.strengh = n;
        this.magic = n;
        this.tech = n;
        this.speed = n;
        this.luck = n;
        this.res = n;
        this.def = n;
        this.charism = n;
        this.mouv = n;
        this.damage = n;
        this.crit = n;
        this.weight = n;
        this.precision = n;
        this.squad_damage = n;
        this.squad_precision = n;
        this.esquive = n;
        this.nombre = n;
    }

    public Bonus(Bonus n) {
        this.life = n.life;
        this.strengh = n.strengh;
        this.magic = n.magic;
        this.tech = n.tech;
        this.speed = n.speed;
        this.luck = n.luck;
        this.res = n.res;
        this.def = n.def;
        this.charism = n.charism;
        this.mouv = n.mouv;
        this.damage = n.damage;
        this.crit = n.crit;
        this.weight = n.weight;
        this.precision = n.precision;
        this.squad_damage = n.squad_damage;
        this.squad_precision = n.squad_precision;
        this.esquive = n.esquive;
        this.nombre = n.nombre;
    }

    public static void apply(Bonus linear, Bonus percent, Unit u, Weapon w, Squad s) {
        if (u != null) {
            u.life *= percent.life;
            u.strengh *= percent.strengh;
            u.magic *= percent.magic;
            u.tech *= percent.tech;
            u.speed *= percent.speed;
            u.luck *= percent.luck;
            u.res *= percent.res;
            u.def *= percent.def;
            u.charism *= percent.charism;
            u.mouv *= percent.mouv;

            u.life /= 100;
            u.strengh /= 100;
            u.magic /= 100;
            u.tech /= 100;
            u.speed /= 100;
            u.luck /= 100;
            u.res /= 100;
            u.def /= 100;
            u.charism /= 100;
            u.mouv /= 100;

            u.life += linear.life;
            u.strengh += linear.strengh;
            u.magic += linear.magic;
            u.tech += linear.tech;
            u.speed += linear.speed;
            u.luck += linear.luck;
            u.res += linear.res;
            u.def += linear.def;
            u.charism += linear.charism;
            u.mouv += linear.mouv;
        }

        if (w != null) {
            w.damage *= percent.damage;
            w.weight *= percent.weight;
            w.crit *= percent.crit;
            w.precision *= percent.precision;

            w.damage /= 100;
            w.weight /= 100;
            w.crit /= 100;
            w.precision /= 100;

            w.damage += linear.damage;
            w.weight += linear.weight;
            w.crit += linear.crit;
            w.precision += linear.precision;
        }

        if (s != null) {
            s.damage *= percent.squad_damage;
            s.precision *= percent.squad_precision;

            s.damage /= 100;
            s.precision /= 100;

            s.damage += linear.squad_damage;
            s.precision += linear.squad_precision;
        }
    }

    public void add(String s, int n) {
        if ("VIE".equals(s)) this.life += n;
        if ("FOR".equals(s)) this.strengh += n;
        if ("MAG".equals(s)) this.magic += n;
        if ("TEC".equals(s)) this.tech += n;
        if ("VIT".equals(s)) this.speed += n;
        if ("CHA".equals(s)) this.luck += n;
        if ("RES".equals(s)) this.res += n;
        if ("DEF".equals(s)) this.def += n;
        if ("CHR".equals(s)) this.charism += n;
        if ("MOU".equals(s)) this.mouv += n;
        if ("DEG".equals(s)) this.damage += n;
        if ("CRI".equals(s)) this.crit += n;
        if ("POI".equals(s)) this.weight += n;
        if ("PRE".equals(s)) this.precision += n;
        if ("T_D".equals(s)) this.squad_damage += n;
        if ("T_P".equals(s)) this.squad_precision += n;
        if ("ESQ".equals(s)) this.esquive += n;
        if ("NBA".equals(s)) this.nombre += n;
    }

    public void mult(String s, int n) {
        if ("VIE".equals(s)) { this.life *= n; life /= 100; }
        if ("FOR".equals(s)) { this.strengh *= n; strengh /= 100; }
        if ("MAG".equals(s)) { this.magic *= n; magic /= 100; }
        if ("TEC".equals(s)) { this.tech *= n; tech /= 100; }
        if ("VIT".equals(s)) { this.speed *= n; speed /= 100; }
        if ("CHA".equals(s)) { this.luck *= n; luck /= 100; }
        if ("RES".equals(s)) { this.res *= n; res /= 100; }
        if ("DEF".equals(s)) { this.def *= n; def /= 100; }
        if ("CHR".equals(s)) { this.charism *= n; charism /= 100; }
        if ("MOU".equals(s)) { this.mouv *= n; mouv /= 100; }
        if ("DEG".equals(s)) { this.damage *= n; damage /= 100; }
        if ("CRI".equals(s)) { this.crit *= n; crit /= 100; }
        if ("POI".equals(s)) { this.weight *= n; weight /= 100; }
        if ("PRE".equals(s)) { this.precision *= n; precision /= 100; }
        if ("T_D".equals(s)) { this.squad_damage *= n; squad_damage /= 100; }
        if ("T_P".equals(s)) { this.squad_precision *= n; squad_precision /= 100; }
        if ("ESQ".equals(s)) { this.esquive *= n; esquive /= 100; }
        if ("NBA".equals(s)) { this.nombre *= n; nombre /= 100; }
    }

    public void mult(Bonus n) {
        this.life *= n.life; life /= 100;
        this.strengh *= n.strengh; strengh /= 100;
        this.magic *= n.magic; magic /= 100;
        this.tech *= n.tech; tech /= 100;
        this.speed *= n.speed; speed /= 100;
        this.luck *= n.luck; luck /= 100;
        this.res *= n.res; res /= 100;
        this.def *= n.def; def /= 100;
        this.charism *= n.charism; charism /= 100;
        this.mouv *= n.mouv; mouv /= 100;
        this.damage *= n.damage; damage /= 100;
        this.crit *= n.crit; crit /= 100;
        this.weight *= n.weight; weight /= 100;
        this.precision *= n.precision; precision /= 100;
        this.squad_damage *= n.squad_damage; squad_damage /= 100;
        this.squad_precision *= n.squad_precision; squad_precision /= 100;
        this.esquive *= n.esquive; esquive /= 100;
        this.nombre *= n.nombre; nombre /= 100;
    }

    public void add(Bonus n) {
        this.life += n.life;
        this.strengh += n.strengh;
        this.magic += n.magic;
        this.tech += n.tech;
        this.speed += n.speed;
        this.luck += n.luck;
        this.res += n.res;
        this.def += n.def;
        this.charism += n.charism;
        this.mouv += n.mouv;
        this.damage += n.damage;
        this.crit += n.crit;
        this.weight += n.weight;
        this.precision += n.precision;
        this.squad_damage += n.squad_damage;
        this.squad_precision += n.squad_precision;
        this.esquive += n.esquive;
        this.nombre += n.nombre;
    }
}
