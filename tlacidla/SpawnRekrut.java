package tlacidla;

import javax.swing.JOptionPane;

/**
 * Zobrazí tlačidlo na kúpu postavy Rekrut.
 */
public class SpawnRekrut extends Button {
    private final int cena = 100;

    /**
     * Konštruktor triedy, nastaví polohu tlačidla a názov.
     */
    public SpawnRekrut() {
        super(5, 730, "rekrut" );
    }

    /**
     * Vráti hodnotu true, ak má hráč dostatok peňazí na kúpu postavy.
     * @return - vráti true, alebo false
     */
    @Override
    public boolean execute() {
        if (super.getMoney() < this.cena) {
            JOptionPane.showMessageDialog(null, "Nemáš dostatok peňazí, potrebuješ aspon 100.");
            return false;
        } else {
            return true;
        }
    }

    /**
     * Getter na cenu.
     * @return - vráti int hodnotu ceny
     */
    @Override
    public int getPrize() {
        return this.cena;
    }
}
