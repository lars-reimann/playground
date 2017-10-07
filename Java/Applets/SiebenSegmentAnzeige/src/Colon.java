import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;

/**
 * Two rectangles, which look like a colon. This is a self-made component that
 * can be used in any {@code Container}.
 *
 * @author Lars Reimann
 * @version 20. August 2010
 */
public final class Colon
extends Canvas {

    // -----Class variables----------------------------------------------------

    /**
     * The preferred size of this element. Several {@code LayoutManager} use
     * this value to set the size of the frame, so that everything can be
     * displayed correctly.
     */
    private static final Dimension PREFERRED_SIZE = new Dimension(5, 70);

    /**
     * Serial version id.
     */
    private static final long serialVersionUID = -8587922069919641040L;


    // -----Instance methods---------------------------------------------------

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
     * Paints two little rectangles. Together they look like a colon.
     *
     * @param graphics the {@code Graphics} object of this component.
     */
    public void paint(final Graphics graphics) {
        graphics.fillRect(0, 25, 5, 5);
        graphics.fillRect(0, 40, 5, 5);
    }
}
