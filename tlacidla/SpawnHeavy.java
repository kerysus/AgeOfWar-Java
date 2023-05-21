package tlacidla;

import javax.swing.JOptionPane;

/**
 * Zobrazí tlačidlo na kúpu postavy Heavy.
 */
public class SpawnHeavy extends Button {
    private final int cena = 700;

    /**
     * Konštruktor triedy, nastaví polohu tlačidla a názov.
     */
    public SpawnHeavy() {
        super(535, 730, "heavy");
    }

    /**
     * Vráti hodnotu true, ak má hráč dostatok peňazí na kúpu postavy.
     * @return - vráti true, alebo false
     */
    @Override
    public boolean execute() {
        if (super.getMoney() < this.cena) {
            JOptionPane.showMessageDialog(null, "Nemáš dostatok peňazí, potrebuješ aspon 700.");
            return false;
        } else {
            return true;
        }
    }

    /**
     * Getter na cenu.
     * @return - cena typu int
     */
    public int getPrize() {
        return this.cena;
    }
}
