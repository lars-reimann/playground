/**
 * Beschreiben Sie hier die Klasse Autoampel.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class AutoAmpel extends Thread
{
     //Rechte Autoampel
    static Rechteck autoAmpelRechts = new Rechteck (); //Kasten
    static Rechteck autoPfostenRechts = new Rechteck (); //Pfosten
    static Kreis rotAutoRechts = new Kreis (); //Rot
    static Kreis gelbAutoRechts = new Kreis (); //Gelb
    static Kreis gruenAutoRechts = new Kreis (); //Gruen
    
    //Linke Autoampel
    static Rechteck autoAmpelLinks = new Rechteck (); //Kasten
    static Rechteck autoPfostenLinks = new Rechteck (); //Pfosten
    static Kreis rotAutoLinks = new Kreis (); //Rot
    static Kreis gelbAutoLinks = new Kreis (); //Gelb
    static Kreis gruenAutoLinks = new Kreis (); //Gruen
    
    public AutoAmpel()
    {
        //Linke Autoampel
        autoAmpelLinks.hoeheAendern (50);
        autoAmpelLinks.breiteAendern (150);
        autoAmpelLinks.farbeAendern ("schwarz"); 
        autoAmpelLinks.horizontalBewegen (300);
        autoAmpelLinks.vertikalBewegen (350);
        
        autoPfostenLinks.hoeheAendern (10);
        autoPfostenLinks.breiteAendern (150);
        autoPfostenLinks.farbeAendern ("schwarz"); 
        autoPfostenLinks.horizontalBewegen (150);
        autoPfostenLinks.vertikalBewegen (370);
               
        rotAutoLinks.groesseAendern (30);
        rotAutoLinks.farbeAendern ("rot");
        rotAutoLinks.horizontalBewegen (450);
        rotAutoLinks.vertikalBewegen (350);
        
        gelbAutoLinks.groesseAendern (30);
        gelbAutoLinks.farbeAendern ("gelb");
        gelbAutoLinks.horizontalBewegen (400);
        gelbAutoLinks.vertikalBewegen (350);
        
        gruenAutoLinks.groesseAendern (30);
        gruenAutoLinks.farbeAendern ("gruen");
        gruenAutoLinks.horizontalBewegen (350);
        gruenAutoLinks.vertikalBewegen (350);
        
        //Rechte Autoampel
        autoAmpelRechts.hoeheAendern (50);
        autoAmpelRechts.breiteAendern (150);
        autoAmpelRechts.farbeAendern ("schwarz"); 
        autoAmpelRechts.horizontalBewegen (600);
        
        autoPfostenRechts.hoeheAendern (10);
        autoPfostenRechts.breiteAendern (150);
        autoPfostenRechts.farbeAendern ("schwarz"); 
        autoPfostenRechts.horizontalBewegen (750);
        autoPfostenRechts.vertikalBewegen (20);
               
        rotAutoRechts.groesseAendern (30);
        rotAutoRechts.farbeAendern ("rot");
        rotAutoRechts.horizontalBewegen (650);
        
        gelbAutoRechts.groesseAendern (30);
        gelbAutoRechts.farbeAendern ("gelb");
        gelbAutoRechts.horizontalBewegen (700);
        
        gruenAutoRechts.groesseAendern (30);
        gruenAutoRechts.farbeAendern ("gruen");
        gruenAutoRechts.horizontalBewegen (750);
    }
    
    public static void zeichneAutoAmpel ()
    {
         //Linke Autoampel
        autoAmpelLinks.sichtbarMachen ();
        autoPfostenLinks.sichtbarMachen ();
        gruenAutoLinks.sichtbarMachen ();
        
        //Rechte Autoampel
        autoAmpelRechts.sichtbarMachen ();
        autoPfostenRechts.sichtbarMachen ();
        gruenAutoRechts.sichtbarMachen ();
    }
    
    public void run ()
    {
        //Autoampeln werden gelb
        gruenAutoLinks.unsichtbarMachen ();
        gelbAutoLinks.sichtbarMachen ();
        gruenAutoRechts.unsichtbarMachen ();
        gelbAutoRechts.sichtbarMachen ();
        
        try {
            Thread.sleep(2000); //Methode steht fuer DauerGelbAuto Sekunden still
        }
        catch (InterruptedException e) {}
        
        //Autoampeln werden rot
        gelbAutoLinks.unsichtbarMachen ();
        rotAutoLinks.sichtbarMachen ();
        gelbAutoRechts.unsichtbarMachen ();
        rotAutoRechts.sichtbarMachen ();
        
        try {
            Thread.sleep(17000); //Methode steht fuer DauerRaeumAuto Sekunden still
        }
        catch (InterruptedException e) {} 
        
        //Autoampeln werden rot/gelb
        gelbAutoLinks.sichtbarMachen ();
        gelbAutoRechts.sichtbarMachen ();
        
        try {
            Thread.sleep(2000); //Methode steht fuer DauerGelbAuto Sekunden still
        }
        catch (InterruptedException e) {}  
        
        //Autoampeln werden gruen
        rotAutoLinks.unsichtbarMachen ();
        gelbAutoLinks.unsichtbarMachen ();
        gruenAutoLinks.sichtbarMachen ();
        rotAutoRechts.unsichtbarMachen ();
        gelbAutoRechts.unsichtbarMachen ();
        gruenAutoRechts.sichtbarMachen ();
    }
}
