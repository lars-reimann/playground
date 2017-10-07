import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

/**
 * @version
 * @author
 */
public class Start extends MIDlet {

    /**
     * @param unconditional
     */
    protected void destroyApp(boolean unconditional)
            throws MIDletStateChangeException {
    }

    /**
     * 
     */
    protected void pauseApp() {
    }

    /**
     * 
     */
    protected void startApp() throws MIDletStateChangeException {
        QuizDisplay quizDisplay = new QuizDisplay(this);
        Display.getDisplay(this).setCurrent(quizDisplay);
    }
}
