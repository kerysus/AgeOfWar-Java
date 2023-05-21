package postavy;

import tvary.Obdlznik;

/**
 * Vykreslí zobrazovač života nad postavou zelenou, alebo červenou farbou.
 */
public class HpBar {
    private Obdlznik vonkajsi;
    private Obdlznik vnutorny;

    /**
     * Konštruktor triedy.
     * Nastavuje farbu a rozmery zobrazovača života.
     * @param jeNepriatel - indikuje či je postava hráč alebo protihráč. Podľa tohto sa vyberá farba.
     * @param x - X-ová súradnica na ktorú sa obdĺžnik zobrazí.
     * @param y - Y-ová súradnica na ktorú sa obdĺžnik zobrazí.
     */
    public HpBar(boolean jeNepriatel, int x, int y) {
        if (jeNepriatel) {
            this.vnutorny = new Obdlznik(x + 1, y + 1, 50, 4, "red");
        } else {
            this.vnutorny = new Obdlznik(x + 1, y + 1, 50, 4, "green");
        }
        this.vonkajsi = new Obdlznik(x, y, 52, 6, "black");
    }

    /**
     * Vypočíta novú dĺžku HpBaru od aktuálneho počtu HP.
     * @param hp
     * @param maxHp
     */
    public void uberHp(int hp, int maxHp) {
        int healthBarWidth = (int)((double)hp / maxHp * 50);
        this.vnutorny.zmenStranuA(healthBarWidth);
    }

    /**
     * Zmení absolútnu polohu HpBaru.
     * @param x - súradnica na x-ovej osi.
     * @param y - súradnica na y-ovej osi.
     */
    public void posunNa(int x, int y) {
        this.vonkajsi.posunNaXY(x, y);
        this.vnutorny.posunNaXY(x + 1, y + 1);
    }

    /**
     * Zobrazí HpBar.
     */
    public void zobraz() {
        this.vonkajsi.zobraz();
        this.vnutorny.zobraz();
    }

    /**
     * Skryje HpBar.
     */
    public void skry() {
        this.vonkajsi.skry();
        this.vnutorny.skry();
    }
}
