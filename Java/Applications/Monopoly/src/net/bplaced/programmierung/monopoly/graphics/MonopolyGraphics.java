package net.bplaced.programmierung.monopoly.graphics;

import java.util.List;

import net.bplaced.programmierung.monopoly.logic.Monopoly;

public final class MonopolyGraphics {
    
    private final Monopoly monopoly;
    private final MonopolyFrame monopolyFrame;

    public MonopolyGraphics(Monopoly monopoly) {
        this.monopoly = monopoly;
        monopolyFrame = new MonopolyFrame();
    }

    public List<String> requestPlayerNames() {
        return MonopolyDialogs.requestPlayerNames(monopolyFrame);
    }
}
