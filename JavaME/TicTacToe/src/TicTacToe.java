import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

public class TicTacToe extends MIDlet implements CommandListener {

    public TicTacToe() {
        MyGameCanvas gameCanvas = new MyGameCanvas();
        gameCanvas.addCommand(new Command("Exit", Command.EXIT, 2));
        gameCanvas.setCommandListener(this);
        Display.getDisplay(this).setCurrent(gameCanvas);
    }

    public void commandAction(Command command, Displayable displayable) {
        if (command.getCommandType() == Command.EXIT) {
            notifyDestroyed();
        }
    }

    protected void destroyApp(boolean unconditional) throws MIDletStateChangeException {

    }

    protected void pauseApp() {

    }

    protected void startApp() throws MIDletStateChangeException {

    }
}