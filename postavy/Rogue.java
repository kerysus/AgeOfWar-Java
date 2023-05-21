package postavy;

import java.util.Random;

/**
 * Potomok triedy Postava, ktorá má unikátne vlastnosti.
 */
public class Rogue extends Character {
    private int hp;
    private int maxHp;
    private int[] damage = new int[2];
    private boolean dead;
    private boolean lineSwitched;
    private Random rn;

    /**
     * Konštruktor triedy. Nastaví unikátne hodnoty atribútom patriace len pre túto postavu.
     * @param isPlayerSide - indikuje či je postava hráčova ale postava protihráča.
     * @param line - indikuje y-ovú pozíciu postavy.
     * @param state - štádium postavy.
     */
    public Rogue(boolean isPlayerSide, int line, int state) {
        super(isPlayerSide, line, "rogue", 7,  9, state);
        this.hp = 230;
        this.maxHp = 230;
        this.damage[0] = 1;
        this.damage[1] = 15;
        this.dead = false;
        this.rn = new Random();
        this.lineSwitched = false;
    }

    /**
     * Metóda reaguje na útok a ubere hp o poškodenie postavy.
     * Postava Rogue obsahuje unikátnu schopnosť zmeniť újsť nebezpečiu keď klesne život pod 25 hp. Postava zmení y-ovú pozíciu.
     * Postava utrpí menšie poškodenie ak ide proti postave typu Heavy.
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
                this.hp -= this.rn.nextInt(1, p.getDamage()[1] + 1);
                this.getHpBar().uberHp(this.hp, this.maxHp);
            }
            //unikátne len pre túto postavu -- obsahuje metodu changeLine()
            if (!this.lineSwitched && this.hp < 25) {
                this.changeLine();
            }
        }
    }

    /**
     * Akonáhle život klesne pod 25 hp, postava zmení y-ovú polohu a zmení atribút line.
     */
    public void changeLine() {
        this.lineSwitched = true;
        switch (this.getLine()) {
            case 1 -> super.setLine(this.rn.nextInt(2, 3));
            case 2 -> super.setLine(1);
            case 3 -> super.setLine(this.rn.nextInt(1, 2));
        }
    }

    /**
     * Getter na množstvo poškodenia, ktoré táto postava spôsobuje.
     * @return - int[] množstvo poškodenia.
     */
    @Override
    public int[] getDamage() {
        return this.damage;
    }

    /**
     * Getter na hodnotu boolean, ktorá udáva či má táto postava menej ako 1 hp.
     * @return - boolean hodnota true, alebo false.
     */
    @Override
    public boolean isDead() {
        return this.dead;
    }
}
