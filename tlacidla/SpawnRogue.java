package tlacidla;

import javax.swing.JOptionPane;

/**
 * Zobrazí tlačidlo na kúpu postavy Rogue.
 */
public class SpawnRogue extends Button {
    private int cena = 400;

    /**
     * Konštruktor triedy, nastaví polohu tlačidla a názov.
     */
    public SpawnRogue() {
        super(270, 730, "rogue" );
    }

    /**
     * Vráti hodnotu true, ak má hráč dostatok peňazí na kúpu postavy.
     * @return - boolean hodnota true alebo false
     */
    @Override
    public boolean execute() {
        if (super.getMoney() < this.cena) {
            JOptionPane.showMessageDialog(null, "Nemáš dostatok peňazí, potrebuješ aspoň 400.");
            return false;
        } else {
            return true;
        }
    }

    /**
     * Getter na cenu.
     * @return - int hodnota ceny
     */
    @Override
    public int getPrize() {
        return this.cena;
    }
}
