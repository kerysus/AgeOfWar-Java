package hra;

import hra.userInput.KeyboardInput;
import hra.userInput.MouseInput;
import postavy.Heavy;
import postavy.Rogue;
import postavy.Character;
import postavy.Recruit;
import tlacidla.Button;
import tlacidla.SpawnRekrut;
import tlacidla.SpawnRogue;
import tlacidla.SpawnHeavy;
import tlacidla.Defend;
import tlacidla.Attack;
import tlacidla.MoneyShower;
import tlacidla.LineSelect;
import javax.swing.JOptionPane;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * Jadro celej hry.
 * Vykresluje celú hru na obrazovku.
 */
public class Game {
    private int money;
    private int line;
    private int camera;
    private int enemiesCount;
    private double spawnTolerance;
    private boolean pressedLine;
    private Background background;
    private ArrayList<Character> characters;
    private Statue[] statues = new Statue[2];
    private Button[] buttons = new Button[9];

    /**
     * Konštruktor triedy.
     * Nastaví základné hodnoty atribútom.
     * Vykreslí pozadie hry a spustí hru.
     */
    public Game() {
        this.money = 100;
        this.line = 1;
        this.camera = 0;
        this.spawnTolerance = 0;
        this.enemiesCount = 0;
        this.pressedLine = false;

        this.background = new Background();
        this.characters = new ArrayList<Character>();

        this.statues[0] = new Statue(true);
        this.statues[1] = new Statue(false);

        this.buttons[0] = new SpawnRekrut();
        this.buttons[1] = new SpawnRogue();
        this.buttons[2] = new SpawnHeavy();
        this.buttons[3] = new Defend();
        this.buttons[4] = new Attack();
        this.buttons[5] = new MoneyShower();
        this.buttons[6] = new LineSelect(810, 850, "digit1");
        this.buttons[7] = new LineSelect(960, 850, "digit2");
        this.buttons[8] = new LineSelect(1110, 850, "digit3");

        this.start();
    }

    /**
     * Spustí hlavný cyklus hry.
     * Obsahuje KeyListener a MouseListener.
     * Vykresľuje postavy, sochy, pozadie, tlačidlá a iné vecy na obrazovku.
     */
    public void start() {
        // lokálne premenné pre hru
        double now;
        double lastTime;
        double lastMoneyAdd = System.currentTimeMillis();
        boolean run = true;
        Random rn = new Random();

        // metódy na nastavenie hry pred spustením hlavného cyklu
        this.background.zobraz();
        this.buttons[5].setMoney(this.money);
        this.buttons[6].setSelected(true);
        this.buttons[6].execute();

        for (Statue s : this.statues) {
            s.update(this.camera);
        }

        // KEYLISTENER - zaznamenáva input na klávesnici
        // Využíva triedu Keyboard input
        // Posúva pozadie - opravuje aj pozíciu iných objektov na obrazovke
        tvary.Platno.dajPlatno().addKeyListener(new KeyboardInput() {
            @Override
            public void keyPressed(KeyEvent e) {
                // posunie pozadie vľavo a aktualizuje polohu postáv
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    if (Game.this.background.posunVlavo(+40)) {
                        Game.this.camera += 40;
                    }
                    Game.this.statues[0].update(Game.this.camera);
                    Game.this.statues[1].update(Game.this.camera);
                }
                // posunie pozadie vpravo a aktualizuje polohu postáv
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    if (Game.this.background.posunVpravo(-40)) {
                        Game.this.camera -= 40;
                    }
                    Game.this.statues[0].update(Game.this.camera);
                    Game.this.statues[1].update(Game.this.camera);
                }
                // reaguje na stlačenie iných tlačidieľ
                switch (e.getKeyCode()) {
                    // stlačenie "1"
                    case 49 -> {
                        Game.this.pressedLine = true;
                        Game.this.line = 1;
                        Game.this.buttons[6].setSelected(true);
                        Game.this.buttons[7].setSelected(false);
                        Game.this.buttons[8].setSelected(false);
                    }
                    // stlačenie "2"
                    case 50 -> {
                        Game.this.pressedLine = true;
                        Game.this.line = 2;
                        Game.this.buttons[6].setSelected(false);
                        Game.this.buttons[7].setSelected(true);
                        Game.this.buttons[8].setSelected(false);
                    }
                    // stlačenie "3"
                    case 51 -> {
                        Game.this.pressedLine = true;
                        Game.this.line = 3;
                        Game.this.buttons[6].setSelected(false);
                        Game.this.buttons[7].setSelected(false);
                        Game.this.buttons[8].setSelected(true);
                    }
                }
                // vykreslenie správneho obrázku na tlačidle
                if (Game.this.pressedLine) {
                    Game.this.buttons[6].execute();
                    Game.this.buttons[7].execute();
                    Game.this.buttons[8].execute();
                    Game.this.pressedLine = false;
                }
            }
        });

        // MouseListener - slúži na reagovanie tlačidiel po kliknutí kurzorom na ne
        // Využíva triedu MouseInput
        tvary.Platno.dajPlatno().addMouseListener(new MouseInput() {
            // Reaguje na stlačenie ľavého tlačidla myši
            @Override
            public void mousePressed(MouseEvent e) {
                // kontroluje na X-ovú a Y-ovú hodnotu
                if (e.getY() >= 730 && e.getY() <= 1000) {
                    if (e.getX() >= 5 && e.getX() <= 265) { //REKRUT
                        Game.this.buttons[0].showClicked();
                    } else if (e.getX() >= 270 && e.getX() <= 530) { //ROGUE
                        Game.this.buttons[1].showClicked();
                    } else if (e.getX() >= 535 && e.getX() <= 795) { //HEAVY
                        Game.this.buttons[2].showClicked();
                    }
                    if (e.getY() <= 815) {
                        if (e.getX() >= 800 && e.getX() <= 1050) { //ATTACK
                            Game.this.buttons[3].showClicked();
                        } else if (e.getX() >= 1060 && e.getX() <= 1320) { //DEFEND
                            Game.this.buttons[4].showClicked();
                        }
                    }
                }
            }

            // Reaguje na uvoľnenie ľavého tlačidla
            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getY() >= 730 && e.getY() <= 1000) {
                    // vytvorí nového Rekruta
                    if (e.getX() >= 5 && e.getX() <= 265) {
                        Game.this.buttons[0].showNormal();
                        Game.this.buttons[0].setMoney(Game.this.money);
                        if (Game.this.buttons[0].execute()) {
                            Game.this.money -= Game.this.buttons[0].getPrize();
                            Game.this.characters.add(new Recruit(true, Game.this.line, 1));
                        }
                    } else if (e.getX() >= 270 && e.getX() <= 530) { // vytvorí nového Rogue
                        Game.this.buttons[1].showNormal();
                        Game.this.buttons[1].setMoney(Game.this.money);
                        if (Game.this.buttons[1].execute()) {
                            Game.this.money -= Game.this.buttons[1].getPrize();
                            Game.this.characters.add(new Rogue(true, Game.this.line, 1));
                        }
                    } else if (e.getX() >= 535 && e.getX() <= 795) { // vytvorí nového Heavy
                        Game.this.buttons[2].showHower();
                        Game.this.buttons[2].setMoney(Game.this.money);
                        if (Game.this.buttons[2].execute()) {
                            Game.this.money -= Game.this.buttons[2].getPrize();
                            Game.this.characters.add(new Heavy(true, Game.this.line, 1));
                        }
                    }

                    if (e.getY() <= 815) {
                        // kontrola na príkaz útoku
                        if (e.getX() >= 800 && e.getX() <= 1050) {
                            Game.this.buttons[3].showHower();
                            Game.this.buttons[3].setMoney(Game.this.money);
                            if (Game.this.buttons[3].execute()) {
                                Game.this.money -= Game.this.buttons[3].getPrize();
                                Game.this.defend();
                            }
                        } else if (e.getX() >= 1060 && e.getX() <= 1320) { // kontrola na príkaz obrany
                            Game.this.buttons[4].showHower();
                            Game.this.buttons[4].setMoney(Game.this.money);
                            if (Game.this.buttons[4].execute()) {
                                Game.this.money -= Game.this.buttons[4].getPrize();
                                Game.this.attack();
                            }
                        }
                    }

                    // reaguje na výber zmeny začínajúce miesta postáv
                    if (e.getY() >= 850) {
                        if (e.getX() >= 810 && e.getX() < 960) { //LINE 1
                            Game.this.line = 1;
                            Game.this.buttons[6].setSelected(true);
                            Game.this.buttons[7].setSelected(false);
                            Game.this.buttons[8].setSelected(false);
                        } else if (e.getX() >= 960 && e.getX() < 1100) { //LINE 2
                            Game.this.line = 2;
                            Game.this.buttons[6].setSelected(false);
                            Game.this.buttons[7].setSelected(true);
                            Game.this.buttons[8].setSelected(false);
                        } else if (e.getX() >= 1100 && e.getX() < 1250) { //LINE 3
                            Game.this.line = 3;
                            Game.this.buttons[6].setSelected(false);
                            Game.this.buttons[7].setSelected(false);
                            Game.this.buttons[8].setSelected(true);
                        }
                        Game.this.buttons[6].execute();
                        Game.this.buttons[7].execute();
                        Game.this.buttons[8].execute();
                    }
                }
                Game.this.buttons[5].setMoney(Game.this.money);
            } } );

        // Hlavný cyklus hry
        while (run) {
            // pridá 100 peňazí každých 5 sekúnd
            now = System.currentTimeMillis();
            if (lastMoneyAdd + 5000 < now) {
                this.money += 100;
                Game.this.buttons[5].setMoney(Game.this.money);
                lastMoneyAdd = System.currentTimeMillis();
            }

            // vytvára náhodných nepriateľov na náhodnej Y-ovej pozícii každých 10 až 20 sekúnd
            if (this.spawnTolerance < now && this.enemiesCount < 3) {
                lastTime = System.currentTimeMillis();
                switch (rn.nextInt(1, 4)) {
                    case 1:
                        this.characters.add(new Recruit(false, rn.nextInt(1, 3), 1));
                        break;
                    case 2:
                        this.characters.add(new Rogue(false, rn.nextInt(1, 3), 1));
                        break;
                    case 3:
                        this.characters.add(new Heavy(false, rn.nextInt(1, 3), 1));
                        break;
                }
                this.enemiesCount++;
                this.spawnTolerance = lastTime + rn.nextInt(15000, 30000);
            }

            try {
                // aktualizuje postavy
                // volá metódy na posun a obsahuje garbage collector na postavy
                Iterator<Character> itr = this.characters.iterator();
                while (itr.hasNext()) {
                    Character p = itr.next();
                    if (p.update()) {
                        if (p.isPlayerSide()) {
                            if (this.statues[1].loseHp(p.getDamage())) {
                                JOptionPane.showMessageDialog(null, "VYHRAL SI!");
                                run = false;
                            }
                        } else {
                            if (this.statues[0].loseHp(p.getDamage())) {
                                JOptionPane.showMessageDialog(null, "PREHRAL SI!");
                                run = false;
                            }
                        }
                    }
                    p.draw(this.camera);
                    if (p.isDead()) {
                        if (!p.isPlayerSide()) {
                            this.enemiesCount--;
                        }
                        p.hide();
                        itr.remove();
                        Game.this.characters.remove(p);
                    }
                }

                // detekuje kolíziu dvoch postáv -> uberá HP postavám a zastaví ich
                for (Character p0 : this.characters) {
                    boolean noEnemy = true;
                    for (Character p1 : this.characters) {
                        if (p0 == p1) {
                            continue;
                        }
                        int p0X1 = p0.getX();
                        int p0X2 = p0X1 + p0.getImgWidth();
                        int p1X1 = p1.getX();
                        int p1X2 = p1X1 + p1.getImgWidth();

                        if (p0.getLine() == p1.getLine()) {
                            if ((p1X2 >= p0X1 && p0X1 >= p1X1) || (p1X2 >= p0X2 && p0X2 >= p1X1)) {
                                noEnemy = false;
                                if (System.currentTimeMillis() - p1.getAttackDelay() >= 100) {
                                    p1.takeDamage(p0);
                                    p1.setAttackDelay(System.currentTimeMillis());
                                }
                            }
                        }
                    }
                    if (p0.getState() == 4 && noEnemy) {
                        p0.setState(1);
                    }
                }
                Game.this.buttons[5].execute();

            } catch (java.util.ConcurrentModificationException ignored) {
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Pridelí príkaz postavám aby sa zahájili útok.
     */
    public void attack() {
        for (Character p : this.characters) {
            if (p.isPlayerSide()) {
                p.changeState("attack");
            }
        }
    }

    /**
     * Prídelí príkaz postavám sa stiahnuť a brániť.
     */
    public void defend() {
        for (Character p : this.characters) {
            if (p.isPlayerSide()) {
                p.changeState("defend");
            }
        }
    }

}


