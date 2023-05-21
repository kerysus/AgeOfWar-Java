package postavy;

import java.util.Random;

/**
 * Potomok triedy Postava.
 */

public class Recruit extends Character {
    private int hp;
    private int maxHp;
    private int[] damage = new int[2];
    private boolean dead;
    private Random rn;

    /**
     * Konštruktor triedy. Nastaví unikátne hodnoty atribútom patriace len pre túto postavu.
     * @param isPlayerSide - indikuje či je postava hráčova ale postava protihráča.
     * @param line - indikuje y-ovú pozíciu postavy.
     * @param state - štádium postavy.
     */
    public Recruit(boolean isPlayerSide, int line, int state) {
        super(isPlayerSide, line, "rekrut", 7,  7, state);
        this.hp = 200;
        this.maxHp = 200;
        this.damage[0] = 3;
        this.damage[1] = 6;
        this.dead = false;
        this.rn = new Random();
    }

    /**
     * Metóda reaguje na útok a ubere hp o poškodenie postavy.
     * Rekrut reaguje na všetky postavy rovnako a nemá žiadne unikátne schopnosti.
     * @param p - predstavuje postavu.
     */
    @Override
    public void takeDamage(Character p) {
        if (p.isPlayerSide() != this.isPlayerSide()) {
            if (this.hp <= 0) {
                this.dead = true;
            }
            this.setState(4);

            if (p instanceof Recruit) {
                this.hp -= this.rn.nextInt(p.getDamage()[0], p.getDamage()[1] + 1);
                this.getHpBar().uberHp(this.hp, this.maxHp);

            } else if (p instanceof Rogue) {
                this.hp -= this.rn.nextInt(p.getDamage()[0], p.getDamage()[1] + 1);
                this.getHpBar().uberHp(this.hp, this.maxHp);

            } else if (p instanceof Heavy) {
                this.hp -= this.rn.nextInt(p.getDamage()[0], p.getDamage()[1] + 1);
                this.getHpBar().uberHp(this.hp, this.maxHp);
            }
        }
    }

    /**
     * Getter na množstvo poškodenia, ktoré táto postava spôsobuje.
     * @return
     */
    @Override
    public int[] getDamage() {
        return this.damage;
    }

    /**
     * Getter na hodnotu boolean, ktorá udáva či má táto postava menej ako 1 hp.
     * @return
     */
    @Override
    public boolean isDead() {
        return this.dead;
    }
}
