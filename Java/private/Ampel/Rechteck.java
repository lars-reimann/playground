import java.awt.Rectangle;

/**
 * Ein Rechteck, das manipuliert werden kann und sich selbst auf einer
 * Leinwand zeichnet.
 * 
 * @Michael Koelling, David J. Barnes, Lars Reimann
 * @24.06.2010
 */

public class Rechteck  
{
  private int hoehe;
  private int breite;
  private int xPosition;
  private int yPosition;
  private String farbe;
  private boolean istSichtbar;

  /**
   * Erzeuge ein neues Rechteck mit einer Standardfarbe an einer
   * Standardposition.
   */
  public Rechteck()
  {
    hoehe = 30;
    breite = 30;
    xPosition = 60;
    yPosition = 50;
    farbe = "rot";
    istSichtbar = false;
  }

  /**
   * Mache dieses Rechteck sichtbar. Wenn es bereits sichtbar ist, tue
   * nichts.
   */
  public void sichtbarMachen()
  {
    istSichtbar = true;
    zeichnen();
  }

  /**
   * Mache dieses Rechteck unsichtbar. Wenn es bereits unsichtbar ist, tue
   * nichts.
   */
  public void unsichtbarMachen()
  {
    loeschen();
    istSichtbar = false;
  }

  /**
   * Bewege dieses Rechteck horizontal um 'entfernung' Bildschirmpunkte.
   */
  public void horizontalBewegen(int entfernung)
  {
    loeschen();
    xPosition += entfernung;
    zeichnen();
  }

  /**
   * Bewege dieses Rechteck vertikal um 'entfernung' Bildschirmpunkte.
   */
  public void vertikalBewegen(int entfernung)
  {
    loeschen();
    yPosition += entfernung;
    zeichnen();
  }

  /**
   * Aendere die Hoehe dieses Rechtecks in 'neueHoehe'.
   * 'neueHoehe' muss groesser gleich Null sein.
   */
  public void hoeheAendern(int neueHoehe)
  {
    loeschen();
    hoehe = neueHoehe;
    zeichnen();
  }
  
  /**
   * Aendere die Breite dieses Rechtecks in 'neueBreite'.
   * 'neueBreite' muss groesser gleich Null sein.
   */  
  public void breiteAendern(int neueBreite)
  {
    loeschen();
    breite = neueBreite;
    zeichnen();
  }

  /**
   * Aendere die Farbe dieses Rechtecks in 'neueFarbe'.
   * Gueltige Angaben sind "rot", "gelb", "blau", "gruen",
   * "lila" und "schwarz".
   */
  public void farbeAendern(String neueFarbe)
  {
    farbe = neueFarbe;
    zeichnen();
  }

  /*
   * Zeichne dieses Rechteck mit seinen aktuellen Werten auf den Bildschirm.
   */
  private void zeichnen()
  {
    if (istSichtbar)
    {
      Leinwand leinwand = Leinwand.gibLeinwand();
      leinwand.zeichne(
        this,
        farbe,
        new Rectangle(xPosition, yPosition, breite, hoehe));
      leinwand.warte(10);
    }
  }

  /*
   * Loesche dieses Rechteck vom Bildschirm.
   */
  private void loeschen()
  {
    if (istSichtbar)
    {
      Leinwand leinwand = Leinwand.gibLeinwand();
      leinwand.entferne(this);
    }
  }
}
