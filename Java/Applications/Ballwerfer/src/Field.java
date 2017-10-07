import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

public class Field extends JPanel implements Runnable {

    private Graphics dbGraphics;
    private Image dbImage;
    private int x = 0;
    private int y = 0;
    private double velocity = 0;
    private double acceleration = 0;
    private double zoom = 0;
    private int sleeptime = 0;
    private final MyFrame myFrame;
    private boolean isStopped = true;
    
    public Field(final MyFrame myFrame) {
        this.myFrame = myFrame;
        setSize(1000, 400);
    }
    
    public void run() {
        x = 0;
        y = 0;
        velocity = myFrame.getVelocity();
        acceleration = myFrame.getAcceleration();
        zoom = myFrame.getZoom();
        sleeptime = myFrame.getSleeptime();
        isStopped = false;
        for (double i = 0; x < getWidth() - 15 && y < getHeight() - 15 && !isStopped; i++) {
            x = (int) Math.round(i * velocity * zoom);
            y = (int) Math.round(0.5 * acceleration * i * i * zoom);
            update(getGraphics());
            try {
                Thread.sleep(sleeptime);
            } catch (InterruptedException exception) {
                // ignoriere
            }
        }
        isStopped = true;
    }
    
    public boolean isStopped() {
        return isStopped;
    }
    
    public void setStopped(final boolean isStopped) {
        this.isStopped = isStopped;
    }
    
    public void reset() {
        getGraphics().clearRect(0, 0, getWidth(), getHeight());
        dbGraphics.clearRect(0, 0, getWidth(), getHeight());
    }

    /**
     * {@inheritDoc}
     */
    public void paint(final Graphics graphics) {
        graphics.fillOval(x, y, 10, 10);
    }

    /**
     * {@inheritDoc}
     */
    public void update(final Graphics graphics) {
        if (dbImage == null) {
            dbImage = createImage(getWidth(), getHeight());
        }
        dbGraphics = dbImage.getGraphics();
        paint(dbGraphics);
        graphics.drawImage(dbImage, 0, 0, this);
    }
}
