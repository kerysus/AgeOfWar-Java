package hra.userInput;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Pomocná trieda ktorá implementuje všetky metódy od KeyLister.
 * Úpravy metód robím v triede @Game.
 */
public class KeyboardInput implements KeyListener {

    /**
     * Metóda ktorá sa spustí keď sa stlačí a uvoľní tlačidlo na klávesnici.
     * @param e the event to be processed
     */
    @Override
    public void keyTyped(KeyEvent e) { }

    /**
     * Metóda ktorá sa spustí keď sa stlačí tlačidlo na klávesnici.
     * @param e the event to be processed
     */
    @Override
    public void keyPressed(KeyEvent e) { }

    /**
     * Metóda ktorá sa spustí keď sa uvoľní tlačidlo na klávesnici.
     * @param e the event to be processed
     */

    @Override
    public void keyReleased(KeyEvent e) { }
}
