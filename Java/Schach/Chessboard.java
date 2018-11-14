 

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public final class Chessboard extends JPanel {

    /**
     * Serialisierungsversion.
     */
    private static final long       serialVersionUID = 4266781828110604262L;
    private transient BufferedImage blackBishop;
    private transient BufferedImage blackKing;
    private transient BufferedImage blackKnight;
    private transient BufferedImage blackPawn;
    private transient BufferedImage blackQueen;
    private transient BufferedImage blackRook;
    private transient Computer      computer;
    private transient boolean       withComputer;
    private transient int           computerColor;

    private transient int           moveCount        = 1;
    private transient StringBuilder notation;
    /**
     *
     */
    private transient Field[][]     fields;

    private final transient MyFrame frame;
    private transient boolean       hasSelectedField;
    /**
     *
     */
    private transient int           player;
    private transient Point         selectedField;

    /**
     *
     */
    private final transient int     size;

    private transient BufferedImage whiteBishop;

    private transient BufferedImage whiteKing;

    private transient BufferedImage whiteKnight;

    private transient BufferedImage whitePawn;

    private transient BufferedImage whiteQueen;

    private transient BufferedImage whiteRook;

    /**
     * 
     * @param size
     */
    public Chessboard(final MyFrame frame, final int size) {
        super();
        this.frame = frame;
        this.size = size;
        computer = new Computer(this);
        String separator = File.separator;
        try {
            blackBishop = ImageIO.read(new File("Figures" + separator + "BlackBishop.gif"));
            blackKing = ImageIO.read(new File("Figures" + separator + "BlackKing.gif"));
            blackKnight = ImageIO.read(new File("Figures" + separator + "BlackKnight.gif"));
            blackPawn = ImageIO.read(new File("Figures" + separator + "BlackPawn.gif"));
            blackQueen = ImageIO.read(new File("Figures" + separator + "BlackQueen.gif"));
            blackRook = ImageIO.read(new File("Figures" + separator + "BlackRook.gif"));
            whiteBishop = ImageIO.read(new File("Figures" + separator + "WhiteBishop.gif"));
            whiteKing = ImageIO.read(new File("Figures" + separator + "WhiteKing.gif"));
            whiteKnight = ImageIO.read(new File("Figures" + separator + "WhiteKnight.gif"));
            whitePawn = ImageIO.read(new File("Figures" + separator + "WhitePawn.gif"));
            whiteQueen = ImageIO.read(new File("Figures" + separator + "WhiteQueen.gif"));
            whiteRook = ImageIO.read(new File("Figures" + separator + "WhiteRook.gif"));
        } catch (IOException exception) {
            // Kann eigentlich nicht auftreten
            exception.printStackTrace();
        }
        fields = new Field[8][8];
        setSize(size, size);
        addMouseListener(new MyMouseListener(this));
        setup();
    }

    public int getComputerColor() {
        return computerColor;
    }

    public int getPlayer() {
        return player;
    }

    public boolean withComputer() {
        return withComputer;
    }

    /**
     * 
     * @param fields
     * @return
     */
    public static Field[][] copyFields(final Field[][] fields) {
        Field[][] fieldsCopy = new Field[fields.length][fields.length];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                final AbstractFigure figure = fields[i][j].getFigure();
                AbstractFigure figureCopy = null;
                if (figure != null) {
                    try {
                        figureCopy = (AbstractFigure) fields[i][j].getFigure()
                                        .clone();
                    } catch (CloneNotSupportedException exception) {
                    }
                }
                fieldsCopy[i][j] = new Field(figureCopy);
            }
        }
        return fieldsCopy;
    }

    /**
     * 
     * @param color
     * @param fieldsCopy
     * @return
     */
    public static boolean isCheck(final int color, final Field[][] fieldsCopy) {
        boolean isCheck = false;
        Label:
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (fieldsCopy[i][j].getFigure() != null &&
                    fieldsCopy[i][j].getFigure().getColor() != color &&
                    fieldsCopy[i][j].getFigure().canBeatKing(fieldsCopy)) {
                    isCheck = true;
                    break Label;
                }
            }
        }
        return isCheck;
    }

    public void clickOnField(final int x, final int y) {
        if (hasSelectedField) {
            if (fields[x][y].isPossible() || fields[x][y].isBeatable()) {
                resetHasMovedTwice();
                resetSelections();
                noteMove(x, y);
                moveFigure(x, y);
                if (player == ChessConstants.WHITE) {
                    player = ChessConstants.BLACK;
                } else {
                    player = ChessConstants.WHITE;
                }
                if (displayEndOfGame() && withComputer &&
                    computerColor == player) {
                    computer.setFields(fields);
                    computer.moveFigure();
                }
                frame.setText(notation.toString());
            } else if (!fields[x][y].isSelected()) {
                resetSelections();
                selectField(x, y);
            }
        } else {
            selectField(x, y);
        }
        repaint();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(size, size);
    }

    /**
     *
     */
    public void setup() {

        // Tuerme
        if (frame.withRooksIsSelected()) {
            fields[0][0] = new Field(new Rook(ChessConstants.BLACK, 0, 0));
            fields[7][0] = new Field(new Rook(ChessConstants.BLACK, 7, 0));
            fields[0][7] = new Field(new Rook(ChessConstants.WHITE, 0, 7));
            fields[7][7] = new Field(new Rook(ChessConstants.WHITE, 7, 7));
        } else {
            fields[0][0] = new Field(null);
            fields[7][0] = new Field(null);
            fields[0][7] = new Field(null);
            fields[7][7] = new Field(null);
        }

        // Springer
        if (frame.withKnightsIsSelected()) {
            fields[1][0] = new Field(new Knight(ChessConstants.BLACK, 1, 0));
            fields[6][0] = new Field(new Knight(ChessConstants.BLACK, 6, 0));
            fields[1][7] = new Field(new Knight(ChessConstants.WHITE, 1, 7));
            fields[6][7] = new Field(new Knight(ChessConstants.WHITE, 6, 7));
        } else {
            fields[1][0] = new Field(null);
            fields[6][0] = new Field(null);
            fields[1][7] = new Field(null);
            fields[6][7] = new Field(null);
        }

        // Laeufer
        if (frame.withBishopsIsSelected()) {
            fields[2][0] = new Field(new Bishop(ChessConstants.BLACK, 2, 0));
            fields[5][0] = new Field(new Bishop(ChessConstants.BLACK, 5, 0));
            fields[2][7] = new Field(new Bishop(ChessConstants.WHITE, 2, 7));
            fields[5][7] = new Field(new Bishop(ChessConstants.WHITE, 5, 7));
        } else {
            fields[2][0] = new Field(null);
            fields[5][0] = new Field(null);
            fields[2][7] = new Field(null);
            fields[5][7] = new Field(null);
        }

        // Damen
        if (frame.withQueensIsSelected()) {
            fields[3][0] = new Field(new Queen(ChessConstants.BLACK, 3, 0));
            fields[3][7] = new Field(new Queen(ChessConstants.WHITE, 3, 7));
        } else {
            fields[3][0] = new Field(null);
            fields[3][7] = new Field(null);
        }

        // Koenige
        fields[4][0] = new Field(new King(ChessConstants.BLACK, 4, 0));
        fields[4][7] = new Field(new King(ChessConstants.WHITE, 4, 7));

        // Bauern und leere Felder
        for (int i = 0; i < 8; i++) {
            fields[i][1] = new Field(new Pawn(ChessConstants.BLACK, i, 1));
            for (int j = 2; j < 6; j++) {
                fields[i][j] = new Field(null);
            }
            fields[i][6] = new Field(new Pawn(ChessConstants.WHITE, i, 6));
        }

        AbstractFigure.setFields(fields);
        AbstractFigure.setComputer(computer);
        AbstractFigure.setChessboard(this);
        player = ChessConstants.WHITE;
        moveCount = 1;
        notation = new StringBuilder();
        hasSelectedField = false;
        frame.setText(notation.toString());
        if (frame.withComputerIsSelected()) {
            withComputer = true;
        } else {
            withComputer = false;
        }
        computerColor = frame.getComputerColor();
        repaint();
    }

    public void setupComputer() {
        computer.setFields(fields);
        computer.setColor(computerColor);
        if (ChessConstants.WHITE == computerColor) {
            computer.moveFigure();
        }
    }

    /**
     *
     */
    @Override
    protected void paintComponent(final Graphics graphics) {
        paintField(graphics);
        paintFigures(graphics);
    }

    public void setField(final Field[][] fields) {
        this.fields = fields;
    }

    /**
     *
     */
    private boolean displayEndOfGame() {
        final Field[][] fieldsCopy = copyFields(fields);
        final boolean hasPossibleMoves = hasPossibleMoves(player, fields);
        final boolean isCheck = isCheck(player, fieldsCopy);
        if (isCheck && hasPossibleMoves) {
            notation.append("+");
        } else if (!hasPossibleMoves) {
            repaint();
            if (isCheck) {
                notation.append("#");
                frame.setText(notation.toString());
                if (player == ChessConstants.WHITE) {
                    JOptionPane.showMessageDialog(this,
                                                  "Schwarz gewinnt!",
                                                  "Schachmatt",
                                                  JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this,
                                                  "Weiss gewinnt!",
                                                  "Schachmatt",
                                                  JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                frame.setText(notation.toString());
                JOptionPane.showMessageDialog(this,
                                              "Spiel endet unentschieden!",
                                              "Patt",
                                              JOptionPane.INFORMATION_MESSAGE);
            }
        }
        return hasPossibleMoves;
    }

    /**
     * 
     * @return
     */
    public static boolean hasPossibleMoves(final int color,
                    final Field[][] fields) {
        boolean hasPossibleMoves = false;

        Label:
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (fields[i][j].getFigure() != null &&
                    fields[i][j].getFigure().getColor() == color) {
                    fields[i][j].getFigure().markMoves(fields);
                    for (int k = 0; k < 8; k++) {
                        for (int l = 0; l < 8; l++) {
                            if (fields[k][l].isPossible() ||
                                fields[k][l].isBeatable()) {
                                hasPossibleMoves = true;
                                break Label;
                            }
                        }
                    }
                    for (int k = 0; k < 8; k++) {
                        for (int l = 0; l < 8; l++) {
                            fields[k][l].resetSelections();
                        }
                    }
                }
            }
        }
        for (int k = 0; k < 8; k++) {
            for (int l = 0; l < 8; l++) {
                fields[k][l].resetSelections();
            }
        }
        return hasPossibleMoves;
    }

    private void noteMove(final int newX, final int newY) {
        final AbstractFigure newFigure = fields[newX][newY].getFigure();
        final int oldX = selectedField.x;
        final int oldY = selectedField.y;
        final AbstractFigure selectedFigure = fields[oldX][oldY].getFigure();

        if (player == ChessConstants.WHITE) {
            if (moveCount > 1) {
                notation.append("\n");
            }
            notation.append(moveCount + ". ");
        } else {
            notation.append("\t");
        }

        // Notation
        if (selectedFigure instanceof Bishop) {
            notation.append("L" + (char) (97 + oldX) + (8 - oldY));
            if (newFigure == null) {
                notation.append("-");
            } else {
                notation.append("x");
            }
            notation.append(Character.toString((char) (97 + newX)) + (8 - newY));
        } else if (selectedFigure instanceof King) {
            if (Math.abs(newX - oldX) == 2) {
                if (newX == 2) {
                    notation.append("0–0-0");
                } else {
                    notation.append("0-0");
                }
            } else {
                notation.append("K" + (char) (97 + oldX) + (8 - oldY));
                if (newFigure == null) {
                    notation.append("-");
                } else {
                    notation.append("x");
                }
                notation.append(Character.toString((char) (97 + newX)) +
                                (8 - newY));
            }
        } else if (selectedFigure instanceof Knight) {
            notation.append("S" + (char) (97 + oldX) + (8 - oldY));
            if (newFigure == null) {
                notation.append("-");
            } else {
                notation.append("x");
            }
            notation.append(Character.toString((char) (97 + newX)) + (8 - newY));
        }
        if (selectedFigure instanceof Pawn) {
            notation.append(Character.toString((char) (97 + oldX)) + (8 - oldY));
            if (newFigure == null) {
                notation.append("-");
            } else {
                notation.append("x");
            }
            notation.append(Character.toString((char) (97 + newX)) + (8 - newY));
            if (Math.abs(newX - oldX) == 1 && newFigure == null) {
                notation.append(" e. p.");
            }
        } else if (selectedFigure instanceof Queen) {
            notation.append("D" + (char) (97 + oldX) + (8 - oldY));
            if (newFigure == null) {
                notation.append("-");
            } else {
                notation.append("x");
            }
            notation.append(Character.toString((char) (97 + newX)) + (8 - newY));
        } else if (selectedFigure instanceof Rook) {
            notation.append("T" + (char) (97 + oldX) + (8 - oldY));
            if (newFigure == null) {
                notation.append("-");
            } else {
                notation.append("x");
            }
            notation.append(Character.toString((char) (97 + newX)) + (8 - newY));
        }

        if (player == ChessConstants.BLACK) {
            moveCount++;
        }
    }

    public String getNotation() {
        return notation.toString();
    }

    private void moveFigure(final int newX, final int newY) {
        final AbstractFigure newFigure = fields[newX][newY].getFigure();
        final int oldX = selectedField.x;
        final int oldY = selectedField.y;
        final AbstractFigure selectedFigure = fields[oldX][oldY].getFigure();

        // Umsetzen der Figur
        selectedFigure.setLocation(newX, newY);
        fields[newX][newY].setFigure(selectedFigure);
        fields[oldX][oldY].setFigure(null);

        // Spezielle Zuege
        if (selectedFigure instanceof Pawn) {
            if (Math.abs(newY - oldY) == 2) {
                ((Pawn) selectedFigure).setHasMovedTwice(true);
            } else {
                ((Pawn) selectedFigure).setHasMovedTwice(false);
                if (Math.abs(newX - oldX) == 1 && newFigure == null) {
                    fields[newX][newY - player].setFigure(null);
                } else if (newY == 0 || newY == 7) {
                    if (withComputer && computerColor == player) {
                        fields[newX][newY].setFigure(new Queen(player, newX, newY));
                        notation.append("D");
                    } else {
                        promotePawn(newX, newY);
                    }
                }
            }
        } else if (selectedFigure instanceof Rook) {
            ((Rook) selectedFigure).setHasMoved(true);
        } else if (selectedFigure instanceof King) {
            ((King) selectedFigure).setHasMoved(true);
            if (Math.abs(newX - oldX) == 2) {
                if (newX == 2) {
                    fields[3][newY].setFigure(fields[0][newY].getFigure());
                    fields[0][newY].setFigure(null);
                    fields[3][newY].getFigure().setLocation(3, newY);
                } else {
                    fields[5][newY].setFigure(fields[7][newY].getFigure());
                    fields[7][newY].setFigure(null);
                    fields[5][newY].getFigure().setLocation(5, newY);
                }
            }
        }
    }

    /**
     * 
     * @param graphics
     */
    private void paintField(final Graphics graphics) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                final Field field = fields[i][j];
                if (field.isSelected()) {
                    graphics.setColor(ChessConstants.SELECTED_FIELD);
                } else if (field.isBeatable()) {
                    graphics.setColor(ChessConstants.BEATABLE_FIELD);
                } else if (field.isPossible()) {
                    graphics.setColor(ChessConstants.POSSIBLE_FIELD);
                } else if ((i + j) % 2 == 0) {
                    graphics.setColor(ChessConstants.WHITE_FIELD);
                } else {
                    graphics.setColor(ChessConstants.BLACK_FIELD);
                }
                graphics.fillRect(i * size / 8, j * size / 8, size / 8,
                                  size / 8);
            }
        }
        graphics.setColor(Color.black);
        for (int i = 1; i < 8; i++) {
            graphics.drawLine(i * size / 8 - 1, 0, i * size / 8 - 1, size);
            graphics.drawLine(0, i * size / 8 - 1, size, i * size / 8 - 1);
        }
        graphics.drawRect(0, 0, size - 1, size - 1);
    }

    /**
     * 
     * @param graphics
     */
    private void paintFigures(final Graphics graphics) {
        graphics.setFont(new Font(Font.SERIF, Font.ITALIC | Font.BOLD, 40));
        final int length = (int) Math.round((double) size / 12);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                final AbstractFigure figure = fields[i][j].getFigure();
                final int x = (int) Math.round((double) size / 8 * i +
                                               (double) size / 16 - length / 2);
                final int y = (int) Math.round((double) size / 8 * j +
                                               (double) size / 16 - length / 2);
                if (figure == null) {
                    continue;
                } else if (figure instanceof Bishop) {
                    if (figure.getColor() == ChessConstants.WHITE) {
                        graphics.drawImage(whiteBishop, x, y, length, length,
                                           this);
                    } else {
                        graphics.drawImage(blackBishop, x, y, length, length,
                                           this);
                    }
                } else if (figure instanceof King) {
                    if (figure.getColor() == ChessConstants.WHITE) {
                        graphics.drawImage(whiteKing, x, y, length, length,
                                           this);
                    } else {
                        graphics.drawImage(blackKing, x, y, length, length,
                                           this);
                    }
                } else if (figure instanceof Knight) {
                    if (figure.getColor() == ChessConstants.WHITE) {
                        graphics.drawImage(whiteKnight, x, y, length, length,
                                           this);
                    } else {
                        graphics.drawImage(blackKnight, x, y, length, length,
                                           this);
                    }
                } else if (figure instanceof Pawn) {
                    if (figure.getColor() == ChessConstants.WHITE) {
                        graphics.drawImage(whitePawn, x, y, length, length,
                                           this);
                    } else {
                        graphics.drawImage(blackPawn, x, y, length, length,
                                           this);
                    }
                } else if (figure instanceof Queen) {
                    if (figure.getColor() == ChessConstants.WHITE) {
                        graphics.drawImage(whiteQueen, x, y, length, length,
                                           this);
                    } else {
                        graphics.drawImage(blackQueen, x, y, length, length,
                                           this);
                    }
                } else if (figure instanceof Rook) {
                    if (figure.getColor() == ChessConstants.WHITE) {
                        graphics.drawImage(whiteRook, x, y, length, length,
                                           this);
                    } else {
                        graphics.drawImage(blackRook, x, y, length, length,
                                           this);
                    }
                }
            }
        }
    }

    /**
     * 
     * @param color
     * @param x
     * @param y
     */
    private void promotePawn(final int x, final int y) {
        final Object[] options = {"Dame", "Turm", "Springer", "Laeufer"};
        final Field field = fields[x][y];
        final int option = JOptionPane
                        .showOptionDialog(this,
                                          "Bitte waehlen Sie eine Figur aus.",
                                          "Aufstieg",
                                          JOptionPane.DEFAULT_OPTION,
                                          JOptionPane.QUESTION_MESSAGE, null,
                                          options, null);
        if (option == 0 || option == JOptionPane.CLOSED_OPTION) {
            field.setFigure(new Queen(player, x, y));
            notation.append("D");
        } else if (option == 1) {
            field.setFigure(new Rook(player, x, y));
            notation.append("T");
        } else if (option == 2) {
            field.setFigure(new Knight(player, x, y));
            notation.append("S");
        } else if (option == 3) {
            field.setFigure(new Bishop(player, x, y));
            notation.append("L");
        }
    }

    public Computer getComputer() {
        return computer;
    }

    private void resetSelections() {
        for (int k = 0; k < 8; k++) {
            for (int l = 0; l < 8; l++) {
                fields[k][l].resetSelections();
            }
        }
        hasSelectedField = false;
    }

    private void resetHasMovedTwice() {
        for (int k = 0; k < 8; k++) {
            for (int l = 0; l < 8; l++) {
                if (fields[k][l].getFigure() instanceof Pawn &&
                    fields[k][l].getFigure().getColor() == player) {
                    ((Pawn) fields[k][l].getFigure()).setHasMovedTwice(false);
                }
            }
        }
    }

    private void selectField(final int x, final int y) {
        if (fields[x][y].getFigure() != null &&
            fields[x][y].getFigure().getColor() == player) {
            fields[x][y].setSelected(true);
            selectedField = new Point(x, y);
            hasSelectedField = true;
            fields[x][y].getFigure().markMoves(fields);
        }
    }
}
