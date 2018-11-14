 

/**
 * Enthaelt alle Informationen eines Feldes des Schachbretts und entsprechende
 * getter- und setter-Methoden. Jedes Feld kann eine Figur beinhalten und
 * ausgewaehlt oder moeglich sein oder eine schlagbare Figur besitzen. Je
 * nachdem welche Bedingungen erfuellt sind, wird das Feld in einer anderen
 * Farbe dargestellt:<br>
 * <br>
 * -Ausgewaehlt: Blau<br>
 * -Moeglich: Gruen<br>
 * -Schlagbar: Rot<br>
 * -Sonst: Schwarz/weiss.
 * 
 * @author Lars Reimann
 * @version 3. Maerz 2011
 */
public final class Field {

    /**
     * Die Figur, die auf diesem Feld steht. Falls sich keine Figur auf dem Feld
     * befindet, hat diese Variable den Wert null.
     */
    private transient AbstractFigure figure;

    /**
     * Zeigt an ob die Figur auf diesem Feld von der ausgewaehlten Figur
     * geschlagen werden kann. Wenn es moeglich ist, wird das Feld rot
     * dargestellt, sonst schwarz oder weiss.
     */
    private transient boolean        isBeatable;

    /**
     * Zeigt an, ob die ausgewaehlte Figur auf dieses Feld ziehen kann. Falls es
     * moeglich ist, wird das Feld gruen dargestellt, sonst schwarz oder weiss.
     */
    private transient boolean        isPossible;

    /**
     * Zeigt an, ob das betreffende Feld ausgewaehlt ist. Ist dies der Fall,
     * wird es blau dargestellt, sonst in der entsprechenden Farbe
     * (schwarz/weiss).
     */
    private transient boolean        isSelected;

    /**
     * Konstruiert ein neues Feld, auf dem die gegebene Figur stehen soll. Falls
     * dieses Feld leer sein soll, ist null zu uebergeben. Die Flags des Feldes
     * werden alle auf false gesetzt, so dass das Feld anfangs weder ausgewaehlt
     * noch moeglich oder schlagbar ist. Es wird deshalb im Bezug zur Position
     * auf dem Schachbrett in der entsprechenden Farbe (schwarz oder weiss)
     * gezeichnet.
     * 
     * @param figure
     *            Die Figur, die auf dem Feld stehen soll.
     */
    public Field(final AbstractFigure figure) {
        super();
        this.figure = figure;
        isBeatable = false;
        isPossible = false;
        isSelected = false;
    }

    /**
     * Gibt die Figure, die sich auf diesem Feld befindet zurueck. Sollte keine
     * Figur auf dem Feld stehen, wird null zurueckgegeben.
     * 
     * @return Die Figur, die auf diesem Feld steht.
     */
    public AbstractFigure getFigure() {
        return figure;
    }

    /**
     * Zeigt an, ob die ausgewaelte Figur die Figur, die auf diesem Feld steht,
     * schlagen kann.
     * 
     * @return Die Flag, die anzeigt ob die Figur auf dem Feld schlagbar ist.
     */
    public boolean isBeatable() {
        return isBeatable;
    }

    /**
     * Zeigt an, ob die ausgewaelte Figure auf dieses Feld ziehen kann.
     * 
     * @return Die Flag, die anzeigt ob ein Zug auf dieses Feld moeglich ist.
     */
    public boolean isPossible() {
        return isPossible;
    }

    /**
     * Zeigt an, ob dieses Feld ausgewaelt ist.
     * 
     * @return Die Flag, die anzeigt ob das Feld ausgewaelt ist.
     */
    public boolean isSelected() {
        return isSelected;
    }

    /**
     * Setzt alle Flags des Feldes wieder auf false zurueck.
     */
    public void resetSelections() {
        isPossible = false;
        isBeatable = false;
        isSelected = false;
    }

    /**
     * Setzt die Flag, die anzeigt ob die Figur, die sich auf dem Feld befindet,
     * von der ausgewaehlten Figur geschlagen werden kann, auf den gegebenen
     * Wert. Dies ist dient zur Einfaerbung des betreffenden Feldes beim
     * Zeichnen, so dass es rot dargestellt wird.
     * 
     * @param isBeatable
     *            Die Flag, die anzeigt ob die Figur auf dem Feld schlagbar ist.
     */
    public void setBeatable(final boolean isBeatable) {
        this.isBeatable = isBeatable;
    }

    /**
     * Setzt die Figur, die sich auf diesem Feld steht, auf den gegebenen Wert.
     * Falls sich keine Figur auf diesem Feld befinden soll, ist null zu
     * uebergeben.
     * 
     * @param figure
     *            Die Figur, die auf diesem Feld stehen soll.
     */
    public void setFigure(final AbstractFigure figure) {
        this.figure = figure;
    }

    /**
     * Setzt die Flag des Feldes, die anzeigt ob die ausgewaehlte Figur hier hin
     * ziehen kann, auf den gegebenen Wert. So wird die Farbe des Feldes beim
     * Zeichnen veraendert und es wird gruen dargestellt.
     * 
     * @param isPossible
     *            Die Flag, die anzeigt ob ein Zug auf dieses Feld moeglich ist.
     */
    public void setPossible(final boolean isPossible) {
        this.isPossible = isPossible;
    }

    /**
     * Setzt die Flag des Feldes, die anzeigt ob es ausgewaehlt ist, auf den
     * gegebenen Wert. Auf diese Weise wird die Farbe des Feldes beim Zeichnen
     * veraendert und es wird blau dargestellt.
     * 
     * @param isSelected
     *            Die Flag, die anzeigt ob das Feld ausgewaehlt ist.
     */
    public void setSelected(final boolean isSelected) {
        this.isSelected = isSelected;
    }
}
