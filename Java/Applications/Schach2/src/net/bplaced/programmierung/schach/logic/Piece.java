package net.bplaced.programmierung.schach.logic;

import java.util.List;

public interface Piece {

    boolean checksEnemyKing(Engine engine, int pos);

    List<Move> generateAllMoves(Engine engine, int pos);

    List<Move> generateValidMoves(Engine engine, int pos);
    
    ChessColor getColor();

    double getPieceValue(Engine engine);

    double getPosValue(Engine engine, int pos);

    char getSymbol();
}