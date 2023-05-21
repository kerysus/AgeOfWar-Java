package tvary;

import java.awt.Rectangle;

/**
 * Obdĺžnik, s ktorým možno pohybovať a nakreslí sa na plátno.
 *
 * @author  Michael Kolling and David J. Barnes
 * @version 1.0  (15 July 2000)
 */

public class Obdlznik {
    private int stranaA;
    private int stranaB;
    private int lavyHornyX;
    private int lavyHornyY;
    private String farba;

    /**
     * Vytvor nový obdĺžnik preddefinovanej farby na preddefinovanej pozícii.
     */
    public Obdlznik(int x, int y, int a, int b, String farba) {
        this.stranaA = a;
        this.stranaB = b;
        this.lavyHornyX = x;
        this.lavyHornyY = y;
        this.farba = farba;
    }

    public void posunNaXY(int x, int y) {
        this.lavyHornyX = x;
        this.lavyHornyY = y;
    }

    public void zmenStranuA(int kolko) {
        this.stranaA = kolko;
    }

    /**
     * (Obdĺžnik) Zobraz sa.
     */
    public void zobraz() {
        this.nakresli();
    }

    /**
     * (Obdĺžnik) Skry sa.
     */
    public void skry() {
        this.zmaz();
    }

    /**
     * (Obdĺžnik) Posuň sa vpravo o pevnú dĺžku.
     */
    public void posunVpravo() {
        this.posunVodorovne(20);
    }

    /**
     * (Obdĺžnik) Posuň sa vľavo o pevnú dĺžku.
     */
    public void posunVlavo() {
        this.posunVodorovne(-20);
    }

    /**
     * (Obdĺžnik) Posuň sa hore o pevnú dĺžku.
     */
    public void posunHore() {
        this.posunZvisle(-20);
    }

    /**
     * (Obdĺžnik) Posuň sa dole o pevnú dĺžku.
     */
    public void posunDole() {
        this.posunZvisle(20);
    }

    /**
     * (Obdĺžnik) Posuň sa vodorovne o dĺžku danú parametrom.
     */
    public void posunVodorovne(int vzdialenost) {
        this.lavyHornyX += vzdialenost;
    }

    /**
     * (Obdĺžnik) Posuň sa zvisle o dĺžku danú parametrom.
     */
    public void posunZvisle(int vzdialenost) {
        this.lavyHornyY += vzdialenost;
    }

    /**
     * (Obdĺžnik) Zmeň dĺžky strán na hodnoty dané parametrami.
     * Dĺžka strany musí byť nezáporné celé číslo.
     */
    public void zmenStrany(int stranaA, int stranaB) {
        this.stranaA = stranaA;
        this.stranaB = stranaB;
    }

    /**
     * (Obdĺžnik) Zmeň farbu na hodnotu danú parametrom.
     * Nazov farby musí byť po anglicky.
     * Možné farby sú tieto:
     * červená - "red"
     * žltá    - "yellow"
     * modrá   - "blue"
     * zelená  - "green"
     * fialová - "magenta"
     * čierna  - "black"
     * biela   - "white"
     * hnedá   - "brown"
     */
    public void zmenFarbu(String farba) {
        this.farba = farba;
    }

    /*
     * Draw the square with current specifications on screen.
     */
    private void nakresli() {
        Platno canvas = Platno.dajPlatno();
        canvas.draw(this, this.farba,
                new Rectangle(this.lavyHornyX, this.lavyHornyY, this.stranaA, this.stranaB));
    }

    /*
     * Erase the square on screen.
     */
    private void zmaz() {
        Platno canvas = Platno.dajPlatno();
        canvas.erase(this);
    }
}
