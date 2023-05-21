package tlacidla;

import tvary.Obrazok;

/**
 * Abstraktná trieda, ktorá slúži na vytvorenie tlačidla na ktoré sa dá kliknúť, alebo len zobrazí obrázok na zvolených súradniciach.
 */
public abstract class Button {

    private Obrazok[] imgs; //0 normal //1 hower //2 click
    private int money;
    private boolean selected;

    /**
     * Konštruktor triedy.
     * @param x - súradnica na x-ovej osi kde sa má zobraziť obrázok.
     * @param y - súradnica na y-ovej osi kde sa má zobraziť obrázok.
     * @param name - názov obrázku, potrebné na nájdenie súbora v adresári.
     */
    public Button(int x, int y, String name) {
        this.imgs = new Obrazok[3];
        this.imgs[0] = new Obrazok("resource/buttons/" + name + "_n.png");
        this.imgs[1] = new Obrazok("resource/buttons/" + name + "_h.png");
        this.imgs[2] = new Obrazok("resource/buttons/" + name + "_c.png");
        this.imgs[0].zmenPolohu(x, y);
        this.imgs[1].zmenPolohu(x, y);
        this.imgs[2].zmenPolohu(x, y);
        this.showNormal();
        this.money = 0;
        this.selected = false;
    }

    /**
     * Getter na peniaze.
     * @return - vráti množstvo peňazí.
     */
    public int getMoney() {
        return this.money;
    }

    /**
     * Setter na peniaze.
     * @param money
     */
    public void setMoney(int money) {
        this.money = money;
    }

    /**
     * Getter na atribút selected.
     * @return - vráti true ak je karta zvolená.
     */
    public boolean isSelected() {
        return this.selected;
    }

    /**
     * Setter na atribút selected.
     * @return - void
     */
    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    /**
     * Vymení obrázky.
     * @param first - index prvého obrázku.
     * @param second - index druhého obrázku.
     */
    public void nastavObrazokNormal(int first, int second) {
        try {
            this.imgs[first] = this.imgs[second];
            this.showNormal();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("CLASS> Button METHOD> nastavObrazokNormal nespravny input");
        }
    }

    /**
     * Zobrazí obrázok - normal verziu.
     */
    public void showNormal() {
        this.imgs[0].zobraz();
    }

    /**
     * Zobrazí obrázok - hower verziu.
     */
    public void showHower() {
        this.imgs[1].zobraz();
    }

    /**
     * Zobrazí obrázok - clicked verziu.
     */
    public void showClicked() {
        this.imgs[2].zobraz();
    }

    /**
     * Abstraktná metóda ktorú vykonávajú potomkovia.
     */
    public abstract boolean execute();

    /**
     * Abstraktná metóda na getter ceny.
     */
    public abstract int getPrize();

}
