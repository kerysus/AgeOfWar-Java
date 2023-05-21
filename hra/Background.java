package hra;

import tvary.Obrazok;

/**
 * Vykreslí do pozadia obrázok.
 * Obsahuje metódy na pohyb obrázku vpravo a vľavo.
 */
public class Background {
    private Obrazok background;

    /**
     * Konštruktor triedy.
     * Vytvorí obrázok a zmení mu polohu.
     */
    public Background() {
        this.background = new Obrazok("resource/background.png");
        this.background.zmenPolohu(0, 0);
    }

    /**
     * Zväčší X-ovú súradnicu obrázka takže sa obrázok posunul vľavo.
     */
    public boolean posunVlavo(int kolko) {
        if (this.background.getPolohaX() < 0) {
            this.background.posunVodorovne(kolko);
            this.zobraz();
            return true;
        } else {
            this.zobraz();
            return false;
        }
    }

    /**
     * Zväčší X-ovú súradnicu obrázka takže sa obrázok posunul vpravo.
     */
    public boolean posunVpravo(int kolko) {
        if (this.background.getPolohaX() > -2957) { //4320x720    4320-1366 = 2954 + 5px border
            this.background.posunVodorovne(kolko);
            this.zobraz();
            return true;
        } else {
            this.zobraz();
            return false;
        }
    }

    /**
     * Znova zobrazí obrázok.
     */
    public void zobraz() {
        this.background.zobraz();
    }
}
