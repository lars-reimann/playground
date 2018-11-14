import java.util.Random;

public class Minesweeper {
    private Field[][] fields;
    private int nBombs;
    private int nSel;
    private int nMark;
    private int nAim;
    private MinesweeperState state;

    public Minesweeper() {
        state = MinesweeperState.GOING;
    }

    public boolean setSize(int x, int y, int nBombs) {
        if (x > 0 && y > 0 && nBombs > 0 && nBombs < x * y) {
            nSel = 0;
            nMark = 0;
            nAim = x * y - nBombs;
            fields = new Field[x + 2][y + 2];
            this.nBombs = nBombs;
            fillFields();
            placeBombs(nBombs);
            countNeighbours();
            state = MinesweeperState.GOING;
            return true;
        }
        return false;
    }

    private void fillFields() {
        for (int i = 0; i < fields.length; i++) {
            for (int j = 0; j < fields[i].length; j++) {
                fields[i][j] = Field.NULL;
            }
        }
    }

    private void placeBombs(int nBombs) {
        Random random = new Random();

        for (int i = nBombs; i > 0;) {
            int x = 1 + random.nextInt(fields.length - 2);
            int y = 1 + random.nextInt(fields[x].length - 2);
            if (fields[x][y] == Field.NULL) {
                fields[x][y] = new Field(0, true);
                i--;
            }
        }
    }

    private void countNeighbours() {
        for (int i = 1; i < fields.length - 1; i++) {
            for (int j = 1; j < fields[i].length - 1; j++) {
                if (!fields[i][j].isBomb()) {
                    fields[i][j] = new Field(countNeighbours(i, j), false);
                }
            }
        }
    }

    private int countNeighbours(int x, int y) {
        int count = 0;
        if (fields[x - 1][y - 1].isBomb()) {
            count++;
        }
        if (fields[x][y - 1].isBomb()) {
            count++;
        }
        if (fields[x + 1][y - 1].isBomb()) {
            count++;
        }
        if (fields[x - 1][y].isBomb()) {
            count++;
        }
        if (fields[x + 1][y].isBomb()) {
            count++;
        }
        if (fields[x - 1][y + 1].isBomb()) {
            count++;
        }
        if (fields[x][y + 1].isBomb()) {
            count++;
        }
        if (fields[x + 1][y + 1].isBomb()) {
            count++;
        }
        return count;
    }

    public boolean sel(int x, int y) {
        if (x > 0 && y > 0 && x < fields.length - 1 && y < fields[x].length - 1
                && !fields[x][y].isSelected()) {
            if (fields[x][y].isBomb()) {
                uncover();
                state = MinesweeperState.LOST;
            } else {
                if (fields[x][y].getNeighbours() == 0) {
                    showEmptyFields(x, y);
                } else {
                    nSel++;
                    fields[x][y].select();
                }
                check(); // TODO Sieg
            }
            return true;
        }
        return false;
    }

    private void check() {
        if (nSel == nAim) {
            state = MinesweeperState.WON;
        }
    }

    private void uncover() {
        for (int i = 1; i < fields.length - 1; i++) {
            for (int j = 1; j < fields[i].length - 1; j++) {
                fields[i][j].select();
            }
        }
    }

    private void showEmptyFields(int x, int y) {
        if (!fields[x][y].isSelected()) {
            nSel++;
            fields[x][y].select();
            if (fields[x][y].getNeighbours() == 0) {
                showEmptyFields(x - 1, y - 1);
                showEmptyFields(x, y - 1);
                showEmptyFields(x + 1, y - 1);
                showEmptyFields(x - 1, y);
                showEmptyFields(x + 1, y);
                showEmptyFields(x - 1, y + 1);
                showEmptyFields(x, y + 1);
                showEmptyFields(x + 1, y + 1);
            }
        }
    }

    public MinesweeperState getState() {
        return state;
    }

    public String[] getRepresentation() {
        String[] representation = new String[fields[0].length - 2];
        for (int i = 1; i < fields.length - 1; i++) {
            representation[i - 1] = "";
            for (int j = 1; j < fields[i].length - 1; j++) {
                if (fields[i][j].isSelected()) {
                    if (fields[i][j].isBomb()) {
                        representation[i - 1] += "X";
                    } else {
                        representation[i - 1] += fields[i][j].getNeighbours();
                    }
                } else if (fields[i][j].isMarked()) {
                    representation[i - 1] += "!";
                } else {
                    representation[i - 1] += "-";
                }
            }
        }
        return representation;
    }

    public int getNBombs() {
        return nBombs;
    }

    public boolean mark(int x, int y) {
        if (x > 0 && y > 0 && x < fields.length - 1 && y < fields[x].length - 1) {
            if (fields[x][y].mark()) { // TODO Ausgewaehlte Elemente duerfen
                                       // nicht markiert werden // x, y
                                       // Koordinaten falsch herum
                nMark++;
                check();
            } else {
                nMark--;
            }

        }
        return false;
    }
}
