/**
 * <p>
 * Diese Klasse beinhaltet alle Methoden, die zum L&ouml;sen der eingegebenen
 * Gleichungen n&ouml;tig sind.
 * </p>
 * <p>
 * Es gibt jeweils eine Methode, die quadratische Gleichungen mit einer
 * Unbekannten, lineare Gleichungen mit einer Unbekannten oder "konstante"
 * Gleichungen ohne Unbekannte l&ouml;sen kann. Au&szlig;erdem gibt es noch
 * einen Methode zum K&uuml;rzen von Br&uuml;chen.
 * </p>
 * 
 * @version 01. Juli 2011
 * @author Lars Reimann
 */
public final class Solver {

    /**
     * <p>
     * Eine Hilfsmethode zum Berechnen der L&ouml;sungen einer quadratischen
     * Gleichung.
     * </p>
     * <p>
     * Da sich die L&ouml;sung einer quadratischen Gleichung mit zwei komplexen
     * L&ouml;sungen nur um das Negieren der Diskriminante und das
     * Hinzuf&uuml;gen der imagin&auml;ren Einheit i am Ende der L&ouml;sung von
     * der L&ouml;sung einer solchen Gleichung mit zwei rellen L&ouml;sungen
     * unterscheidet, ist die Berechnung beziehungsweise Zusammensetzung des
     * L&ouml;sung aus den einzelnen Komponenten in diese Methode ausgelagert
     * worden, um den Wartungsaufwand zu verringern und die Lesbarkeit des
     * Programmes zu verbessern. Im Grunde handelt es sich nur um einige
     * Fallunterscheidungen, um f&uuml;r eine m&ouml;glichst lesbare und
     * verst&auml;ndliche Ausgabe der L&ouml;sung zu sorgen.
     * </p>
     * 
     * @param a
     *            Der Wert f&uuml;r die Variable a der Gleichung ax^2 + bx + c =
     *            0.
     * @param b
     *            Der Wert f&uuml;r die Variable b der Gleichung ax^2 + bx + c =
     *            0.
     * @param d
     *            Die Diskriminate, also der Radikand der L&ouml;sungsformel
     *            f&uuml;r quadratische Gleichungen.
     * @return Einen Teil des Strings der L&ouml;sungsmenge der Gleichung.
     */
    private String computeSolution(final int a, final int b, final int d) {
        String solution = "";

        // Erster Teil (evtl. Realteil)
        if (b != 0 && b % (2 * a) == 0) {
            solution += -b / (2 * a) + " \u00B1 ";
        } else {
            solution += simplify(-b, 2 * a) + " \u00B1 ";
        }

        // Zweiter Teil (evtl. Imaginaerteil)
        if ((int) (Math.sqrt(d)) == Math.sqrt(d)) {
            if (Math.sqrt(d) % (2 * a) == 0) {
                solution += (int) (Math.sqrt(d) / (2 * Math.sqrt(a * a)));
            } else {
                solution += simplify((int) Math.sqrt(d),
                                     2 * (int) Math.sqrt(a * a));
            }
        } else {
            if (d % (4 * a * a) == 0) {
                solution += "sqrt(" + d / (4 * a * a) + ")";
            } else {
                final int gcd = gcd(d, 4 * a * a);
                if ((int) (Math.sqrt(gcd)) == Math.sqrt(gcd)) {
                    solution += "sqrt(" + d / gcd + ") / " +
                                (int) Math.sqrt(4 * a * a / gcd);
                } else {
                    for (int i = (int) Math.sqrt(gcd); i >= 1; i--) {
                        if (d % (i * i) == 0 && (2 * a) % i == 0) {
                            solution += "sqrt(" + d / (i * i) + ") / " + 2 *
                                        (int) Math.sqrt(a * a) / i;
                            break;
                        }
                    }
                }
            }
        }
        return solution;
    }

    /**
     * <p>
     * Berechnet den ggT zweier Zahlen.
     * </p>
     * <p>
     * Berechnet unter Verwendung des modernen Euklidischen Algorithmus, welcher
     * auf der Division mit Rest beruht, den ggT des Nenners und des
     * Z&auml;hlers zweier Zahlen. Hier wird dieses Ergebnis schlie&szlig;lich
     * beim K&uuml;rzen dieses Bruches ausgenutzt.
     * </p>
     * 
     * @param num
     *            Der Z&auml;hler des Bruches.
     * @param den
     *            Den Nenner des Bruches.
     * @return Den gr&ouml;&szlig;ten gemeinsamen Teiler des Nenners und des
     *         Z&auml;hlers.
     */
    private int gcd(final int num, final int den) {
        int r = -1;
        int a = num;
        int b = den;
        while (r != 0) {
            r = a % b;
            a = b;
            b = r;
        }
        return a;
    }

    /**
     * <p>
     * Diese Methode ist f&uuml;r das K&uuml;rzen eines Bruches zust&auml;ndig.
     * </p>
     * <p>
     * Dazu wird erst unter Verwendung des modernen Euklidischen Algorithmus,
     * welcher auf der Division mit Rest beruht, der ggT des Z&auml;hlers und
     * des Nenners berechnet. Wenn das K&uuml;rzen des Bruches zur Verbesserung
     * der Lesbarkeit des Bruches beitr&auml;gt, was bei Fliesskommazahlen im
     * Z&auml;hler oder Nenner nicht zwangsl&auml;ufig gegeben sein muss, wird
     * der Bruch gek&uuml;rzt zur&uuml;ckgeben. Sonst wird die unver&auml;nderte
     * Version zur&uuml;ckgegeben.
     * </p>
     * 
     * @param num
     *            Der Z&auml;hler des Bruches.
     * @param den
     *            Der Nenner des Bruches.
     * @return Die vollst&auml;ndig gek&uuml;rzte Form des Bruches, falls diese
     *         besser ist; sonst die urspr&uuml;ngliche Form.
     */
    private String simplify(final int num, final int den) {

        // Sonderfaelle
        if (num == 0) {
            return "" + 0;
        } else if (den == 1) {
            return "" + num;
        } else if (den == -1) {
            return "" + -num;
        }

        // Berechnung des ggT
        final int gcd = gcd(num, den);

        // Ausgabe
        final int newNum = num / gcd;
        final int newDen = den / gcd;
        if (newDen == 1) {
            return "" + newNum;
        } else if (newDen == -1) {
            return "" + -newNum;
        } else if (newDen >= 0) {
            return newNum + " / " + newDen;
        } else {
            return -newNum + " / " + -newDen;
        }
    }

    /**
     * <p>
     * L&ouml;st eine "konstante" Gleichung ohne Unbekannte in der Form a = 0.
     * </p>
     * <p>
     * F&uuml;r den Fall, dass a den Wert 0 besitzt, hat die Gleichung unendlich
     * viele L&ouml;sungen, sonst keine einzige. Dies wird dann entsprechend von
     * der Methode zur&uuml;ckgegeben.
     * </p>
     * 
     * @param a
     *            Der Wert f&uuml;r die Variable a der Gleichung a = 0.
     * @return Die L&ouml;sungsmenge der Gleichung.
     */
    private String solveConstant(final int a) {
        if (a == 0) {
            return "L = {x | x Element aus C}";
        } else {
            return "L = {}";
        }
    }

    /**
     * <p>
     * L&ouml;st eine lineare Gleichung mit einer Unbekannten, welche die Form
     * ax + b = 0 besitzt.
     * </p>
     * <p>
     * Falls a = 0 ist, wird die Methode solveConstant() aufgerufen, welche
     * Gleichungen ohne Unbekannte l&ouml;sen kann. Sonst wird die
     * L&ouml;sungsmenge der Gleichung wie folgt bestimmt: Zuerst subtrahiert
     * man b, anschlie&szlig;end wird durch a geteilt. Die L&ouml;sung der
     * Gleichung ist also x = -a / b. Dieser Bruch wird schlie&szlig;lich noch
     * gek&uuml;rzt und im Anschluss wird die L&ouml;sungsmenge
     * zur&uuml;ckgegeben.
     * </p>
     * 
     * @param a
     *            Der Wert f&uuml;r die Variable a der Gleichung ax + b = 0.
     * @param b
     *            Der Wert f&uuml;r die Variable b der Gleichung ax + b = 0.
     * @return Die L&ouml;sungsmenge der Gleichung.
     */
    private String solveLinear(final int a, final int b) {
        if (a == 0) {
            return solveConstant(b);
        } else {
            if (b == 0) {
                return "L = {0}";
            } else {
                return "L = {" + simplify(-b, a) + "}";
            }
        }
    }

    /**
     * <p>
     * L&ouml;st eine quadratische Gleichung mit einer Unbekannten in der Form
     * ax^2 + bx + c = 0.
     * </p>
     * <p>
     * F&uuml;r den Fall, dass a den Wert 0 besitzt, wird die Methode
     * solveLinear() aufgerufen, welche die Funktionalit&auml;t zum L&ouml;sen
     * linearer Gleichungen besitzt. Sonst wird erst einmal die Diskriminante
     * b^2 - 4ac berechnet, welche Auskunft &uuml;ber die L&ouml;sungen der
     * Gleichung gibt. Nach der abc-Formel gilt f&uuml;r die L&ouml;sung einer
     * quadratischen Gleichung x = (-b +/- sqrt(b^2 - 4ac)) / 2a. Die
     * Diskriminante entspricht also dem Radikand in der abc-Formel.
     * <p>
     * Ist die Diskriminante kleiner als 0, so steht etwas Negatives unter der
     * Wurzel, was zeigt dass es keine reellen L&ouml;sungen geben kann. Es gibt
     * jedoch komplexe L&ouml;sungen, welche dann auch zur&uuml;ckgegeben
     * werden. Ist die Diskriminante gleich 0, so gibt es genau eine
     * L&ouml;sung, n&auml;mlich -b / 2a, da das Addieren oder Subtrahieren von
     * sqrt(0) den Wert nicht ver&auml;ndert. Ist die Diskriminante positiv, so
     * gibt es zwei verschiedene L&ouml;sungen, welche schlie&szlig;lich auch
     * berechnet und ausgegeben werden.
     * </p>
     * 
     * @param a
     *            Der Wert f&uuml;r die Variable a der Gleichung ax^2 + bx + c =
     *            0.
     * @param b
     *            Der Wert f&uuml;r die Variable b der Gleichung ax^2 + bx + c =
     *            0.
     * @param c
     *            Der Wert f&uuml;r die Variable c der Gleichung ax^2 + bx + c =
     *            0.
     * @return Die L&ouml;sungsmenge der Gleichung.
     */
    public String solveQuadratic(final int a, final int b, final int c) {
        if (a == 0) {
            return solveLinear(b, c);
        } else {
            final int d = b * b - 4 * a * c;

            // Zwei komplexe Loesungen
            if (d < 0) {
                return "Keine reellen L\u00F6sungen!\nL = {" +
                       computeSolution(a, b, -d) + " i}";
            }

            // Eine reelle Loesung
            else if (d == 0) {
                if (b == 0) {
                    return "L = {0}";
                } else {
                    return "L = {" + simplify(-b, 2 * a) + "}";
                }
            }

            // Zwei reelle Loesungen
            else {
                return "L = {" + computeSolution(a, b, d) + "}";
            }
        }
    }
}
