
/**
 * Dieses Programm f�hrt die Polynomdivision aus.
 * 
 * @Lars Reimann 
 * @12.03.2010
 */
public class Polynomdivision
{        
    /**
     * Form: (a5x^5 + a4x^4 + a3x� + a2x� + a1x + a0) / (b2x� + b1x + b0)
     */
    public static void Polynomdivision (double a5, double a4, double a3, double a2, double a1, double a0, double b2, double b1, double b0)
    {
        if (b2 != 0)
        {
            System.out.println("(" + a5 + "x^5 + " + a4 + "x^4 + " + a3 + "x� + " + a2 + "x� + " + a1 + "x + " + a0 + ") / (" + b2 + "x� + " + b1 + "x + " + b0 + ") = ");
            if (a5 != 0) 
            {
                System.out.print(a5 / b2 + "x� + ");               
                a4 = a4 - ((a5 / b2) * b1);               
                a3 = a3 - ((a5 / b2) * b0);
            }            
            if (a4 != 0)
            {
                System.out.print(a4 / b2 + "x� + ");
                a3 = a3 - ((a4 / b2) * b1);               
                a2 = a2 - ((a4 / b2) * b0);
            }            
            if (a3 != 0)
            {
                System.out.print(a3 / b2 + "x + ");
                a2 = a2 - ((a3 / b2) * b1);
                a1 = a1 - ((a3 / b2) * b0); 
            }            
            if (a2 != 0)
            {
                System.out.print(a2 / b2);
                a1 = a1 - ((a2 / b2) * b1);
                a0 = a0 - ((a2 / b2) * b0);               
            }            
            if (a1 != 0) 
            {
                System.out.print (" Rest: " + a1 + "x");
                if (a0 != 0) System.out.print (" + " + a0);
            }           
            if (a1 == 0 && a0 != 0) System.out.print (" Rest: " + a0); 
            System.out.println("");
        }        
        else if (b2 == 0 && b1 != 0)
        {
            System.out.println("(" + a5 + "x^5 + " + a4 + "x^4 + " + a3 + "x� + " + a2 + "x� + " + a1 + "x + " + a0 + ") / (" + b1 + "x + " + b0 + ") = ");
            if (a5 != 0) 
            {
                System.out.print(a5 / b1 + "x^4 + ");               
                a4 = a4 - ((a5 / b1) * b0);               
            }            
            if (a4 != 0)
            {
                System.out.print(a4 / b1 + "x� + ");
                a3 = a3 - ((a4 / b1) * b0);               
            }            
            if (a3 != 0)
            {
                System.out.print(a3 / b1 + "x� + ");
                a2 = a2 - ((a3 / b1) * b0);
            }            
            if (a2 != 0)
            {
                System.out.print(a2 / b1 + "x + ");
                a1 = a1 - ((a2 / b1) * b0);
            }            
            if (a1 != 0)
            {
                System.out.print(a1 / b1);
                a0 = a0 - ((a1 / b1) * b0);
            }           
            if (a0 != 0) System.out.print (" Rest: " + a0);
        }
        
        else if (b2 == 0 && b1 == 0 && b0 != 0)
        {
            System.out.println("(" + a5 + "x^5 + " + a4 + "x^4 + " + a3 + "x� + " + a2 + "x� + " + a1 + "x + " + a0 + ") / " + b0 + " = ");
            if (a5 != 0) 
            {
                System.out.print(a5 / b0 + "x^5 + ");                             
            }            
            if (a4 != 0)
            {
                System.out.print(a4 / b0 + "x^4 + ");             
            }            
            if (a3 != 0)
            {
                System.out.print(a3 / b0 + "x� + ");
            }            
            if (a2 != 0)
            {
                System.out.print(a2 / b0 + "x� + ");
            }            
            if (a1 != 0)
            {
                System.out.print(a1 / b0 + "x + ");
            }              
            if (a0 != 0)
            {
                System.out.print(a0 / b0);
            }
        }        
        else if (b2 == 0 && b1 == 0 && b0 == 0) System.out.println("Es kann nicht durch Null geteilt werden!");
    }
}
