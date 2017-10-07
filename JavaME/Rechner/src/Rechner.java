import javax.microedition.midlet.MIDlet;
import javax.microedition.lcdui.Display;

/**
 * Diese Klasse beinhaltet alle Methoden um das Programm zu starten, pausieren und abbbbrechen.
 * 
 * @author Patrick Pruess und Lars Reimann
 * @version 01.07.2011
 */
public class Rechner extends MIDlet
{
  
    /**
     * Diese Methode wird aufgerufen, wenn das Programm gestartet wird.
     */
    public void startApp()
    {
        GrundrechenartenBildschirm bildschirm = new GrundrechenartenBildschirm();
        Display.getDisplay(this).setCurrent(bildschirm);
    }
    
    /**
     * Diese Methode wird aufgerufen, wenn eine Pause folgen soll.
     */
    public void pauseApp()
    {
    }
    
    /**
     * Diese Methode wird aufgerufen, wenn abgebrochen werden soll.
     */
    public void destroyApp(boolean args0)
    {
    }


}