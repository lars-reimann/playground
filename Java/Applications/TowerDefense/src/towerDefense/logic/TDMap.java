package towerDefense.logic;

import java.awt.Point;

import java.io.File;
import java.io.IOException;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

/**
 * Diese Klasse stellt die Karte dar.
 * 
 * @author (Carsten)
 * @version (1.0)
 */
public class TDMap {
    private enum Status {
        Occupied, Unoppupied, Way
    }

    private int dim;
    private int[][] field;
    private int level;;
    private Point start, end;

    // Konstruktoren
    /**
     * Map zu Level 1
     */
    public TDMap() {
        level = 1;
        init();
    }

    /**
     * Map zu gewünschtem level
     */
    public TDMap(int level) {
        this.level = level;
        if (!init()) {
            level = 1;
            init();
        }
    }

    public boolean disoccupy(int x, int y) {
        if (field[x][y] == 2) {
            field[x][y] = 0;
            return true;
        }
        return false;
    }

    public int getDim() {
        return dim;
    }

    public Point getEnd() {
        return end;
    }

    public int[][] getField() {
        return field;
    }

    public String getFieldStatus(int x, int y) {
        if (x >= dim || x < 0 || y >= dim || y < 0) {
            return "outOfBounds";
        }
        switch (field[x][y]) {
        case 0:
            return "unoccupied";
        case 1:
            return "way";
        case 2:
            return "occupied";
        }
        return "unknown";
    }

    // Getter-Methoden
    public int getLevel() {
        return level;
    }

    public Point getStart() {
        return start;
    }

    protected boolean init() {
        File inputWorkbook = new File("karteeins.xls");
        Workbook w;
        try {
            w = Workbook.getWorkbook(inputWorkbook);
            // Get the first sheet
            Sheet sheet = w.getSheet(0);
            // Dimension holen
            dim = Integer.parseInt(sheet.getCell(0, 0).getContents());
            // Startpunkt Endpunkt holen
            int sx = Integer.parseInt(sheet.getCell(0, 1).getContents());
            int sy = Integer.parseInt(sheet.getCell(1, 1).getContents());
            int ex = Integer.parseInt(sheet.getCell(0, 2).getContents());
            int ey = Integer.parseInt(sheet.getCell(1, 2).getContents());
            start = new Point(sx, sy);
            end = new Point(ex, ey);
            // felder initialisieren
            field = new int[dim][dim];
            for (int j = 0; j < dim; j++) {
                for (int i = 0; i < dim; i++) {
                    field[i][j] = Integer.parseInt(sheet.getCell(j, i + 3)
                            .getContents());
                }
            }
        } catch (BiffException e) {
            return false;
        } catch (IOException ex) {
            return false;
        }
        return true;
    }

    // Setter-Methoden
    public void levelUp() {
        level++;
        if (!init()) {
            level = 1;
            init();
        }
    }

    public boolean occupy(int x, int y) {
        if (field[x][y] == 0) {
            field[x][y] = 2;
            return true;
        }
        return false;
    }

}