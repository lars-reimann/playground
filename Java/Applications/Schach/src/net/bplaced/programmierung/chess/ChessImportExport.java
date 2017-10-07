package net.bplaced.programmierung.chess;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @version
 * @author Lars Reimann
 */
public final class ChessImportExport {   
    
    private final ChessBoard board;
    private final ChessLogic logic;
    
    public ChessImportExport(final ChessBoard board, final ChessLogic logic) {
        this.board = board;
        this.logic = logic;
    }
    
    public void importPos(final String path) {
        String pos = "";
        
        // Lesen der Datei
        Scanner scanner;
        try {
            scanner = new Scanner(new File(path));
            while(scanner.hasNext()) {
                pos += scanner.next();
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } 
        
        final byte[][] figures = new byte[8][8];
        
        // Auslesen des Strings
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                switch (pos.charAt(i * 9 + j)) {
                    case '0':
                        figures[j][i] = ChessConstants.EMPTY;
                        break;
                    case 'L':
                        figures[j][i] = ChessConstants.WHITE_BISHOP;
                        break;
                    case 'K':
                        figures[j][i] = ChessConstants.WHITE_KING;
                        break;
                    case 'S':
                        figures[j][i] = ChessConstants.WHITE_KNIGHT;
                        break;
                    case 'B':
                        figures[j][i] = ChessConstants.WHITE_PAWN;
                        break;
                    case 'D':
                        figures[j][i] = ChessConstants.WHITE_QUEEN;
                        break;
                    case 'T':
                        figures[j][i] = ChessConstants.WHITE_ROOK;
                        break;
                    case 'l':
                        figures[j][i] = ChessConstants.BLACK_BISHOP;
                        break;
                    case 'k':
                        figures[j][i] = ChessConstants.BLACK_KING;
                        break;
                    case 's':
                        figures[j][i] = ChessConstants.BLACK_KNIGHT;
                        break;
                    case 'b':
                        figures[j][i] = ChessConstants.BLACK_PAWN;
                        break;
                    case 'd':
                        figures[j][i] = ChessConstants.BLACK_QUEEN;
                        break;
                    case 't':
                        figures[j][i] = ChessConstants.BLACK_ROOK;
                        break;
                }
            }
        }
        board.setFigures(figures);
        board.repaint();
    }
}
