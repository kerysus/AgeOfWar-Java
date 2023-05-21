import hra.Game;

import javax.swing.JOptionPane;

/**
 * Hlavná trieda v ktorej sa spúšťa hra.
 */

// * Dôležitá informácia!
// * Pri tvorbe hry som využil obrázky, ktoré nevlastním a nemám k nim už prístup.

public class Main {
    /**
     * Metóda main, ktorá spustí hru.
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("Goodbye world");
        JOptionPane.showMessageDialog(null, "Vitaj v hre War of Age.");
        Game game = new Game();
    }
}
