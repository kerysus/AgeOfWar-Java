package hra;

import tvary.Obdlznik;
import tvary.Obrazok;
import java.util.Random;

/**
 * Vytvorí sochu s určitým počtom hp.
 * Po jej zničení sa hra končí.
 */

public class Statue {
    private int hp;
    private int maxHp;
    private int positionX;
    private int positionY;
    private int outsideX;
    private int insideX;
    private int outsideY;
    private int insideY;
    private double now;
    private double lastHit;
    private final Obrazok obrazok;
    private final Obdlznik outside;
    private final Obdlznik inside;
    private final Random rn = new Random();

    /**
     * Konštruktor triedy.
     * Nastaví súradnice v závislosti od parametra a vytvorí obrázok s ukazovateľom hp.
     * Obmedzuje ako rýchlo sa oberú hp.
     * @param playerSide - Indikuje či je socha na hráčova, alebo socha protivníka.
     */

    public Statue(boolean playerSide) {
        this.hp = 200;
        this.maxHp = 200;
        this.now = 0;
        this.lastHit = 0;

        if (playerSide) {
            this.positionX = 100;
            this.positionY = 230;

            this.outsideX = this.positionX + 50;
            this.insideX = this.positionX + 54;

            this.outsideY = this.positionY + 460;
            this.insideY = this.positionY + 464;

            this.obrazok = new Obrazok("resource/statues/player_statue.png");
            this.outside = new Obdlznik(this.positionX, this.positionY, 200, 23, "black");
            this.inside = new Obdlznik(this.positionX, this.positionY + 10, 190, 15, "green");
        } else {
            this.positionX = 3900;
            this.positionY = 230;

            this.outsideX = this.positionX + 150;
            this.insideX = this.positionX + 154;

            this.outsideY = this.positionY + 460;
            this.insideY = this.positionY + 464;

            this.obrazok = new Obrazok("resource/statues/enemy_statue.png");
            this.outside = new Obdlznik(this.positionX, this.positionY, 200, 23, "black");
            this.inside = new Obdlznik(this.positionX, this.positionY + 10, 190, 15, "red");
        }
    }

    /**
     * Ubere život od veľkosti poškodenia každé 2 sekundy.
     * @param damage - indikuje veľkosť poškodenia, ktoré má socha dostať od postavy.
     * @return - vráti boolean hodnotu typu true, ak životy klesli pod hodnotu 0.
     */
    public boolean loseHp(int[] damage) {
        this.now = System.currentTimeMillis();
        if (this.lastHit + 2000 < this.now) {
            this.hp -= this.rn.nextInt(damage[0], damage[1] + 1) * 3;
            int healthBarWidth = (int)((double)this.hp / this.maxHp * 190);
            this.inside.zmenStranuA(healthBarWidth);
            this.inside.zobraz();
            this.lastHit = System.currentTimeMillis();
        }
        return this.hp <= 0;
    }

    /**
     * Znova vykreslí obrázky a ukazovač života.
     * @param camera - slúži na vyčíslenie logickej pozície v hre a podľa toho vykreslí obrázok.
     */
    public void update(int camera) {
        this.obrazok.zmenPolohu(this.positionX + camera, this.positionY);
        this.outside.posunNaXY(this.outsideX + camera, this.outsideY);
        this.inside.posunNaXY(this.insideX + camera, this.insideY);

        this.obrazok.zobraz();
        this.outside.zobraz();
        this.inside.zobraz();
    }
}
