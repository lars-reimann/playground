package src.net.bplaced.programmierung.schach.logic;

import java.util.Arrays;
import java.util.Map;

import net.bplaced.programmierung.schach.gui.ChessPanel;
import net.bplaced.programmierung.schach.logic.AbstractBishop.BlackBishop;
import net.bplaced.programmierung.schach.logic.AbstractBishop.WhiteBishop;
import net.bplaced.programmierung.schach.logic.AbstractKingBothCastlings.BlackKingBothCastlings;
import net.bplaced.programmierung.schach.logic.AbstractKingBothCastlings.WhiteKingBothCastlings;
import net.bplaced.programmierung.schach.logic.AbstractKnight.BlackKnight;
import net.bplaced.programmierung.schach.logic.AbstractKnight.WhiteKnight;
import net.bplaced.programmierung.schach.logic.AbstractPawnDouble.BlackPawnDouble;
import net.bplaced.programmierung.schach.logic.AbstractPawnDouble.WhitePawnDouble;
import net.bplaced.programmierung.schach.logic.AbstractQueen.BlackQueen;
import net.bplaced.programmierung.schach.logic.AbstractQueen.WhiteQueen;
import net.bplaced.programmierung.schach.logic.AbstractRookKingside.BlackRookKingside;
import net.bplaced.programmierung.schach.logic.AbstractRookKingside.WhiteRookKingside;
import net.bplaced.programmierung.schach.logic.AbstractRookQueenside.BlackRookQueenside;
import net.bplaced.programmierung.schach.logic.AbstractRookQueenside.WhiteRookQueenside;

public final class Engine {

    public static boolean hasMoves(final Piece[] pieces, final ChessColor color) {
        for (int i = 21; i <= 98; i++) {
            final Piece piece = pieces[i];
            if (piece != DefaultPiece.INVALID && piece != DefaultPiece.NULL
                    && piece.getColor() == color) {
                if (piece.generateMoves(pieces, i, true).size() > 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isCheck(final Piece[] pieces, final ChessColor color) {
        // TODO Schleife ueberarbeiten
        final int kingPos = getKingPos(pieces, color);
        for (int i = 21; i <= 98; i++) {
            final Piece piece = pieces[i];
            if (piece != DefaultPiece.NULL && piece != DefaultPiece.INVALID
                    && piece.getColor() != color) {
                if (piece.checksEnemyKing(pieces, i, kingPos)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static int getKingPos(final Piece[] pieces, final ChessColor color) {
        for (int i = 21; i <= 98; i++) {
            if (pieces[i] instanceof AbstractKing
                    && pieces[i].getColor() == color) {
                return i;
            }
        }
        throw new AssertionError("-----Kein gegnerischer Koenig gefunden-----");
    }

    public static boolean isMate(final Piece[] pieces, final ChessColor color) {
        return !hasMoves(pieces, color) && isCheck(pieces, color);
    }

    public static boolean isStaleMate(final Piece[] pieces,
            final ChessColor color) {
        return !hasMoves(pieces, color) && !isCheck(pieces, color);
    }

    private Piece[] pieces;

    private Piece selectedPiece;

    private ChessPanel panel;

    private Map<Integer, Move> moves;

    private ChessColor player;

    private int selectedPos;

    public Engine(final ChessPanel panel) {
        super();
        pieces = new Piece[120];
        this.panel = panel;
        selectedPiece = DefaultPiece.NULL;
        setup();
        this.computer = new Computer(ChessColor.BLACK, this); // TODO Farbe
    }

    public Engine() {
        super();
        pieces = new Piece[120];
        selectedPiece = DefaultPiece.NULL;
        setup();
        this.computer = new Computer(ChessColor.BLACK, this);
    }

    private final Computer computer;

    public void click(final int pos) {
        if (player == ChessColor.WHITE && !isFinished(pieces)) {
            if (selectedPiece == DefaultPiece.NULL) {
                if (pieces[pos] != DefaultPiece.NULL
                        && pieces[pos].getColor() == player) {
                    selectedPiece = pieces[pos];
                    selectedPos = pos;
                    moves = selectedPiece.generateMoves(pieces, pos, true);
                    panel.repaint();
                }
            } else if (selectedPiece != DefaultPiece.INVALID) {
                boolean hasChanged = false;
                final Move move = moves.get(pos);
                if (move != null) {
                    move.execute(pieces, selectedPos, pos);
                    player = ChessColor.BLACK;
                    hasChanged = true;
                }
                selectedPiece = DefaultPiece.NULL;
                panel.repaint();
                if (hasChanged && !isFinished(pieces)) {
                    getComputerMove();
                } else if (isFinished(pieces)) {
                    panel.paintEndOfGame(panel.getGraphics());
                }
            }
        }
        panel.repaint();
    }

    public static boolean isFinished(Piece[] pieces) {
        return isMate(pieces, ChessColor.WHITE)
                || isStaleMate(pieces, ChessColor.WHITE)
                || isMate(pieces, ChessColor.BLACK)
                || isStaleMate(pieces, ChessColor.BLACK);
    }

    public void setPieces(final Piece[] pieces) {
        this.pieces = pieces;
        player = ChessColor.WHITE;
        panel.repaint();
        if (isFinished(pieces)) {
            panel.paintEndOfGame(panel.getGraphics());
        }
    }

    public void getComputerMove() {
        computer.setPiece(pieces);
        computer.computeMove(pieces);
    }

    public ChessColor getEnemy() {
        return player == ChessColor.WHITE ? ChessColor.BLACK : ChessColor.WHITE;
    }

    public Piece getPiece(final int pos) {
        return pieces[pos];
    }

    public Piece[] getPieces() {
        return pieces;
    }

    public ChessColor getPlayer() {
        return player;
    }

    public Piece getSelectedPiece() {
        return selectedPiece;
    }

    public Map<Integer, Move> getSelectedPieceMove() {
        return moves;
    }

    /**
     *
     */
    public void setup() {
        setupBoard();
        setupRooks();
        setupKnights();
        setupBishops();
        setupQueens();
        setupKings();
        setupPawns();
        setupEmptyFields();

        player = ChessColor.WHITE;
    }

    private void setupBishops() {
        pieces[23] = BlackBishop.getInstance();
        pieces[26] = BlackBishop.getInstance();
        pieces[93] = WhiteBishop.getInstance();
        pieces[96] = WhiteBishop.getInstance();
    }

    private void setupBoard() {
        Arrays.fill(pieces, DefaultPiece.INVALID);
    }

    private void setupEmptyFields() {
        for (int i = 41; i <= 71; i += 10) {
            for (int j = 0; j < 8; j++) {
                pieces[i + j] = DefaultPiece.NULL;
            }
        }
    }

    private void setupKings() {
        pieces[25] = BlackKingBothCastlings.getInstance();
        pieces[95] = WhiteKingBothCastlings.getInstance();
    }

    private void setupKnights() {
        pieces[22] = BlackKnight.getInstance();
        pieces[27] = BlackKnight.getInstance();
        pieces[92] = WhiteKnight.getInstance();
        pieces[97] = WhiteKnight.getInstance();
    }

    private void setupPawns() {
        for (int i = 0; i < 8; i++) {
            pieces[31 + i] = BlackPawnDouble.getInstance();
            pieces[81 + i] = WhitePawnDouble.getInstance();
        }
    }

    private void setupQueens() {
        pieces[24] = BlackQueen.getInstance();
        pieces[94] = WhiteQueen.getInstance();
    }

    private void setupRooks() {
        pieces[21] = BlackRookQueenside.getInstance();
        pieces[28] = BlackRookKingside.getInstance();
        pieces[91] = WhiteRookQueenside.getInstance();
        pieces[98] = WhiteRookKingside.getInstance();
    }

    public int getSelectedPos() {
        return selectedPos;
    }
}
