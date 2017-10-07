package net.bplaced.programmierung.schach.logic;

import java.util.List;
import java.util.Arrays;

import javax.swing.JPanel;

public final class Engine {

    // /**
    // *
    // * @return
    // */
    // public static boolean hasPossibleMoves(final Color color,
    // final Piece[][] pieces) {
    // boolean hasPossibleMoves = false;
    //
    // Label: for (int i = 0; i < 8; i++) {
    // for (int j = 0; j < 8; j++) {
    // if (pieces[i][j] != null && pieces[i][j].getColor() == color) {
    // pieces[i][j].markMoves(fields);
    // for (int k = 0; k < 8; k++) {
    // for (int l = 0; l < 8; l++) {
    // if (fields[k][l].isPossible()
    // || fields[k][l].isBeatable()) {
    // hasPossibleMoves = true;
    // break Label;
    // }
    // }
    // }
    // for (int k = 0; k < 8; k++) {
    // for (int l = 0; l < 8; l++) {
    // fields[k][l].resetSelections();
    // }
    // }
    // }
    // }
    // }
    // for (int k = 0; k < 8; k++) {
    // for (int l = 0; l < 8; l++) {
    // fields[k][l].resetSelections();
    // }
    // }
    // return hasPossibleMoves;
    // }

    private Piece[] pieces;

    private Piece selectedPiece;

    private JPanel panel;

    private List<Move> moves;

    // /**
    // *
    // * @param size
    // */
    // public Engine() {
    // super();
    // pieces = new Piece[120];
    //
    // setup();
    // }

    public void click(final int pos) {
	if (selectedPiece == DefaultPiece.NULL) {
	    if (pieces[pos] != DefaultPiece.NULL) {
		selectedPiece = pieces[pos];
		moves = selectedPiece.generateMoves(pieces, true);
	    }
	} else if (selectedPiece != DefaultPiece.INVALID){
	    for (Move move : moves) {
		if (move.getNewPos() == pos) {
		    pieces = move.simulateMove(pieces);
		    break;
		}
	    }
	    selectedPiece = DefaultPiece.NULL;
	}
	panel.repaint();
    }
//	else {
//	    if (fields[x][y].isPossible() || fields[x][y].isBeatable()) {
//		noteMove(x, y);
//		moveFigure(x, y);
//		if (player == -1) {
//		    player = 1;
//		} else {
//		    player = -1;
//		}
//		if (displayEndOfGame() && withComputer
//			&& computerColor == player) {
//		    computer.moveFigure();
//		}
//	    } else if (!fields[x][y].isSelected()) {
//		selectedPiece = pieces[pos];
//	    }
//	}
	
    

    // /**
    // *
    // */
    // private boolean displayEndOfGame() {
    // final Piece[][] figures = copyFigures(this.figures);
    // final boolean hasPossibleMoves = hasPossibleMoves(player, figures,
    // fields);
    // final boolean isCheck = isCheck(player, figures);
    // if (isCheck && hasPossibleMoves) {
    // notation.append("+");
    // } else if (!hasPossibleMoves) {
    // data.repaint();
    // if (isCheck) {
    // if (player == -1) {
    // notation.append(" 0-1");
    // frame.setText(notation.toString());
    // JOptionPane.showMessageDialog(data, "Schwarz gewinnt!",
    // "Schachmatt", JOptionPane.INFORMATION_MESSAGE);
    // } else {
    // notation.append(" 1-0");
    // frame.setText(notation.toString());
    // JOptionPane.showMessageDialog(data, "Weiss gewinnt!",
    // "Schachmatt", JOptionPane.INFORMATION_MESSAGE);
    // }
    // } else {
    // notation.append(" 1/2-1/2");
    // frame.setText(notation.toString());
    // JOptionPane.showMessageDialog(data,
    // "Spiel endet unentschieden!", "Patt",
    // JOptionPane.INFORMATION_MESSAGE);
    // }
    // }
    // return hasPossibleMoves;
    // }

    // private void noteMove(final int newX, final int newY) {
    // final Piece newFigure = figures[newX][newY];
    // final int oldX = selectedField.x;
    // final int oldY = selectedField.y;
    // final Piece selectedFigure = figures[oldX][oldY];
    //
    // if (player == -1) {
    // if (moveCount > 1) {
    // notation.append("\n");
    // }
    // notation.append(moveCount + ". ");
    // } else {
    // notation.append("\t");
    // moveCount++;
    // }
    //
    // // Notation
    // if (selectedFigure instanceof Bishop) {
    // notation.append("L" + (char) (97 + oldX) + (8 - oldY));
    // if (newFigure == null) {
    // notation.append("-");
    // } else {
    // notation.append("x");
    // }
    // notation.append(Character.toString((char) (97 + newX)) + (8 - newY));
    // } else if (selectedFigure instanceof King) {
    // if (Math.abs(newX - oldX) == 2) {
    // if (newX == 2) {
    // notation.append("0-0-0");
    // } else {
    // notation.append("0-0");
    // }
    // } else {
    // notation.append("K" + (char) (97 + oldX) + (8 - oldY));
    // if (newFigure == null) {
    // notation.append("-");
    // } else {
    // notation.append("x");
    // }
    // notation.append(Character.toString((char) (97 + newX))
    // + (8 - newY));
    // }
    // } else if (selectedFigure instanceof Knight) {
    // notation.append("S" + (char) (97 + oldX) + (8 - oldY));
    // if (newFigure == null) {
    // notation.append("-");
    // } else {
    // notation.append("x");
    // }
    // notation.append(Character.toString((char) (97 + newX)) + (8 - newY));
    // }
    // if (selectedFigure instanceof Pawn) {
    // notation.append(Character.toString((char) (97 + oldX)) + (8 - oldY));
    // if (newFigure == null) {
    // notation.append("-");
    // } else {
    // notation.append("x");
    // }
    // notation.append(Character.toString((char) (97 + newX)) + (8 - newY));
    // if (Math.abs(newX - oldX) == 1 && newFigure == null) {
    // notation.append(" e. p.");
    // }
    // } else if (selectedFigure instanceof Queen) {
    // notation.append("D" + (char) (97 + oldX) + (8 - oldY));
    // if (newFigure == null) {
    // notation.append("-");
    // } else {
    // notation.append("x");
    // }
    // notation.append(Character.toString((char) (97 + newX)) + (8 - newY));
    // } else if (selectedFigure instanceof Rook) {
    // notation.append("T" + (char) (97 + oldX) + (8 - oldY));
    // if (newFigure == null) {
    // notation.append("-");
    // } else {
    // notation.append("x");
    // }
    // notation.append(Character.toString((char) (97 + newX)) + (8 - newY));
    // }
    // }

    public Engine(final JPanel panel) {
	// TODO Auto-generated constructor stub
	super();
	pieces = new Piece[120];
	this.panel = panel;
	selectedPiece = DefaultPiece.NULL;
	setup();
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
    }

    private void setupBoard() {
	Arrays.fill(pieces, DefaultPiece.INVALID);
    }

    private void setupBishops() {
	pieces[24] = new Bishop(ChessColor.BLACK, 24);
	pieces[27] = new Bishop(ChessColor.BLACK, 27);
	pieces[94] = new Bishop(ChessColor.WHITE, 94);
	pieces[97] = new Bishop(ChessColor.WHITE, 97);
    }

    private void setupEmptyFields() {
	for (int i = 42; i <= 72; i += 10) {
	    for (int j = 0; j < 8; j++) {
		pieces[i + j] = DefaultPiece.NULL;
	    }
	}
    }

    private void setupKings() {
	pieces[26] = new KingBothCastlings(ChessColor.BLACK, 26);
	pieces[96] = new KingBothCastlings(ChessColor.WHITE, 96);
    }

    private void setupKnights() {
	pieces[23] = new Knight(ChessColor.BLACK, 23);
	pieces[28] = new Knight(ChessColor.BLACK, 28);
	pieces[93] = new Knight(ChessColor.WHITE, 93);
	pieces[98] = new Knight(ChessColor.WHITE, 98);
    }

    private void setupPawns() {
	for (int i = 0; i < 8; i++) {
	    pieces[32 + i] = new PawnCanDoubleMove(ChessColor.BLACK, 32 + i);
	    pieces[82 + i] = new PawnCanDoubleMove(ChessColor.WHITE, 82 + i);
	}
    }

    private void setupQueens() {
	pieces[25] = new Queen(ChessColor.BLACK, 25);
	pieces[95] = new Queen(ChessColor.WHITE, 95);
    }

    private void setupRooks() {
	pieces[22] = new QueensideRook(ChessColor.BLACK, 22);
	pieces[29] = new KingsideRook(ChessColor.BLACK, 29);
	pieces[92] = new QueensideRook(ChessColor.WHITE, 92);
	pieces[99] = new KingsideRook(ChessColor.WHITE, 99);
    }

    public Piece getPiece(final int pos) {
	return pieces[pos];
    }

    public Piece getSelectedPiece() {
	return selectedPiece;
    }

    public Piece[] getPieces() {
	return pieces;
    }



    public List<Move> getSelectedPieceMove() {
	return moves;
    }
}
