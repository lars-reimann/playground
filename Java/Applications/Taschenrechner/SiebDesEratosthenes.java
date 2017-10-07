/**
 * Diese Klasse sucht Primzahlen bis zu einem bestimmten Endwert und gibt sie ab einem bestimmten
 * Startwert in der Konsole aus.
 * 
 * @Lars Reimann
 * @23.02.2010
 */
public class SiebDesEratosthenes
{ 
    /**
     * Geben Sie den Startwert und das Ende der Suche ein. Der Startwert beeinflusst jedoch lediglich
     * die Ausgabe, da das Sieb des Eratosthenes nur bei einem Startwert von 2 funktioniert.
     */
    public int Pruefzahlen [ ];
    public int Start;
    public int Ende;
    
    public void ProgrammStarten (int Start, int Ende)
    {
        this.Start = Start;
        this.Ende = Ende;       
        Pruefzahlen = new int [Ende + 1]; //Dies ist das Array in das die Pruefzahlen eingeschrieben werden;        
        int Zaehler = 2;
        int Teiler = 2;
        boolean NaechsterTeiler = true; //Verwendet zum Testen ob ein naechsthoeherer Teiler kleiner als das Ende vorhanden ist
        
        //Einlesen
        while (Zaehler <= Ende)
        {
            Pruefzahlen [Zaehler] = Zaehler; //Die Speicherplaetze ab Nummer 2 erhalten den Wert ihrer Nummer [2] = 2....
            Zaehler ++;
        }
        
        //Aussortieren         
        while (Teiler <= Math.sqrt(Ende) && NaechsterTeiler == true) //Die Schleife wird nur ausgeführt, wenn es einen neuen Teiler gibt und dieser neue Teiler kleiner als die Wurzel des Endes ist
        {
            Zaehler = Teiler + 1; //Das "Durchstreichen" ist nur bei Zahlen groeßer als der Teiler noetig
            //Vielfache des Teilers aussortieren
            while (Zaehler <= Ende) //Alle Zahlen größer als der Teiler und kleiner als das Ende werden auf Vielfachheit getestet
            {
                if (Pruefzahlen [Zaehler] % Teiler == 0) Pruefzahlen [Zaehler] = 0; //Wenn der Rest der Division Zaehler : Teiler = 0 ist, ist Zaehler ein Vielfaches des Teilers und damit keine Primzahl mehr
                Zaehler ++;
            }
            //Variablen zuruecksetzen
            NaechsterTeiler = false; //Da alle Vielfachen des Teilers aussortiert wurden, muss diese Variable auf falsch gestellt werden, da man nicht weiß ob es noch einen hoeheren Teiler kleiner als das Ende gibt
            Zaehler = Teiler + 1; //Da nur neue Teiler groeßer als der alte gesucht werden werden auch nur diese gesucht und man faengt nicht wieder bei 2 an
            //Naestgroesseren Teiler finden
            while (NaechsterTeiler == false && Zaehler <= Math.sqrt(Ende))
            {
                if (Pruefzahlen [Zaehler] != 0) 
                {
                    Teiler = Pruefzahlen [Zaehler];
                    NaechsterTeiler = true;
                }
                Zaehler ++;
            }
        }
        System.out.print("Liste erstellt");
    }
    
    public void Primzahlen ()
    {
        int Zaehler = this.Start; //Zaehler wird zum Durchzaehlen der Arrayplaetze genutzt
        int PrimzahlZaehlerEinzel = 0;
        int PrimzahlZaehlerGesamt = 0;
        System.out.println("Primzahlen von " + Start + " bis " + Ende + ":");
        System.out.println("");
        System.out.println("");
        
        while (Zaehler <= Ende)
        {
            if (Pruefzahlen [Zaehler] != 0)
            {
                System.out.print(Zaehler + " "); 
                PrimzahlZaehlerEinzel ++;
                PrimzahlZaehlerGesamt ++;
            }
            if (Zaehler % 100 == 0 && Zaehler != Start || Zaehler == Ende)
            {
                System.out.println(" (" + PrimzahlZaehlerEinzel + " Primzahlen)");
                System.out.println("");
                PrimzahlZaehlerEinzel = 0;
                
            }           
            Zaehler ++;
        }
        System.out.println("");
        System.out.println("");
        System.out.println("Zwischen " + Start + " und " + Ende + " gibt es insgesamt " + PrimzahlZaehlerGesamt + " Primzahlen.");
    }
    
    public void Primzahlzwillinge ()
    {        
        int Zaehler = 2; //Zaehler wird zum Durchzaehlen der Arrayplaetze genutzt
        int Teiler = 0; //Wird zum Zaehlen der Primzahlen verwendet
        System.out.println("Primzahlzwillinge von " + Start + " bis " + Ende + ":");
        System.out.println("");
        //Sonderfall
        if (Start <= 2 && Ende >= 3) 
        {
            System.out.println("2 - 3");
            Teiler ++;
        }
        while (Zaehler <= Ende)
        {
            if (Pruefzahlen [Zaehler] != 0 && Pruefzahlen [Zaehler - 2] != 0 && Pruefzahlen [Zaehler] >= Start)
            {
                System.out.println ((Zaehler - 2) + " - " + Zaehler);
                Teiler ++;
            }
            Zaehler ++;
        }
        System.out.println("");
        if (Teiler == 1) System.out.println("Zwischen " + Start + " und " + Ende + " gibt es " + Teiler + " Primzahlzwilling.");
        else System.out.println("Zwischen " + Start + " und " + Ende + " gibt es " + Teiler + " Primzahlzwillinge.");
    }
    
    public void Primzahldrillinge ()
    {
        int Zaehler = 6; //Zaehler wird zum Durchzaehlen der Arrayplaetze genutzt
        int Teiler = 0; //Wird zum Zaehlen der Primzahlen verwendet
        System.out.println("Primzahldrillinge von " + Start + " bis " + Ende + ":");
        System.out.println("");
        //Sonderfälle
        if (Start <= 2 && Ende >= 5)
        {
            System.out.println("2 - 3 - 5");
            Teiler ++;
        }
        if (Start <= 3 && Ende >= 7)
        {
            System.out.println("3 - 5 - 7");
            Teiler ++;
        }
        while (Zaehler <= Ende)
        {
            if (Pruefzahlen [Zaehler] != 0 && Pruefzahlen [Zaehler - 2] != 0 && Pruefzahlen [Zaehler - 6] != 0 && Pruefzahlen [Zaehler] >= Start)
            {
                System.out.println ((Zaehler - 6) + " - " + (Zaehler - 2) + " - " + Zaehler);
                Teiler ++;
            }
            else if (Pruefzahlen [Zaehler] != 0 && Pruefzahlen [Zaehler - 4] != 0 && Pruefzahlen [Zaehler - 6] != 0 && Pruefzahlen [Zaehler] >= Start)
            {
                System.out.println ((Zaehler - 6) + " - " + (Zaehler - 4) + " - " + Zaehler);
                Teiler ++;
            }
            Zaehler ++;

        }
        System.out.println("");
        if (Teiler == 1) System.out.println("Zwischen " + Start + " und " + Ende + " gibt es " + Teiler + " Primzahldrilling.");
        else System.out.println("Zwischen " + Start + " und " + Ende + " gibt es " + Teiler + " Primzahldrillinge.");
    }
}