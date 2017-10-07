/**
 * @version
 * @author
 */
public class Frage {

    /**
     * 
     */
    private String frage;

    /**
     * 
     */
    private String ersteAntwort;

    /**
     * 
     */
    private String zweiteAntwort;

    /**
     * 
     */
    private String dritteAntwort;

    /**
     * 
     */
    private int richtigeAntwort;

    /**
     * @param Frage
     * @param ErsteAntwort
     * @param ZweiteAntwort
     * @param DritteAntwort
     * @param RichtigeAntwort
     */
    public Frage(String Frage, String ErsteAntwort, String ZweiteAntwort,
                 String DritteAntwort, int RichtigeAntwort) {
        frage = Frage;
        ersteAntwort = ErsteAntwort;
        zweiteAntwort = ZweiteAntwort;
        dritteAntwort = DritteAntwort;
        richtigeAntwort = RichtigeAntwort;
    }

    /**
     * @return
     */
    public String zeigeFrage() {
        return frage;
    }

    /**
     * @return
     */
    public String zeigeZweiteAntwort() {
        return zweiteAntwort;
    }

    /**
     * @return
     */
    public String zeigeErsteAntwort() {
        return ersteAntwort;
    }

    /**
     * @return
     */
    public String zeigeDritteAntwort() {
        return dritteAntwort;
    }

    /**
     * @return
     */
    public int zeigeRichtigeAntwort() {
        return richtigeAntwort;
    }
}
