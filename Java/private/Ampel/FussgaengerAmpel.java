/**
 * Beschreiben Sie hier die Klasse Fussgaengerampel.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class FussgaengerAmpel extends Thread
{         
    //Obere Fussgaengerampel
    Rechteck fussAmpelOben = new Rechteck (); //Kasten
    Rechteck fussPfostenOben = new Rechteck (); //Pfosten
    Kreis rotFussOben = new Kreis (); //Rot
    Kreis gruenFussOben = new Kreis (); //Gruen
    
    //Untere Fussgaengerampel
    Rechteck fussAmpelUnten = new Rechteck (); //Kasten
    Rechteck fussPfostenUnten = new Rechteck (); //Pfosten
    Kreis rotFussUnten = new Kreis (); //Rot
    Kreis gruenFussUnten = new Kreis (); //Gruen
    
    public FussgaengerAmpel ()
    {       
        //Obere Fussgaengerampel
        fussAmpelOben.hoeheAendern (100);
        fussAmpelOben.breiteAendern (50);
        fussAmpelOben.farbeAendern ("schwarz");
        fussAmpelOben.horizontalBewegen (500);
        fussAmpelOben.vertikalBewegen (-50);
        
        fussPfostenOben.hoeheAendern (50);
        fussPfostenOben.breiteAendern (10);
        fussPfostenOben.farbeAendern ("schwarz");
        fussPfostenOben.horizontalBewegen (520);
        fussPfostenOben.vertikalBewegen (40);
        
        rotFussOben.groesseAendern (30);
        rotFussOben.farbeAendern ("rot");
        rotFussOben.horizontalBewegen (550);
        rotFussOben.vertikalBewegen (-50);
        
        gruenFussOben.groesseAendern (30);
        gruenFussOben.farbeAendern ("gruen");
        gruenFussOben.horizontalBewegen (550);
        
        //Untere Fussgaengerampel
        fussAmpelUnten.hoeheAendern (100);
        fussAmpelUnten.breiteAendern (50);
        fussAmpelUnten.farbeAendern ("schwarz");
        fussAmpelUnten.horizontalBewegen (500);
        fussAmpelUnten.vertikalBewegen (350);
        
        fussPfostenUnten.hoeheAendern (50);
        fussPfostenUnten.breiteAendern (10);
        fussPfostenUnten.farbeAendern ("schwarz");
        fussPfostenUnten.horizontalBewegen (520);
        fussPfostenUnten.vertikalBewegen (300);
        
        rotFussUnten.groesseAendern (30);
        rotFussUnten.farbeAendern ("rot");
        rotFussUnten.horizontalBewegen (550);
        rotFussUnten.vertikalBewegen (400);
        
        gruenFussUnten.groesseAendern (30);
        gruenFussUnten.farbeAendern ("gruen");
        gruenFussUnten.horizontalBewegen (550);
        gruenFussUnten.vertikalBewegen (350);
    }
    
    public void zeichneFussgaengerAmpel ()
    {       
        //Obere Fussgaengerampel
        fussAmpelOben.sichtbarMachen ();
        fussPfostenOben.sichtbarMachen ();
        rotFussOben.sichtbarMachen (); 
        
        //Untere Fussgaengerampel
        fussAmpelUnten.sichtbarMachen ();
        fussPfostenUnten.sichtbarMachen ();
        rotFussUnten.sichtbarMachen ();
    }
    
    public void run ()
    {
        try {
            Thread.sleep(4000);
        }
        catch (InterruptedException e) {}
        
        //Fussgaengerampeln werden gruen
        rotFussOben.unsichtbarMachen ();
        gruenFussOben.sichtbarMachen ();
        rotFussUnten.unsichtbarMachen ();
        gruenFussUnten.sichtbarMachen (); 
        
        try {
            Thread.sleep(10000); //Methode steht fuer DauerGruenFuss Sekunden still
        }
        catch (InterruptedException e) {} 
        
        //Fussgaengerampeln werden rot
        gruenFussOben.unsichtbarMachen ();
        rotFussOben.sichtbarMachen ();
        gruenFussUnten.unsichtbarMachen ();
        rotFussUnten.sichtbarMachen (); 
    }
}
