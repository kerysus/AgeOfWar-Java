package postavy;

import tvary.Obrazok;

/**
 * Abstraktná trieda, ktorá slúži na vytvorenie postavy.
 * Trieda obsahuje metódy na posun a animáciu postavy.
 */

public abstract class Character {
    private int x;
    private int y;
    private int line;
    private int velocity;
    private int imgCount;
    private int animationCount;
    private int state;
    private int home;
    private double now;
    private double lastTime;
    private double lastUpdate;
    private boolean isPlayerSide;
    private long attackDelay;
    private String name;
    private Obrazok img;
    private HpBar hpBar;

    /**
     * Konštruktor triedy. Nastaví základné hodnoty atribútom, zmení pozíciu, vytvorí obrázok a HpBar.
     *
     * @param isPlayerSide - indikuje či je postava hráčova, alebo postava protihráča.
     * @param line - určuje no ktorej y-ovej súradnici sa má postava zobraziť.
     * @param name - názov postavy, ktorý slúži na načitávanie obrázkov.
     * @param imgCount - počet obrázkov potrebný na animáciu postavy.
     * @param velocity - rýchlosť pohybu postavy.
     * @param state - indikuje čo má postava robiť. Hodnoty sú: //  0->IDLE, 1->ATTACK, 2->DEFEND, 3->HEAL, 4->STOP, 5 -> STATUE HIT
     */

    public Character(boolean isPlayerSide, int line, String name, int imgCount, int velocity, int state) {
        //PARAMETRE
        this.isPlayerSide = isPlayerSide;
        this.line = line;
        this.name = name;
        this.imgCount = imgCount;
        this.velocity = velocity;
        this.state = state;
        //ATRIBÚTY
        this.y = 0;
        this.animationCount = 0;
        this.now = 0;
        this.lastTime = 0;
        this.lastUpdate = 0;
        this.attackDelay = 0;

        // nastavenie počiatočných hodnôt
        if (line == 1) {
            this.y = 460;
        } else if (line == 2) {
            this.y = 550;
        } else if (line == 3) {
            this.y = 640;
        }

        if (isPlayerSide) {
            this.x = 0;
            this.home = 400;
            this.img = new Obrazok("resource/" + name + "/normal/run0.png");
            this.img.zmenPolohu(100, 600);
            this.hpBar = new HpBar(false, this.img.getPolohaX(), this.img.getPolohaY());
        } else {
            this.x = 3900;
            this.home = 3800;
            this.img = new Obrazok("resource/" + name + "/flipped/run0.png");
            this.img.zmenPolohu(1000, 600);
            this.hpBar = new HpBar(true, this.img.getPolohaX(), this.img.getPolohaY());
        }

    }

    /**
     * Posúva postavu od aktuálneho od parametra state.
     * Ak postava úťočí, pohybuje sa smerom k nepriateľskej soche.
     * Ak postava bráni, vráti sa späť k soche.
     *
     * @return - vráti true, ak postava prišla k soche nepriatela.
     */
    public boolean update() {
        this.now = System.currentTimeMillis();
        if ((this.lastUpdate + 50) > this.now) {
            return false;
        }
        if (this.state == 1) {
            if (this.isPlayerSide) {
                if (this.x < 3900) {
                    this.x += this.velocity;
                    this.lastUpdate = System.currentTimeMillis();
                } else {
                    return true;
                }
            } else {
                if (this.x > 400) {
                    this.x -= this.velocity;
                    this.lastUpdate = System.currentTimeMillis();
                } else {
                    return true;
                }
            }
        } else if (this.state == 2) {
            if (this.isPlayerSide) {
                if (this.x > this.home) {
                    this.x -= this.velocity;
                    this.lastUpdate = System.currentTimeMillis();
                } else {
                    this.state = 0;
                }
            }
        }
        return false;
    }

    /**
     * Metóda na zmenu štádia postavy.
     * @param newState - nové štádium postavy.
     */

    public void changeState(String newState) {
        String[] states = new String[] {"idle", "attack", "defend"};
        for (int i = 0; i < states.length; i++) {
            if (states[i].equals(newState)) {
                this.state = i;
            }
        }
    }

    /**
     * Animuje postavu a znova zobrazí HpBar na novej pozícii.
     * @param camera - predstavuje aktuálnu polohu kamery na x-ovej osi v hre.
     */

    public void draw(int camera) {
        // zmení ktorý obrázok momentálne vykresľuje
        if (this.animationCount >= this.imgCount) {
            this.animationCount = 0;
        }

        // zmení obrázok od toho či je postava hráčova, alebo postava protihráča.
        if  (this.isPlayerSide) {
            this.img.zmenObrazok("resource/" + this.name + "/normal/run" + this.animationCount + ".png");
        } else {
            this.img.zmenObrazok("resource/" + this.name + "/flipped/run" + this.animationCount + ".png");
        }

        // po uplinutí času sa obrázok zobrazí na x-ovú a y-ovú súradnicu a znova vykreslí HpBar.
        this.now = System.currentTimeMillis();
        if (this.lastTime + 80 < this.now) {
            this.img.zmenPolohu(this.x + camera, this.y);
            this.img.zobraz();
            this.hpBar.posunNa(this.x + camera, this.y);
            this.hpBar.zobraz();
            this.animationCount += 1;
            this.lastTime = System.currentTimeMillis();
        }
    }

    /**
     * Skryje obrázok a ukazovač života.
     */
    public void hide() {
        this.hpBar.skry();
        this.img.skry();
    }

    /**
     * Zmení line a y-ovú súradnicu postavy.
     *
     * @param line - indikuje y-ovú polohu postavy.
     */
    public void setLine(int line) {
        this.line = line;

        if (this.line == 1) {
            this.y = 460;
        } else if (this.line == 2) {
            this.y = 560;
        } else if (this.line == 3) {
            this.y = 640;
        }
    }

    /**
     * Getter na HpBar.
     *
     * @return - vráti HpBar.
     */
    public HpBar getHpBar() {
        return this.hpBar;
    }

    /**
     * Getter na zistenie strany postavy.
     * @return - vráti true ak je postava hráčova, inak false.
     */
    public boolean isPlayerSide() {
        return this.isPlayerSide;
    }

    /**
     * Vráti x-ovú polohu postavy.
     * @return
     */
    public int getX() {
        return this.x;
    }

    /**
     * Setter na y-ovú súradnicu postavy.
     * @param y - pozícia postavy na y-ovej osi.
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Getter na šírku obrázku.
     * @return - vráti int hodnotu širky.
     */
    public int getImgWidth() {
        return this.img.getObrazok().getWidth();
    }

    /**
     * Getter na atribúť line.
     * @return - vráti line, ktorý predstavuje polohu postavy na y-ovej osi.
     */
    public int getLine() {
        return this.line;
    }

    /**
     * Vráti štádium postavy.
     * @return - vráti štádium postavy.
     */
    public int getState() {
        return this.state;
    }

    /**
     * Vráti rýchlosť útoku.
     * @return - vráti double hodnotu časovej dĺžky útoku.
     */
    public long getAttackDelay() {
        return this.attackDelay;
    }

    /**
     * Vráti časovú dĺžku pauzy medzi útokmi.
     * @param time - trvanie času medzi útokmi.
     */
    public void setAttackDelay(long time) {
        this.attackDelay = time;
    }

    /**
     * Setter na nastavenia štádia postavy.
     * @param state - indikuje štádium postavy.
     */
    public void setState(int state) {
        this.state = state;
    }

    /**
     * Abstraktná metóda pre potomkov, ktorá uberá hp postavám. Každý potomok na toto reaguje inak.
     * @param p - predstavuje postavu.
     */
    public abstract void takeDamage(Character p);

    /**
     * Abstraktná metóda na zistenie poškodenia postavy.
     * @return - vráti množstvo poškodenia, ktoré postava môže spraviť.
     */
    public abstract int[] getDamage();

    /**
     * Abstraktná metóda, ktorá zisťuje či má postava menej ako 1 hp.
     * @return - hodnota typu boolean.
     */
    public abstract boolean isDead();
}
