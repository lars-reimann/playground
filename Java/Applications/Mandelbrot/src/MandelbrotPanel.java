import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public final class MandelbrotPanel extends JPanel {

    private int colorstyle;
    private double maxAbsSquare;
    private int maxIterations;
    private double maxX;
    private double maxY;
    private double minX;
    private double minY;
    private int panelHeight;
    private int panelWidth;

    public MandelbrotPanel() {
        final Dimension screenSize = getToolkit().getScreenSize();

        maxAbsSquare = 4.;
        maxIterations = 50;
        minX = -3;
        minY = -3;
        panelWidth = screenSize.width;
        panelHeight = screenSize.height;
        maxX = 3;
        maxY = 3;
        colorstyle = 3;

        setPreferredSize(new Dimension(panelWidth, panelHeight));
    }

    public Color colorize(final int iterations) {
        switch (colorstyle) {
            case 1:
                return new Color((255 - iterations % 256) % 32 * 8,
                                 (255 - iterations % 256) % 16 * 16,
                                 (255 - iterations % 256) % 8 * 32);
            case 2:
                return new Color(iterations % 256 % 32 * 8,
                                 iterations % 256 % 16 * 16,
                                 iterations % 256 % 8 * 32);
            case 3:
                final int color2 = 255 - 255 * iterations / maxIterations;
                return new Color(color2, color2, color2);
            default:
                if (iterations == maxIterations) {
                    return Color.black;
                } else {
                    return Color.white;
                }
        }
    }

    private int countIterations(double cx, double cy) {
        double x = 0;
        double y = 0;
        double absSquare = 0;
        int count = 1;
        for (; count < maxIterations && absSquare < maxAbsSquare; count++) {
            final double newX = x * x - y * y + cx;
            final double newY = 2 * x * y + cy;
            x = newX;
            y = newY;
            absSquare = x * x + y * y;
        }
        return count;
    }

    public int getColorstyle() {
        return colorstyle;
    }

    public double getMaxAbsSquare() {
        return maxAbsSquare;
    }

    public int getMaxIterations() {
        return maxIterations;
    }

    public double getMaxX() {
        return maxX;
    }

    public double getMaxY() {
        return maxY;
    }

    public double getMinX() {
        return minX;
    }

    public double getMinY() {
        return minY;
    }

    public int getPanelHeight() {
        return panelHeight;
    }

    public int getPanelWidth() {
        return panelWidth;
    }

    @Override
    public void paintComponent(final Graphics g) {
        final double xDistance = panelWidth / (maxX - minX);
        final double yDistance = panelHeight / (maxY - minY);
        for (int i = 0; i < panelWidth; i++) {
            final double cx = minX + i / xDistance;
            for (int j = 0; j < panelHeight; j++) {
                final double cy = minY + j / yDistance;
                final int iterations = countIterations(cx, cy);
                if (iterations == maxIterations) {
                    g.setColor(Color.black);
                } else {
                    g.setColor(colorize(iterations));
                }
                g.drawLine(i, j, i, j);
            }
        }
    }

    public void setColorstyle(int colorstyle) {
        this.colorstyle = colorstyle;
    }

    public void setMaxAbsSquare(double maxAbsSquare) {
        this.maxAbsSquare = maxAbsSquare;
    }

    public void setMaxIterations(int maxIterations) {
        this.maxIterations = maxIterations;
    }

    public void setMaxX(double maxX) {
        this.maxX = maxX;
    }

    public void setMaxY(double maxY) {
        this.maxY = maxY;
    }

    public void setMinX(double minX) {
        this.minX = minX;
    }

    public void setMinY(double minY) {
        this.minY = minY;
    }

    public void setPanelHeight(int panelHeight) {
        this.panelHeight = panelHeight;
    }

    public void setPanelWidth(int panelWidth) {
        this.panelWidth = panelWidth;
    }
}
