import java.awt.geom.Ellipse2D;

/**
 * Ein Kreis, der manipuliert werden kann und sich selbst auf einer
 * Leinwand zeichnet.
 * 
 * @Michael Koelling, David J. Barnes, Lars Reimann
 * @24.06.2010
 */

public class Kreis
{
  private int durchmesser;
  private int xPosition;
  private int yPosition;
  private String farbe;
  private boolean istSichtbar;

  /**
   * Erzeuge einen neuen Kreis an einer Standardposition mit
   * einer Standardfarbe.
   */
  public Kreis()
  {
    durchmesser = 30;
    xPosition = 20;
    yPosition = 60;
    farbe = "blau";
    istSichtbar = false;
  }

  /**
   * Mache diesen Kreis sichtbar. Wenn es bereits sichtbar ist, tue
   * nichts.
   */
  public void sichtbarMachen()
  {
    istSichtbar = true;
    zeichnen();
  }

  /**
   * Mache diesen Kreis unsichtbar. Wenn es bereits unsichtbar ist, tue
   * nichts.
   */
  public void unsichtbarMachen()
  {
    loeschen();
    istSichtbar = false;
  }

  /**
   * Bewege diesen Kreis horizontal um 'entfernung' Bildschirmpunkte.
   */
  public void horizontalBewegen(int entfernung)
  {
    loeschen();
    xPosition += entfernung;
    zeichnen();
  }

  /**
   * Bewege diesen Kreis vertikal um 'entfernung' Bildschirmpunkte.
   */
  public void vertikalBewegen(int entfernung)
  {
    loeschen();
    yPosition += entfernung;
    zeichnen();
  }

  /**
   * Aendere den Durchmesser dieses Kreises in 'neuerDurchmesser'
   * (Angabe in Bildschirmpunkten).
   * 'neuerDurchmesser' muss groesser gleich Null sein.
   */
  public void groesseAendern(int neuerDurchmesser)
  {
    loeschen();
    durchmesser = neuerDurchmesser;
    zeichnen();
  }

  /**
   * Aendere die Farbe dieses Kreises in 'neueFarbe'.
   * Gueltige Angaben sind "rot", "gelb", "blau", "gruen",
   * "lila" und "schwarz".
   */
  public void farbeAendern(String neueFarbe)
  {
    farbe = neueFarbe;
    zeichnen();
  }

  /*
   * Zeichne diesen Kreis mit seinen aktuellen Werten auf den Bildschirm.
   */
  private void zeichnen()
  {
    if (istSichtbar)
    {
      Leinwand leinwand = Leinwand.gibLeinwand();
      leinwand.zeichne(
        this,
        farbe,
        new Ellipse2D.Double(xPosition, yPosition, durchmesser, durchmesser));
      leinwand.warte(10);
    }
  }

  /*
   * Loesche diesen Kreis vom Bildschirm.
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
