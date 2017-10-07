/**
 * Die Instanzen dieser Klasse enthalten Informationen ueber ihren Schluessel
 * und ob der Einsetzungs- oder Elementzeiger auf ihnen steht.
 * 
 * @author Lars Reimann
 * @version 30. Mai 2011
 */
public class Entry {

    /**
     * Gibt an, ob der Einsetzungszeiger auf diesem Eintrag steht.
     */
    private boolean isInsertPos;

    /**
     * Gibt an, ob der Elementzeiger auf diesem Eintrag steht.
     */
    private boolean isSelectedPos;

    /**
     * Der Schluessel dieses Eintrages.
     */
    private int key;

    /**
     * Konstruiert einen neuen Eintrag mit einem zufaelligen Schluessel zwischen
     * 50 und 150.
     */
    public Entry() {
        newKey();
    }

    /**
     * Konstruiert einen neuen Eintrag mit dem uebergebenen Schluessel.
     * 
     * @param key
     *            Der Schluessel den der Eintrag besitzen soll.
     */
    public Entry(final int key) {
        setKey(key);
    }

    /**
     * Dient zur Abfrage der momentanen Schluessels dieses Elementes.
     * 
     * @return Den Schluessel dieses Eintrages.
     */
    public int getKey() {
        return key;
    }

    /**
     * Dient zur Abfrage, ob der Einsetzungszeiger momentan auf diesen Eintrag
     * deutet.
     * 
     * @return Ob der Einsetzungszeiger auf diesen Eintrag deutet.
     */
    public boolean isInsertPos() {
        return isInsertPos;
    }

    /**
     * Dient zur Abfrage, ob der Elementzeiger momentan auf diesen Eintrag
     * deutet.
     * 
     * @return Ob der Elementzeiger auf diesen Eintrag deutet.
     */
    public boolean isSelectedPos() {
        return isSelectedPos;
    }

    /**
     * Weist diesem Eintrag einen neuen zufaelligen Schluessel zwischen 50 und
     * 150 zu.
     */
    public void newKey() {
        key = (int) (Math.random() * 101) + 50;
    }

    /**
     * Setzt die Flag, ob der Einsetzungszeiger momentan auf diesen Eintrag
     * deutet auf den uebergebenen Wert.
     * 
     * @param isInsertPos
     *            Gibt an, ob der Einsetzungszeiger auf diesen Eintrag deutet.
     */
    public void setIsInsertPos(final boolean isInsertPos) {
        this.isInsertPos = isInsertPos;
    }

    /**
     * Setzt die Flag, ob der Elementzeiger momentan auf diesen Eintrag deutet
     * auf den uebergebenen Wert.
     * 
     * @param isSelected
     *            Gibt an, ob der Elementzeiger auf diesen Entrag deutet.
     */
    public void setIsSelectedPos(final boolean isSelected) {
        this.isSelectedPos = isSelected;
    }

    /**
     * Weist diesem Eintrag den uebergebenen Schluessel zu.
     * 
     * @param key
     *            Der neue Schluessel, der diesem Eintrag zugewiesen werden
     *            soll.
     * @throws IllegalArgumentException
     */
    public void setKey(final int key) throws IllegalArgumentException {
        if (key < 0 || key > 150) {
            throw new IllegalArgumentException();
        } else {
            this.key = key;
        }
    }
}

