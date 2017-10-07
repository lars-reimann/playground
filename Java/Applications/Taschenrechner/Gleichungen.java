
/**
 * Mit diesem Programm können lineare und quadratische Gleichungen
 * gelöst werden.
 * 
 * @Lars Reimann 
 * @15.02.2010
 */
public class Gleichungen
{
    /**
    * Form : ax + b = c
    */
    public static void LineareGleichungen (double a, double b, double c)
    {
        double x = 0;
        
        if (a != 0)
        {
            x = (c - b) / a;
            System.out.println(a + "x + " + b + " = " + c); 
            System.out.println("---------------");
            System.out.println("Beide Seiten -" + b + ":");
            System.out.println(a + "x = " + (c - b));
            System.out.println("---------------");
            System.out.println("Beide Seiten /" + a + ":");
            System.out.println("x = " + x); 
        }
        else 
        {
            if (b == c) System.out.println("Jede reelle Zahl erfüllt diese Gleichung!");
            else System.out.println("Es gibt keine reelle Lösung!");
        }
    }
    
    /**
    * Form: ax² + bx + c = d
    */
    public static void QuadratischeGleichungen (double a, double b, double c, double d)
    {
        double x1 = 0;
        double x2 = 0;
        
        c = c - d;        
        if ((Math.pow(b,2) - (4 * a * c)) > 0)
        {
            x1 = (b + Math.sqrt(Math.pow(b,2) - (4 * a * c))) / (2 * a);
            System.out.println("x1 = " + x1);
            x2 = (b - Math.sqrt(Math.pow(b,2) - (4 * a * c))) / (2 * a);
            System.out.println("x2 = " + x2);
        }       
        else if ((Math.pow(b,2) - (4 * a * c)) == 0)
        {
            x1 = b / (2 * a);
            System.out.println("x = " + x1);
        }        
        else System.out.println("Es gibt keine reelle Lösung");
    }
}
