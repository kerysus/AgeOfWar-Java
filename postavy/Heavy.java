package postavy;

import java.util.Random;

/**
 * Predstavuje najsilnejšiu postavu v hre.
 * Má najviac hp a ako unikátnu vlastnosť má blokovanie útokov v závislosti od typu nepriateľa.
 */
public class Heavy extends Character {
    private int hp;
    private int maxHp;
    private int[] damage;
    private boolean dead;
    private Random rn;

    /**
     * Konštruktor triedy ktorý nastaví hodnoty atribútom.
     * @param isPlayerSide - indikuje či je postava hráčova alebo postava protihráča.
     * @param line - indikuje na ktorú Y-ovú súradnicu sa má postava zobraziť.
     * @param state - indikuje čo má postava robiť. State je popísaný v triede @Postava.
     */
    public Heavy(boolean isPlayerSide, int line, int state) {
        super(isPlayerSide, line, "heavy", 7,  5, state);
        this.hp = 150;
        this.maxHp = 150;
        this.damage = new int[2];
        this.damage[0] = 6;
        this.damage[1] = 15;
        this.dead = false;
        this.rn = new Random();
    }

    /**
     * Reaguje na útok. Ak HP klesne pod 0, postava sa vymaže.
     * Pre každý druh postavy blokuje útok s inou pravdepodobnosťou.
     * @param p - predstavuje postavu podľa ktorej blokuje útok a uberá hp.
     */
    @Override
    public void takeDamage(Character p) {
        // ak je NEPRIATEl -> uber HP
        if (p.isPlayerSide() != this.isPlayerSide()) {
            //kontrola HP
            if (this.hp <= 0) {
                this.dead = true;
            }

            if (p instanceof Recruit) {
                if (this.rn.nextInt(1, 101) <= 40) { // 40% probability of blocked attack
                    this.hp -= this.rn.nextInt(p.getDamage()[0], p.getDamage()[1] + 1);
                    this.getHpBar().uberHp(this.hp, this.maxHp);
                    this.setState(4);
                }
            } else if (p instanceof  Rogue) {
                if (this.rn.nextInt(1, 101) <= 30) {  // 30% probability of parried attack
                    this.hp -= this.rn.nextInt(p.getDamage()[0], p.getDamage()[1] + 1);
                    this.getHpBar().uberHp(this.hp, this.maxHp);
                }
            } else if (p instanceof  Heavy) {
                if (this.rn.nextInt(1, 101) <= 20) { // 20% probability of blocked attack
                    this.hp -= this.rn.nextInt(p.getDamage()[0], p.getDamage()[1] + 1);
                    this.getHpBar().uberHp(this.hp, this.maxHp);
                }
            }
            this.setState(4);
        }
    }

    /**
     * Getter na minimálne a maximálne množstvo poškodenia.
     * @return - array na množstvo poškodenia.
     */
    @Override
    public int[] getDamage() {
        return this.damage;
    }

    /**
     * Vráti true ak postava nemá žiadne životy.
     * @return
     */
    @Override
    public boolean isDead() {
        return this.dead;
    }
}
