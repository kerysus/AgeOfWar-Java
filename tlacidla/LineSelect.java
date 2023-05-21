package tlacidla;

/**
 * Potomok tiredy Button. Slúži na miesta zobrazovania postáv.
 */
public class LineSelect extends Button {
    /**
     * Nastaví polohu tlačidla a meno.
     * @param x - súradnica na x-ovej osi.
     * @param y - súradnica na y-ovej osi.
     * @param name - meno tlačidla, ktoré slúži na načítanie obrázku v adresári.
     */

    public LineSelect(int x, int y, String name) {
        super(x, y, name);
    }

    /**
     * Ak tlačidlo nie je zvolené, zobrazí sa normálne verzia obrázku.
     * Ak je tlačidlo zvolené, zobrazí sa clicked verzia obrázku.
     * @return - vráti true ak je tlačidlo zvolené, false ak nie je.
     */
    @Override
    public boolean execute() {
        if (super.isSelected()) {
//            Platno.dajPlatno().drawString("ZVOLENÉ", this.x, this.y-20, "black", 20);
            super.nastavObrazokNormal(0, 2);
            return true;
        } else {
            super.nastavObrazokNormal(0, 1);
            return false;
        }
    }

    /**
     * Getter na cenu tlačidla.
     * @return - vráti cenu tlačidla.
     */
    @Override
    public int getPrize() {
        return 0;
    }
}
