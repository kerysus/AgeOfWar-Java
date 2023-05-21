package tlacidla;

import tvary.Platno;

/**
 * Zobrazuje aktuálny počet peňazí (zlata).
 * Na zobrazenie peňazí som do plátna pridal metódu drawString.
 */
public class MoneyShower extends Button {

    /**
     * Konštruktor triedy, nastaví polohu tlačidla a názov.
     */
    public MoneyShower() {
        super(1210, 730, "gold" );
    }

    /**
     * Zobrazí množstvo peňazí na obrazovke.
     * @return true
     */
    @Override
    public boolean execute() {
        Platno.dajPlatno().drawString(Integer.toString(super.getMoney()),  1300, 780, "black" , 20);
        return true;
    }

    /**
     * Getter na cenu.
     */
    @Override
    public int getPrize() {
        return 0;
    }
}
