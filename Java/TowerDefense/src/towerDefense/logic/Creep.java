package towerDefense.logic;

import java.awt.Point;

import towerDefense.main.Main;

/**
 * Die Darstellung eines Gegners.
 * 
 * @version 27.3.2012
 * @author Lars Reimann
 */
public class Creep {

    /**
     * Der Betrag, der nach T&ouml;tung des Gegners gutgeschrieben wird.
     */
    private int gain;

    /**
     * Die Lebenspunkte dieses Gegners.
     */
    private int hitpoints;

    /**
     * Der Name dieses Gegners.
     */
    private final String name;

    /**
     * Die Position des Mittelpunktes des Gegners in Pixeln von der linken
     * oberen Ecke des Fensters aus gerechnet.
     */
    private Point pixelPos;

    /**
     * Kopiert den angegebenen Gegner und setzt die Position des somit kopierten
     * Gegners entsprechend.
     * 
     * @param creep
     *            Der zu kopierende Gegner.
     * @param pixelPos
     *            Die Position des neuen Gegners.
     */
    public Creep(Creep creep, Point pixelPos) {
        this.name = creep.getName();
        this.hitpoints = creep.getHitpoints();
        this.gain = creep.getGain();
        this.pixelPos = pixelPos;
    }

    /**
     * Erstellt einen neuen Prototypen eines Gegners mit den angegebenen Werten.
     * Die Position wird auf den Standardwert (0; 0) gesetzt.
     * 
     * @param name
     *            Der Name des Gegners.
     * @param hitpoints
     *            Die Trefferpunkte des Gegners.
     * @param gain
     *            Der Gewinn nach T&ouml;ten des Gegners.
     */
    public Creep(String name, int hitpoints, int gain) {
        this.name = name;
        this.hitpoints = hitpoints;
        this.gain = gain;
        this.pixelPos = new Point();
    }

    /**
     * F&uuml;gt diesem Gegner den angegebenen Schadensbetrag zu.
     * 
     * @param damage
     *            Der zuzuf&uuml;gende Schaden.
     */
    public void damage(int damage) {
        hitpoints -= damage;
    }

    /**
     * Gibt den Betrag zur&uuml;ck, den ein Spieler erh&auml;lt, wenn dieser
     * Gegner abgeschossen wird.
     * 
     * @return Den zu erhaltenden Betrag.
     */
    public int getGain() {
        return gain;
    }

    /**
     * Gibt die verbleibenden Trefferpunkte dieses Gegners zur&uuml;ck.
     * 
     * @return Die verbleibenden Trefferpunkte.
     */
    public int getHitpoints() {
        return hitpoints;
    }

    /**
     * Gibt den Namen dieses Gegners zur&uuml;ck.
     * 
     * @return Den Namen des Gegners.
     */
    public String getName() {
        return name;
    }

    /**
     * Gibt die aktuelle Position des Gegners zur&uuml;ck.
     * 
     * @return Die aktuelle Position.
     */
    public Point getPixelPos() {
        return pixelPos;
    }
    
    public void strengthen() {
        hitpoints = hitpoints * 120 / 100;
        gain = gain * 110 / 100;
    }

    /**
     * Gibt an, ob dieser Gegner get&ouml;tet worden ist, ob dessen Lebenspunkte
     * also auf oder unter null gesunken sind.
     * 
     * @return Ob dieser Gegner get&ouml;tet worden ist.
     */
    public boolean isDead() {
        return hitpoints <= 0;
    }

    /**
     * Gibt an, ob der Gegner im Ziel angekommen ist.
     * 
     * @param main
     *            Die Instanz der Hauptklasse.
     * @return Ob der Gegner im Ziel angekommen ist.
     */
    public boolean isSecure(Main main) {
        Point end = main.getMap().getEnd();
        return isXSecure(end) && isYSecure(end);
    }

    /**
     * Gibt an, ob der Gegner im Ziel angekommen sein kann, wenn man nur die
     * x-Koordinate betrachtet.
     * 
     * @param end
     *            Die Position des Zieles im Feldarray.
     * @return Ob die obrige Bedingung erfüllt ist.
     */
    private boolean isXSecure(Point end) {
        return pixelPos.x >= end.x * 40 && pixelPos.x < (end.x + 1) * 40;
    }

    /**
     * Gibt an, ob der Gegner im Ziel angekommen sein kann, wenn man nur die
     * y-Koordinate betrachtet.
     * 
     * @param end
     *            Die Position des Zieles im Feldarray.
     * @return Ob die obrige Bedingung erfüllt ist.
     */
    private boolean isYSecure(Point end) {
        return pixelPos.y >= end.y * 40 && pixelPos.y < (end.y + 1) * 40;
    }

    /**
     * Setzt die Position des Gegners auf die angegebenen Werte.
     * 
     * @param pos
     *            Die neue Position des Gegners.
     */
    public void setPixelPos(Point pixelPos) {
        this.pixelPos = pixelPos;
    }
}
