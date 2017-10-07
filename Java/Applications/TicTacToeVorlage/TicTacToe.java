import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;

/**
 * @author Lars Reimann
 * @version 19. Februar 2011
 */
public class TicTacToe extends MIDlet {

    public TicTacToe() {
        TicTacToeCanvas gameCanvas = new TicTacToeCanvas();
        Display.getDisplay(this).setCurrent(gameCanvas);
    }

    protected void destroyApp(boolean unconditional) {

    }

    protected void pauseApp() {

    }

    protected void startApp() {

    }
}
