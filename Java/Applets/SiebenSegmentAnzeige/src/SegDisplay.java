import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;

/**
 * A seven segment display that can be used in any {@code Container}. It
 * behaves like any other dialogue element.
 *
 * @author Lars Reimann
 * @version 20. August 2010
 */
public final class SegDisplay extends Canvas {

    /**
     * The preferred size of this element. Several {@code LayoutManager} use
     * this value to set the size of the frame, so that everything can be
     * displayed correctly.
     */
    private static final Dimension PREFERRED_SIZE = new Dimension(40, 70);

    /**
     * Serial version id.
     */
    private static final long serialVersionUID = -5662197420701981978L;

    /**
     * The digit that is being displayed at the moment.
     */
    private int                     digit;

    /**
     * This array contains the information, whether a digit uses a segment
     * or not. A value of 1 indicates, that the digit needs the segment, a
     * value of 0 means the opposite.
     */
    private final int[][] digits  = {
        {1, 1, 1, 0, 1, 1, 1},         // 0
        {0, 0, 1, 0, 0, 1, 0},         // 1
        {1, 0, 1, 1, 1, 0, 1},         // 2
        {1, 0, 1, 1, 0, 1, 1},         // 3
        {0, 1, 1, 1, 0, 1, 0},         // 4
        {1, 1, 0, 1, 0, 1, 1},         // 5
        {1, 1, 0, 1, 1, 1, 1},         // 6
        {1, 0, 1, 0, 0, 1, 0},         // 7
        {1, 1, 1, 1, 1, 1, 1},         // 8
        {1, 1, 1, 1, 0, 1, 1}          // 9
    };

    /**
     * <p>This array contains x-coordinates of points. The points are used to
     * paint the polygon for each segment.</p>
     * <p>The y-coordinates are represented by the other array yPoints. The
     * positions within the array are the same. This means, that xPoints[0][0]
     * and yPoints[0][0] form together one point of the polygon.</p>
     */
    private final int[][] xPoints = {
        { 5, 10, 30, 35, 30, 10},      // Segment 0 (top)
        { 0,  5, 10, 10,  5,  0},      // Segment 1 (top/left)
        {30, 35, 40, 40, 35, 30},      // Segment 2 (top/right)
        { 5, 10, 30, 35, 30, 10},      // Segment 3 (middle)
        { 0,  5, 10, 10,  5,  0},      // Segment 4 (bottom/left)
        {30, 35, 40, 40, 35, 30},      // Segment 5 (bottom/right)
        { 5, 10, 30, 35, 30, 10},      // Segment 6 (bottom)
    };

    /**
     * <p>This array contains y-coordinates of points. The points are used to
     * paint the polygon for each segment.</p>
     * <p>The x-coordinates are represented by the other array xPoints. The
     * positions within the arrays are the same. This means, that xPoints[0][0]
     * and yPoints[0][0] form together one point of the polygon.</p>
     */
    private final int[][] yPoints = {
        { 5,  0,  0,  5, 10, 10},      // Segment 0 (top)
        {10,  5, 10, 30, 35, 30},      // Segment 1 (top/left)
        {10,  5, 10, 30, 35, 30},      // Segment 2 (top/right)
        {35, 30, 30, 35, 40, 40},      // Segment 3 (middle)
        {40, 35, 40, 60, 65, 60},      // Segment 4 (bottom/left)
        {40, 35, 40, 60, 65, 60},      // Segment 5 (bottom/right)
        {65, 60, 60, 65, 70, 70}       // Segment 6 (bottom)
    };

    /**
     * Constructs a new {@code SegDisplay}, which initially display the
     * given digit.
     *
     * @param digit the initial value that should be displayed.
     */
    public SegDisplay(int digit) {
        super();
        this.digit = digit % 10;
    }

    /**
     * Can be used to get the value, which is being displayed at the moment.
     *
     * @return the currently displayed digit.
     */
    public int getDigit() {
        return digit;
    }

    /**
     * Overrides the method of {@Component} to tell the {@code LayoutManager}
     * the preferred size of this element. Several managers adjust the size of
     * the frame according to this value.
     *
     * @return the preferred size of this element.
     */
    public Dimension getPreferredSize() {
        return PREFERRED_SIZE;
    }

    /**
     * Paints the selected digit.
     *
     * @param graphics the {@code Graphics} object of this component.
     */
    public void paint(Graphics graphics) {
        for (int j = 0; j < xPoints.length; j++) {
            if (digits[digit][j] == 1) {
                graphics.fillPolygon(xPoints[j],
                                     yPoints[j],
                                     xPoints.length - 1);
            }
        }
    }

    /**
     * Sets the variable digit to the new value and repaints the element.
     * After the repainting is done, the new value will be displayed.
     *
     * @param digit the value, which should be displayed.
     */
    public void setDigit(int digit) {
        this.digit = digit % 10;
        repaint();
    }
}
