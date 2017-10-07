package net.bplaced.programmierung.schach.logic;

public enum ChessColor {
    WHITE(-1, 32, 39), BLACK(1, 82, 89);
    
    private ChessColor(final int direction, final int promoStart, final int promoEnd) {
	this.direction = direction;
	this.promoStart = promoStart;
	this.promoEnd = promoEnd;
    }
    private final int direction;
    private final int promoStart;
    private final int promoEnd;
    
    public int getDirection() {
	return direction;
    }
    
    public int getPromoStart() {
	return promoStart;
    }
    
    public int getPromoEnd() {
	return promoEnd;
    }
}
