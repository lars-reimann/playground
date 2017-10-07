import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public final class SierpinskiPanel extends JPanel {

    private double maxX;
    private double maxY;
    private double minX;
    private double minY;
    private int panelHeight;
    private int panelWidth;

    public SierpinskiPanel() {
        final Dimension screenSize = getToolkit().getScreenSize();

        minX = 0;
        minY = 0;
        panelWidth = screenSize.width;
        panelHeight = screenSize.height;
        maxX = 1000;
        maxY = 1000;

        setPreferredSize(new Dimension(panelWidth, panelHeight));
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
            final int cx = (int) Math.round(minX + i / xDistance);
            for (int j = 0; j < panelHeight; j++) {
                final int cy = (int) Math.round(minY + j / yDistance);
                if ((cx & cy) == 0) {
                    g.setColor(Color.black);
                } else {
                    g.setColor(Color.white);
                }
                g.drawLine(i, j, i, j);
            }
        }
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
