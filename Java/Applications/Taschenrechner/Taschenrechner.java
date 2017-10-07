/**
 * Dies ist ein einfacher Taschenrechner, der addieren, subtrahieren,
 * multiplizieren und dividieren kann. Außerdem können Potenzen berechnet#
 * werden.
 *
 * @Lars Reimann
 * @05.02.2010
 */
public class Taschenrechner
{
     /** 
      * Operator: "+", "-", "*", "/", "^"
      */
     public static void Grundrechenarten (double ErsteZahl, String Operator, double ZweiteZahl)
    {
        double Ergebnis = 0;
        
        if (Operator == "+")
        {
            Ergebnis = ErsteZahl + ZweiteZahl;
            if (ZweiteZahl > 0) System.out.println(ErsteZahl + " + " + ZweiteZahl + " = " + Ergebnis);
            else System.out.println(ErsteZahl + " + (" + ZweiteZahl + ") = " + Ergebnis);
        }       
        if (Operator == "-")
        {
            Ergebnis = ErsteZahl - ZweiteZahl;
            if (ZweiteZahl > 0) System.out.println(ErsteZahl + " - " + ZweiteZahl + " = " + Ergebnis);
            else System.out.println(ErsteZahl + " - (" + ZweiteZahl + ") = " + Ergebnis);
        }       
        if (Operator == "*")
        {
            Ergebnis = ErsteZahl * ZweiteZahl;
            if (ZweiteZahl > 0) System.out.println(ErsteZahl + " * " + ZweiteZahl + " = " + Ergebnis);
            else System.out.println(ErsteZahl + " * (" + ZweiteZahl + ") = " + Ergebnis);
        }       
        if (Operator == "/")
        {
            Ergebnis = ErsteZahl / ZweiteZahl;
            if (ZweiteZahl > 0) System.out.println(ErsteZahl + " / " + ZweiteZahl + " = " + Ergebnis);
            else System.out.println(ErsteZahl + " / (" + ZweiteZahl + ") = " + Ergebnis);
        }        
        if (Operator == "^")
        {
            Ergebnis = Math.pow(ErsteZahl, ZweiteZahl);
            if (ZweiteZahl > 0) System.out.println(ErsteZahl + " ^ " + ZweiteZahl + " = " + Ergebnis);
            else System.out.println(ErsteZahl + " ^ (" + ZweiteZahl + ") = " + Ergebnis);
        }     
    }    
}