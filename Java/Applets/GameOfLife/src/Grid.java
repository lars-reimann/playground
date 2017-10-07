import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public final class Grid extends Canvas implements Runnable {

    private static final Grid INSTANCE         = new Grid();

    private static final long serialVersionUID = 2892865424401791072L;
    
    
    // -----Instance variables-------------------------------------------------

    private transient boolean           isStopped        = false;
    private final transient byte[][]     cells         = new byte[20][20];
    private transient byte[][]    currentCells         = new byte[20][20];
    private final transient Color lightBlue = new Color(50, 150, 255);

    private Grid() {
        super();
        enableEvents(Event.MOUSE_DOWN);
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 10; j++) {
                cells[i][j] = 0;
            }
        }
    }

    public static Grid getInstance() {
        return INSTANCE;
    }

    public Dimension getPreferredSize() {
        return new Dimension(440, 440);
    }

    public void resetCells() {
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                cells[i][j] = 0;
                repaint();
            }
        }
    }

    public void nextStep() {
        copyArray();
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                final int neighbours = getNeighbours(i, j);
                final int state = currentCells[i][j];
                if (state == 0) {  //Cell is dead
                    if (neighbours == 3) {
                        cells[i][j] = 1;
                    }
                } else if (state == 1) {
                    if (neighbours < 2) {
                        cells[i][j] = 0;
                    } else if (neighbours > 3) {
                        cells[i][j] = 0;
                    }
                }
            }
        }
        repaint();
    }

    public void paint(final Graphics graphics) {
        for (int i = 0; i <= 20; i++) {
            graphics.drawLine(i * 20, 0, i * 20, 400);
            graphics.drawLine(0, i * 20, 400, i * 20);
        }
    }

    public void processMouseEvent(final MouseEvent event) {
        if (event.getID() == Event.MOUSE_DOWN) {
            final int xValue = (int) (event.getX() / 20);
            final int yValue = (int) (event.getY() / 20);
            if (xValue < 20 && yValue < 20) {
                cells[xValue][yValue] = (byte) ((cells[xValue][yValue] + 1) % 2);
                repaint();
            }
        }
        super.processMouseEvent(event);
    }
    
    public void update(final Graphics graphics) {
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                if (cells[i][j] == 0) {
                    graphics.setColor(Color.white);
                    graphics.fillRect(i * 20 + 1, j * 20 + 1, 19, 19);
                }
                if (cells[i][j] == 1) {
                    graphics.setColor(lightBlue);
                    graphics.fillRect(i * 20 + 1, j * 20 + 1, 19, 19);
                }
            }
        }
    }

    public void run() {
        while (!isStopped) {
            copyArray();
            for (int i = 0; i < 20; i++) {
                for (int j = 0; j < 20; j++) {
                    final int neighbours = getNeighbours(i, j);
                    final int state = currentCells[i][j];
                    final boolean willDie = 
                        (state == 1) && (neighbours < 2 || neighbours > 3);
                    final boolean willLive = 
                        (state == 0) && (neighbours == 3) ||
                        (state == 1) && (neighbours == 2 || neighbours == 3);         
                    if (willDie) {
                        cells[i][j] = 0;
                    } else if (willLive) {
                        cells[i][j] = 1;
                    }
                }
            }
            repaint();
            try {
                Thread.sleep(100);
            } catch (InterruptedException exception) {
                // ignore
            }
        }
    }
    
    public void copyArray() {
        final ByteArrayOutputStream bufOutStream = new ByteArrayOutputStream();
        ObjectOutputStream outStream;
        try {
            outStream = new ObjectOutputStream(bufOutStream);
            outStream.writeObject(cells);
            outStream.close();
        } catch (IOException exception) {
            // ignore
        }

        final byte[] buffer = bufOutStream.toByteArray();

        final ByteArrayInputStream bufInStream = new ByteArrayInputStream(buffer);
        ObjectInputStream inStream;
        try {
            inStream = new ObjectInputStream(bufInStream);
            currentCells = (byte[][]) inStream.readObject();
        } catch (IOException execption) {
            // ignore
        } catch (ClassNotFoundException exception) {
            // ignore
        }
    }
    
    public int getNeighbours(final int xValue, final int yValue) {
        int neighboursNo = 0;
        neighboursNo += getState(xValue - 1, yValue - 1);
        neighboursNo += getState(xValue - 1, yValue    );
        neighboursNo += getState(xValue - 1, yValue + 1);
        neighboursNo += getState(xValue,     yValue - 1);
        neighboursNo += getState(xValue,     yValue + 1);
        neighboursNo += getState(xValue + 1, yValue - 1);
        neighboursNo += getState(xValue + 1, yValue    );
        neighboursNo += getState(xValue + 1, yValue + 1);
        return neighboursNo;
    }
    
    public int getState(final int xValue, final int yValue) {
        int state;
        try {
            state = (int) currentCells[xValue][yValue];
        } catch (ArrayIndexOutOfBoundsException exception) {
            state = 0;
        }
        return state;
    }
    
    public void setStopped(final boolean isStopped) {
        this.isStopped = isStopped;
    }
}
