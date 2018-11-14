/**
 * Der Status des Minesweeperspieles. Es kann entweder gewonnen, verloren oder noch laufend sein.
 */
public enum MinesweeperState {

    /**
     * Zeigt an, dass das Spiel gewonnen ist.
     */
    WON,
    
    /**
     * Zeigt an, dass das Spiel verloren ist.
     */
    LOST,
    
    /**
     * Zeigt an, dass das Spiel noch nicht entschieden ist.
     */
    GOING
}