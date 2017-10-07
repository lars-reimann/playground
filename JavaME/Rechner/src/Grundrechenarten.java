/**
 * Hier wird gesagt, was zu rechnen ist, wenn ein bestimmter Button gedrueckt wurde.
 * 
 * @author Patrick Pruess, Lars Reimann
 * @version 01.07.2011
 */
public class Grundrechenarten
{
    /**
     * Hier wird addiert.
     * 
     * @param ersterSummand Das ist der Startwert.
     * @param summanden Diese Werte werden zum Startwert dazu addiert.
     * @param ende Das ist die Stelle des letzten Wertes, der dazu addiert wird.
     * @return Die Summe der einzelnen Werte wird zurueck gegeben.
     */
    public int addiere(int ersterSummand, int[] summanden, int ende)
    {
        int ergebnis = ersterSummand;
        for (int i=0; i<ende; i++)
        {
            ergebnis = ergebnis + summanden[i];
        }
        return ergebnis;
    }
    
    /**
     * Hier wird subtrahiert.
     * 
     * @param minuend Das ist der Starwert.
     * @param subtrahenden Diese Werte werden vom Startwert abgezogen.
     * @param ende Das ist die Stelle des letzten Wertes, der subtrahiert wird.
     * @return Die Differenz der einzelnen Werte wird zurueck gegeben.
     */
    public int subtrahiere(int minuend, int[] subtrahenden, int ende)
    {
        int ergebnis = minuend;
        for (int i=0; i<ende; i++)
        {
            ergebnis = ergebnis - subtrahenden[i];
        }
        return ergebnis;
    }
    
    /**
     * Hier wird multipliziert.
     * 
     * @param multiplikand Das ist der Starwert.
     * @param multiplikatoren Diese Werte werden mit dem Startwert multipliziert.
     * @param ende Das ist die Stelle des letzten Wertes, der multipliziert wird.
     * @return Das Produkt der einzelnen Werte wird zurueck gegeben.
     */
    public int multipliziere(int multiplikand, int[] multiplikatoren, int ende)
    {
        int ergebnis = multiplikand;
        for (int i=0; i<ende; i++)
        {
            ergebnis = ergebnis * multiplikatoren[i];
        }
        return ergebnis;
    }
    
    /**
     * Hier wird dividiert.
     * 
     * @param dividend Das ist der Starwert.
     * @param divisoren Der Startwert wird durch diese Werte geteilt.
     * @param ende Das ist die Stelle des letzten Wertes, durch den der Startwert dividiert wird.
     * @return Der Quotient der einzelnen Werte wird zurueck gegeben.
     */
    public int dividiere(int dividend, int[] divisoren, int ende)
    {
        int ergebnis = dividend;
        for (int i=0; i<ende; i++)
        {
            ergebnis = ergebnis / divisoren[i];
        }
        return ergebnis;
    }
}

