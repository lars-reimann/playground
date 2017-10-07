import javax.microedition.lcdui.Choice;
import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;

/**
 * @version
 * @author
 */
public class QuizDisplay extends Form implements CommandListener {

    /**
     * 
     */
    private Start midlet;

    /**
     * 
     */
    private Frage[] fragen = new Frage[3];

    /**
     * 
     */
    private ChoiceGroup auswahl = new ChoiceGroup("Antworten:",
                                                  Choice.EXCLUSIVE);

    /**
     * 
     */
    private int zeiger = 0;

    /**
     * 
     */
    private int zaehler = 0;

    /**
     * 
     */
    public QuizDisplay(Start Midlet) {
        super("Quiz");

        midlet = Midlet;

        fragen[0] = new Frage(
            "Wie viele Einwohner hat Deutschland?",
            "Ca. 70 Mio.",
            "Ca. 80 Mio.",
            "Ca. 90 Mio.",
            1
        );

        fragen[1] = new Frage(
            "Bei welcher Temperatur hat Wasser die groesste Dichte?",
            "Bei 20 Grad",
            "Bei 0 Grad",
            "Bei 4 Grad",
            2
        );

        fragen[2] = new Frage(
            "Wo traegt ein guter Mensch sprichtwoertlich das Herz?",
            "Am rechten Fleck",
            "Am linken Bein",
            "In der Brust",
            0
        );

        naechsteFrage();

        setCommandListener(this);
        addCommand(new Command("OK", Command.OK, 1));
        addCommand(new Command("Beenden", Command.EXIT, 2));
    }

    /**
     * 
     */
    private void naechsteFrage() {
        auswahl.deleteAll();
        auswahl.append("1. " + fragen[zeiger].zeigeErsteAntwort() + "\n",
                       null);
        auswahl.append("2. " + fragen[zeiger].zeigeZweiteAntwort() + "\n",
                       null);
        auswahl.append("3. " + fragen[zeiger].zeigeDritteAntwort() + "\n",
                       null);

        deleteAll();
        append(fragen[zeiger].zeigeFrage() + "\n");
        append(auswahl);

        zeiger = zeiger + 1;
    }

    /**
     * @param c
     * @param d
     */
    public void commandAction(Command c, Displayable d) {
        if (zeiger == -1 || c.getCommandType() == Command.EXIT) {
            midlet.notifyDestroyed();
        } else {
            int ausgewaehlteAntwort = auswahl.getSelectedIndex();
            int richtigeAntwort = fragen[zeiger - 1].zeigeRichtigeAntwort();

            if (ausgewaehlteAntwort == richtigeAntwort) {
                zaehler = zaehler + 1;
            }

            if (zeiger < 3) {
                naechsteFrage();
            } else if (zeiger == 3) {
                deleteAll();
                append("Sie haben " + zaehler +
                       " von 3 Fragen richtig beantwortet.");
                zeiger = -1;
            }
        }

    }
}
