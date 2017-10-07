import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.game.GameCanvas;

/**
 * @author Lars Reimann
 * @version 19. Februar 2011
 */
public class TicTacToeCanvas extends GameCanvas {

    public TicTacToeCanvas() {
        super(false);
    }

    public void paint(Graphics graphics) {
        
        // Hier kommt der Quelltext zum Zeichnen des Spieles rein.
        // Benoetigt werden Geraden fuer das Spielfeld, Kreise fuer die
        // Darstellung der Spielsteine und die Moeglichkeit, die Farbe zu
        // aendern, um anzuzeigen wem der Spielstein gehoert.
        // Schliesslich muss der Benutzer noch ueber den Ausgang des Spiels
        // informiert werden, wenn dieses zu Ende ist. Dies kann etwa durch
        // die Ausgabe eines Strings geschehen.
        
        // Fuer Geraden:  graphics.drawLine(int x1, int y1, int x2, int y2);
        // Fuer Kreise:   graphics.fillArc(int x, int y, int breite, int hoehe, int startwinkel, int endwinkel);
        // Farbe aendern: graphics.setColor(int anteilRot, int anteilGruen, int anteilBlau);
        // Fuer Strings:  graphics.drawString(String wort, int x, int y, int anker); [anker = 0]
        
    }

    protected void keyPressed(int keyCode) {
        
        // if (keyCode == KEY_NUM1) {
        //     tue etwas;
        // } else if (keyCode == KEY_NUM2) {
        //     tue etwas anderes
        // }
        // .....
        
        // KEY_NUM1 entspricht der Taste 1 auf dem Handy
        // KEY_NUM2 entspricht demnach der Taste 2
        // Was entspricht dann wohl der Taste 3?!
        
    }

    private boolean istGewonnen(int spieler) {
        
        // Testet, ob der etsprechende Spieler, also der Benutzer oder der
        // Computer gewonnen hat. Wem nicht gelaeufig sein sollte, wann man
        // gewonnen hat, sei noch folgendes gesagt:
        // Ziel ist es, drei Spielsteine in einer Reihe, Spalte oder Diagonalen
        // unterzubringen.
        // Der Parameter "spieler" zeigt dabei an, ob die Steine des Spielers
        // oder die des Computer-Gegners ueberprueft werden sollen.
        
        return false;
    }

    private boolean istVoll() {
        
        // Testet, ob das gesamte Spielfeld mit Steinen gefuellt ist. Ist dies
        // der Fall und hat niemand 3 Steine in einer Reihe, Spalte oder
        // Diagonalen platzieren koennen, endet das Spiel unentschieden.
        
        return false;
    }

    private void setzeKI() {
        
        // Fuege hier den Code fuer den Computer-Gegner ein.
        // Tipp: Fuer den Anfang reicht es vollkommen aus, den Computer
        // seine Steine voellig zufaellig platzieren zu lassen. Wenn du dann
        // noch Zeit und Lust hast, kannst du den Computer immer noch ein wenig
        // "cleverer" machen.
        
    }
}
