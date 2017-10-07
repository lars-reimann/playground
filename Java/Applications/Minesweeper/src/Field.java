public class Field {
    private boolean isSelected;
    private boolean isMarked;
    private final int neighbours;
    private final boolean isBomb;
    
    public static final Field NULL;
    
    static {
        NULL = new Field(-1, false);
        NULL.select();
    }
    
    public Field(int neighbours, boolean isBomb) {
        this.neighbours = neighbours;
        this.isBomb = isBomb;
    }
    
    public boolean isBomb() {
        return isBomb;
    }
    
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
