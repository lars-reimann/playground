public class Field {

    /**
     * Gibt an, ob dieses Feld ausgew&auml;hlt ist.
     */
    private boolean isSelected;
    
    /**
     * Gibt an, ob dieses Feld markiert ist.
     */
    private boolean isMarked;
    
    /**
     * Gibt die Anazahl der Bomben in der Umgebung an.
     */
    private final int neighbours;
    
    /**
     * Gibt an, ob auf diesem Feld eine Bombe liegt.
     */
    private final boolean isBomb;
    
    /**
     * Das Nullobjekt dieser Klasse, welches als Ersatz f&uuml;r die null dient, um so
     * einige Bedingungen und &Uuml;berpr&uuml,fungen zu sparen.
     */
    public static final Field NULL;
    
    /**
     * Der statische Konstruktor, der das Nullobjekt initiiert.
     */
    static {
        NULL = new Field(-1, false);
        NULL.select();
    }
    
    /**
     * Konstruiert ein neues Feld mit der angegebenen Zahl an Bomben in der Umgebung. Au&szlig;erdem
     * ist anzugeben, ob auf diesem Feld selbst eine Bombe liegt.
     * 
     * @param neighbours Die Anzahl der Bomben in der Umgebung.
     * @partam isBomb Ob das Feld selbst eine Bombe hat.
     */
    public Field(int neighbours, boolean isBomb) {
        this.neighbours = neighbours;
        this.isBomb = isBomb;
    }
    
    /**
     * Gibt an, ob das Feld eine Bombe besitzt.
     * 
     * @return Ob das Feld eine Bombe hat.
     */
    public boolean isBomb() {
        return isBomb;
    }
    
    /**
     * Gibt an, ob das Feld markiert worden ist, also ob eine "Vorsicht Bombe"-Warnung auf diesem Feld
     * platziert worden ist.
     * 
     * @return Ob das Feld markiert worden ist.
     */
    public boolean isMarked() {
        return isMarked;
    }
    
    public boolean isSelected() {
        return isSelected;
    }
    
    public int getNeighbours() {
        return neighbours;
    }
    
    public void select() {
        isSelected = true;
    }
    
    public boolean mark() {
        return isMarked = !isMarked;
    }
}
