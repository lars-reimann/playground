package towerDefense.main;

import java.awt.Point;
import java.util.*;

import Grafik.GCreep;
import Grafik.GUI;

import towerDefense.logic.Creep;
import towerDefense.logic.Engine;
import towerDefense.logic.TDMap;
import towerDefense.logic.Tower;

/**
 * Dies ist die Schnittstelle zwischen Grafik und Logik des Spieles.
 * 
 * @version 24.3.2012
 * @author Carsten Orth, Lars Reimann
 */
public class Main implements Runnable {

    /**
     * Das Kapital des Spielers.
     */
    private int capital;

    /**
     * Die Spiellogik.
     */
    private Engine engine;

    /**
     * Die Anzahl der verbleibenden Leben.
     */
    private int lives;

    private GUI g;
    private TDMap map;

    private long timePerSequence;

    private int deathCount;
    
    public Main() {
        map = new TDMap();
        g = new GUI(map.getField(), this);
        engine = new Engine(this);
        capital = 1000;
        lives = 20;
        timePerSequence = 75;

        Set<String> towerNames = engine.getTowerNames();
        for (String towerName : towerNames) {
            g.addTower(towerName);
        }

        new Thread(this).start();
    }

    public TDMap getMap() {
        return map;
    }

    public void run() {
        // TODO Test
        int counter = 0;
        while (true) {
            update();
            if (lives <= 0) {
                g.showEnd(deathCount);
                break;
            }
            try {
                Thread.sleep(timePerSequence);
            } catch (Exception e) {
            }
        }
    }

    private void update() {
        updateEngine();
        updateMap();
        updateInterface();
    }

    /**
     * L&ouml;scht den Turm an der Stelle (x; y).
     * 
     * @param point
     *            Die Position des abzurei&szlig;enden Turmes.
     */
    public void delTower(Point point) {
        engine.delTower(point);
    }

    /**
     * Gibt das momentane Kapital des Spielers zur&uuml;ck.
     * 
     * @return Das momentane Kapital des Spielers.
     */
    public int getCapital() {
        return capital;
    }

    /**
     * Gibt die Anzahl an verbleibenden Leben des Spielers zur&uuml;ck.
     * 
     * @return Die Anzahl an verbleibenden Leben.
     */
    public int getLives() {
        return lives;
    }

    /**
     * Gibt die Liste mit den Namen der verf&uuml;gbaren Prototypen der
     * T&uuml;rme zur&uuml;ck.
     * 
     * @return Die Liste mit den Namen der Prototypen der T&uuml;rme.
     */
    public Set<String> getTowerNames() {
        return engine.getTowerNames();
    }

    /**
     * Gibt den Turm mit dem angegebenen Namen zur&uuml;ck.
     * 
     * @param name
     *            Der Name des zur&uuml;ckzugegebenen Turmes.
     * @return Den geforderten Turm.
     */
    public Tower getTower(String name) {
        return engine.getTower(name);
    }

    /**
     * Gibt den Turm zu&uuml;ck, der an der angegebenen Stelle steht.
     * 
     * @param pos
     *            Die Position des auszugebenden Turmes.
     * @return Den Turm an dieser Stelle.
     */
    public Tower getTower(Point pos) {
        return engine.getTower(pos);
    }

    /**
     * Gibt an, ob der Turm an der Stelle (x; y) verbessert werden kann, also ob
     * es noch weitere Upgrades gibt und ob diese finanzierbar sind.
     * 
     * @param Point
     *            pos Die Position des zu &uuml;berpr&uuml;fenden Turmes.
     * @return Ob der spezifizierte Turm verbessert werden kann.
     */
    public boolean isUpgradable(Point pos) {
        return engine.isUpgradable(pos);
    }

    /**
     * Gibt an, ob der angegebene Turm finanziert werden kann.
     * 
     * @param name
     *            Der zu testende Turm.
     * @return Ob der angegebene Turm gekauft werden kann.
     */
    public boolean canBuyTower(String name) {
        return engine.canBuyTower(name);
    }

    /**
     * Baut an der Stelle (x; y) einen neuen Turm, der durch den angegebenen
     * Namen spezifiziert ist.
     * 
     * @param name
     *            Die Art des zu bauenden Turmes.
     * @param pos
     *            Die Position des Baustelle.
     */
    public void buildTower(String name, Point pos) {
        engine.buildTower(name, pos);
    }

    /**
     * Setzt das Kapital des Spielers auf den &uuml;bergebenen Wert.
     * 
     * @param capital
     *            Das neue Kapital.
     */
    public void setCapital(int capital) {
        this.capital = capital;
    }

    /**
     * Setzt die verbliebenen Leben des Spielers auf den &uuml;bergebenen Wert.
     * 
     * @param lives
     *            Die neue Anzahl an Leben des Spielers.
     */
    public void setLives(int lives) {
        this.lives = lives;
    }

    /**
     * Ruft die Methode in der Engine auf, die den Spielstand aktualisiert und
     * alle auszuf&uuml;hrenden Aktionen t&auml;tigt.
     */
    public void updateEngine() {
        engine.update();
    }

    /**
     * Verbessert den an der Stelle (x; y) stehenden Turm.
     * 
     * @param pos
     *            Die Position des zu verbessernden Turmes.
     */
    public void upgradeTower(Point pos) {
        engine.upgradeTower(pos);
    }

    public void actCreep(int index, int newX, int newY) {
        g.setCreepLocation(index, newX, newY);
    }

    public Creep getCreep(String name) {
        return engine.getCreep(name);
    }

    public void updateMap() {
        g.updateMap();
    }

    public void updateInterface() {
        g.setMoney(capital);
        g.setLife(lives);
    }

    /**
     * Entfernt den Gegner aus der Gegnerliste in der Grafik.
     * 
     * @param index
     *            Die Position des zu l&ouml;schenden Gegners.
     */
    public void removeCreep(int index) {
        g.removeCreep(index);
    }

    public void spawnCreep(String name, Point pos) {
        g.spawnCreep(name, pos);
    }

    public void updateCreepInGraphics(int index, int HPPercentage) {
        g.setCreepLP(index, HPPercentage);
    }

    public void increaseDeathCount() {
        deathCount++;
    }
}