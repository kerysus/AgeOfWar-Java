package hra.userInput;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Pomocná trieda ktorá implementuje všetky metódy od MouseListener.
 * Úpravy metód robím v triede @Game.
 */
public class MouseInput implements MouseListener {
    /**
     * Metóda ktorá sa spustí keď sa stlačí a uvoľní tlačidlo na myši.
     * @param e the event to be processed
     */
    @Override
    public void mouseClicked(MouseEvent e) { }

    /**
     * Metóda ktorá sa spustí keď sa stlačí tlačidlo na myši.
     * @param e the event to be processed
     */
    @Override
    public void mousePressed(MouseEvent e) { }

    /**
     * Metóda ktorá sa spustí keď sa uvoľní tlačidlo na myši.
     * @param e the event to be processed
     */
    @Override
    public void mouseReleased(MouseEvent e) { }

    /**
     * Metóda ktorá sa spustí keď sa kurzor vojde na pozíciu objektu.
     * @param e the event to be processed
     */
    @Override
    public void mouseEntered(MouseEvent e) { }

    /**
     * Metóda ktorá sa spustí keď kurzor odíde z pozície objektu.
     * @param e the event to be processed
     */
    @Override
    public void mouseExited(MouseEvent e) { }
}
