package tlacidla;

import javax.swing.JOptionPane;

/**
 * Zobrazuje tlačidlo útoku na obrazovke. Aktivovanie príkazu stojí 10 zlata.
 */
public class Attack extends Button {
    private int cena = 10;

    /**
     * Konštruktor triedy, nastaví polohu tlačidla a názov.
     */
    public Attack() {
        super(1000, 730, "attack");
    }

    /**
     * Vykoná príkaz ak má hráč dostatok zlata, inak zobrazí správu.
     * @return Vráti true ak hráč má dostatok peňazí na vykonanie príkazu.
     */

    @Override
    public boolean execute() {
        if (super.getMoney() < this.cena) {
            JOptionPane.showMessageDialog(null, "Na zahájanie príkazu nemáš dostatok peňazí, potrebuješ aspoň 10.");
            return false;
        } else {
            return true;
        }
    }

    /**
     * Getter na cenu príkazu.
     * @return - cena príkazu.
     */
    public int getPrize() {
        return this.cena;
    }
}
