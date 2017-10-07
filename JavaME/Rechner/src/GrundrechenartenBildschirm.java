import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.TextField;

/**
 * In dieser Klasse wird beschrieben, wie der Bildschirm dargestellt werden soll.
 * 
 * @version 01.07.2011
 * @author Patrick Pruess, Lars Reimann
 */
public class GrundrechenartenBildschirm extends Form implements CommandListener {

    /**
     * Hier werden die anderen/alle Werte bis auf den den Startwert gespeichert.
     * Bsp.: Bei einer Summe werden hier die 2., 3. ... Summanden abgespeichert.
     */
    private int[] andereWerte;
    
    /**
     * Hier werden die Zahlen,die spaeter berechnet werden sollen, eingetippt.
     */
    private TextField eingabe;
    
    /**
     * Hier sind die Methoden addiere(), subtrahiere() ... definiert.
     * Damit wird spaeter alles berechnet.
     */
    private Grundrechenarten grundrechenarten;
    
    /**
     * Hier wird der erste eingegebene Wert gespeichert.
     * Bei einer Summe ist dies zum Beispiel der 1. Summand.
     */
    private int startwert;
    
    /**
     * Hier werden die Werte fuer das Array gespeichert.
     */
    private int zeiger;
    
    /**
     * Hier wird das Menuefeld fuer die Rechenoperationen erstellt.
     * Zudem wird das Eingabefeld erstellt.
     * Alle Variabeln werden initialisiert.
     */
    public GrundrechenartenBildschirm()
    {
        // Aufruf des Konstruktors der Vaterklasse
        super("Grundrechenarten");
        
        andereWerte = new int [100];
        startwert = Integer.MIN_VALUE;
        zeiger = 0;
        
        eingabe = new TextField("Eingabe","",10,TextField.DECIMAL);
        append(eingabe);
       
        grundrechenarten = new Grundrechenarten();
        
        addCommand( new Command ("fuegeHinzu",Command.OK, 1));
        addCommand( new Command ("+",Command.OK,2));
        addCommand( new Command ("-",Command.OK,2));
        addCommand( new Command ("*",Command.OK,2));
        addCommand( new Command ("/",Command.OK,2));
        setCommandListener(this);
    }
    
    /**
     * Diese Methode wird aufgerufen, wenn eine Taste gedrueckt wurde.
     * Wenn die erste Taste gedrueckt wurde, wird die Zahl aus dem Eingabefeld hinzugefuegt.
     * Wenn eine andere Taste gedrueckt wurde, werden die Zahlen berechnet und alles zurueckgesetzt.
     * 
     * @param c Dies gibt an welche Taste gedrueckt wurde.  
     * @param d Das gibt an in welchem Bildschirm die Taste gedrueckt wurde.
     */
    public void commandAction(Command c, Displayable d)
    {
        String label = c.getLabel();
        if("fuegeHinzu".equals(label))
        {
            fuegeHinzu();
        }
        else
        {
            berechne(label.charAt(0));
            zuruecksetzen();
        }
    }
    
    /**
     * Hier wird der Zeiger auf Null gesetzt. Das heisst, dass die Werte im Array spaeter ueberschrieben werden.
     * Startwert wird zurueckgesetzt, so dass dieser wieder ueberschrieben werden kann.
     * In der Eingabe steht nun nichts mehr drin.
     */
    private void zuruecksetzen()
    {
        zeiger = 0;
        startwert = Integer.MIN_VALUE;
        eingabe.setString("");
    }
    
    /**
     * Hier wird gesagt, was zu machen ist, wenn ein bestimmtes Rechenzeichen ausgewaehlt wurde.
     * Bsp.: Wenn ein Plus gedrueckt wird, dann wird aus Grundrechenarten die Methode addiere aufgerufen.
     * 
     * @param operator Das gibt an, welche Operation auszufuehren ist.
     */
    private void berechne(char operator)
    {
        if (operator == '+')
        {
            append("= " + grundrechenarten.addiere(startwert, andereWerte, zeiger) + "\n");
        }
        
        else if (operator == '-')
        {
            append("= " + grundrechenarten.subtrahiere(startwert, andereWerte, zeiger)+"\n");
        }
        
        else if (operator == '*')
        {
            append("= " + grundrechenarten.multipliziere(startwert, andereWerte, zeiger)+"\n");
        }
        
        else if (operator == '/')
        {
            append("= " + grundrechenarten.dividiere(startwert, andereWerte, zeiger)+"\n");
        }
    }
    
    /**
     * Hier wird der Wert aus dem Eingabefeld hinzu gefuegt.
     * Der eingegebene Startwert wird zurueckgesetzt, so dass der Wert ueberschrieben werden kann.
     */
    private void fuegeHinzu()
    {
        if (startwert == Integer.MIN_VALUE)
        {
            startwert = Integer.parseInt(eingabe.getString());
            append(startwert + " ");
        }
        else 
        {
            andereWerte[zeiger] = Integer.parseInt(eingabe.getString());
            append(andereWerte[zeiger] + " ");
            zeiger++;
        }
        
    }

}