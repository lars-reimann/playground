package net.bplaced.programmierung.chess;

/**
 * @version
 * @author Lars Reimann
 */
public final class ChessComputer {
    
    /**
     * 
     */
    private final ChessLogic logic;
    
    /**
     * Die Tabelle, in der die Figuren auf den einzelnen Feldern gespeichert
     * werden.
     */
    private byte[][] figures;
    
    /**
     * @param logic
     */
    public ChessComputer(final ChessLogic logic) {
        this.logic = logic;
    }
}
