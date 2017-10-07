package net.bplaced.programmierung.chess;

/**
 * @version
 * @author Lars Reimann
 */
public final class ChessLogic {
    
    /**
     * 
     */
    private final ChessBoard board;
    
    /**
     * 
     */
    private final ChessComputer computer;
    
    /**
     * Die Tabelle, in der die Figuren auf den einzelnen Feldern gespeichert
     * werden.
     */
    private byte[][] figures;
    
    /**
     * 
     */
    private final ChessFrame frame;
    
    /**
     * @param board
     * @param frame
     */    
    public ChessLogic(final ChessBoard board, final ChessFrame frame) {
        this.board = board;
        computer = new ChessComputer(this);
        figures = new byte[8][8];
        this.frame = frame;
    }
}
