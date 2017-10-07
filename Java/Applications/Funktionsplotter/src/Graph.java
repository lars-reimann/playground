import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;


public class Graph extends Canvas {
    
    private       double    xExtension = 250;
    private       double    yExtension = 50;
    private       int       xShift     = 0;
    private       int       yShift     = 0;
    private final int       width;
    private final int       height;
    private       Graphics  dbGraphics;  
    private       Image     dbImage;   
    private final Dimension preferredSize;
    
    public Graph() {
        width = getToolkit().getScreenSize().width - 20;
        height = getToolkit().getScreenSize().height - 20;
        preferredSize = new Dimension(width, height);
    }
    
    public Dimension getPreferredSize() {
        return preferredSize;
    }
    
    public void paint(final Graphics graphics) {

        graphics.setColor(Color.lightGray);
        for (int i = 1; i < width / 50; i++) {
            graphics.drawLine(width / 2 + i * 50, 0, width / 2 + i * 50, height);
            graphics.drawLine(width / 2 - i * 50, 0, width / 2 - i * 50, height);
            graphics.drawLine(0, height / 2 + i * 50, width, height / 2 + i * 50);
            graphics.drawLine(0, height / 2 - i * 50, width, height / 2 - i * 50);
        }
        graphics.setColor(Color.black);
        graphics.drawLine(0, height / 2, width, height / 2);
        graphics.drawLine(width / 2, 0, width / 2, height);
        graphics.setColor(Color.red);
        for (int i = 0; i < width; i++) {
            int y = yShift + (int) Math.round(height / 2 - yExtension * Math.tan((double) (i + xShift) / xExtension));
            graphics.drawLine(width / 2 + i, y, width / 2 + i, y);
            y = yShift + (int) Math.round(height / 2 - yExtension * Math.tan((double) (-i + xShift) / xExtension));
            graphics.drawLine(width / 2 - i, y, width / 2 - i, y);
        }
    }
    
    public void setXExtension(final double xExtension) {
        this.xExtension = xExtension;
    }
    
    public void setYExtension(final double yExtension) {
        this.yExtension = yExtension;
    }
    
    public void update(final Graphics graphics) {
        if (dbImage == null) {
            dbImage = createImage(getSize().width, getSize().height);
            dbGraphics = dbImage.getGraphics();
        }
        dbGraphics.setColor(getBackground());
        dbGraphics.clearRect(0, 0, getSize().width, getSize().height);
        dbGraphics.setColor(getForeground());
        paint(dbGraphics);
        graphics.drawImage(dbImage, 0, 0, this);
    }
}
