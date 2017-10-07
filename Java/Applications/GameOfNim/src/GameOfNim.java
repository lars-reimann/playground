import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;

public class GameOfNim extends Applet {

    /**
     * Serial Version id.
     */
    private static final long  serialVersionUID = -588137753692379770L;
    public static final int[] ROWS             = new int[5];
    public static int changedRow;
    public static int player = 0;
    
    public void start() {
        addMouseListener(new MyMouseListener());
        fillArray();
    }
    
    public void fillArray() {
        for (int i = 0; i < ROWS.length; i++) {
            int random = (int) (Math.random() * 8) - 1;
            ROWS[i] = random;
        }    
    }
    
    public static boolean isWon() {
        int circles = 0;
        for (int i = 0; i < ROWS.length; i++) {
            circles += ROWS[i];
        }
        return (circles == 0) ? true : false;
    }
    
    public void update(final Graphics graphics) {
        graphics.setColor(Color.white);
        switch (changedRow) {
            case 0 :
                updateCircles(graphics, 0);
                break;
            case 1 :
                updateCircles(graphics, 1);
                break;
            case 2 :
                updateCircles(graphics, 2);
                break;
            case 3 :
                updateCircles(graphics, 3);
                break;
            case 4 :
                updateCircles(graphics, 4);
                break;
            default :
                break;
        }
    }
    
    public void paint(final Graphics graphics) {
        
        // First row
        graphics.setColor(new Color(50, 120, 120));
        paintCircles(graphics, 0);    
        
        // Second row
        graphics.setColor(Color.blue);
        paintCircles(graphics, 1);
        
        // Third row
        graphics.setColor(Color.gray);
        paintCircles(graphics, 2);
        
        // Forth row
        graphics.setColor(new Color(150, 0, 150));
        paintCircles(graphics, 3);
        
        // Fifth row
        graphics.setColor(Color.darkGray);
        paintCircles(graphics, 4);
    }
    
    private void paintCircles(final Graphics graphics, final int row) {
        int circles = ROWS[row];
        for (int i = 0; i < circles; i++) {
            graphics.fillArc(i * 40, row * 40, 40, 40, 0, 360);
        }
    }
    
    private void updateCircles(final Graphics graphics, final int row) {
        int circles = ROWS[row];
        for (int i = circles; i <= ROWS.length; i++) {
            graphics.fillArc(i * 40, row * 40, 40, 40, 0, 360);
        }
    }
}
