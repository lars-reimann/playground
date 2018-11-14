package towerDefense.logic;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import towerDefense.main.Main;

/**
 * Die Hauptklasse der Spiellogik.
 * 
 * @version 24.3.2012
 * @author Lars Reimann
 */
public class Engine {

    private List<Creep> activeCreeps;

    /**
     * Die Darstellung der aktiven T&uuml;rme.
     */
    private final Tower[][] activeTowers;

    private int creepCooldown;

    /**
     * Die Liste mit den Prototypen der T&uuml;rme.
     */
    private final Map<String, Creep> creepPrototypes;

    /**
     * Die Instanz der Haupklasse.
     */
    private final Main main;

    private int moveCooldown;

    private List<Point> moves;

    private int timePerCreep;

    private int timePerMove;

    /**
     * Die Liste mit den Prototypen der T&uuml;rme.
     */
    private final Map<String, Tower> towerPrototypes;

    public Engine(Main main) {
        this.main = main;
        creepPrototypes = readCreeps();
        towerPrototypes = readTowers();
        int[][] field = main.getMap().getField();
        activeTowers = new Tower[field.length][field[0].length];
        activeCreeps = new ArrayList<Creep>();
        creepCooldown = 0;
        moveCooldown = 0;
        timePerMove = 20;
        timePerCreep = 50;
        moves = new ArrayList<Point>();
        visitedFields = new boolean[16][16];
    }

    /**
     * Baut an der angegebenen Stelle einen neuen Turm des entsprechenden Types.
     * 
     * @param name
     *            Der Name des zu bauenden Turmes.
     * @param pos
     *            Die Position im Array, wo der Turm gebaut werden soll.
     */
    public void buildTower(String name, Point pos) {
        Tower tower = new Tower(getTower(name), computePixelPos(pos));
        activeTowers[pos.x][pos.y] = tower;
        main.setCapital(main.getCapital() - tower.getPrice());
    }

    /**
     * Gibt an, ob der angegebene Turm finanziert werden kann.
     * 
     * @param name
     *            Der zu testende Turm.
     * @return Ob der angegebene Turm gekauft werden kann.
     */
    public boolean canBuyTower(String name) {
        return getTower(name).getPrice() <= main.getCapital();
    }

    private int computeHPPercentage(Creep creep) {
        return 100 * creep.getHitpoints()
                / creepPrototypes.get(creep.getName()).getHitpoints();
    }

    private void computeMoves() {
        moves.clear();
        for (int i = 0; i < activeCreeps.size(); i++) {
            Point pixelPos = activeCreeps.get(i).getPixelPos();
            Point pos = computePos(pixelPos);
            Point newPos = findPath(pos);
            Point newPixelPos = computePixelPos(newPos);
            Point move = new Point((newPixelPos.x - pixelPos.x) / timePerMove,
                    (newPixelPos.y - pixelPos.y) / timePerMove);
            moves.add(move); // TODO
        }
    }

    /**
     * Berechnet aus der angegebenen Position im Array die Position in Pixeln
     * des Mittelpunktes des Objektes von der linken oberen Ecke aus gerechnet.
     * 
     * @param pos
     *            Die Position im Array.
     * @return Die berechnete Position des Mittelpunktes.
     */
    private Point computePixelPos(Point pos) {
        int pixelX = (int) ((pos.x + 0.5) * 40);
        int pixelY = (int) ((pos.y + 0.5) * 40);
        return new Point(pixelX, pixelY);
    }

    private Point computePos(Point pixelPos) {
        return new Point(pixelPos.x / 40, pixelPos.y / 40);
    }

    /**
     * L&ouml;scht den Turm an der angegebenen Stelle.
     * 
     * @param pos
     *            Die Position des Turmes, der gel&ouml;scht werden soll.
     */
    public void delTower(Point pos) {
        activeTowers[pos.x][pos.y] = null;
    }

    private Point findPath(Point pos) {
        TDMap map = main.getMap();
        Point point = startSearch(pos.x, pos.y, map.getEnd());
        return point;
    }

    private Point startSearch(int x, int y, Point end) {
        resetVisitedFields();
        visitedFields[x][y] = true;
        if (x == end.x && y == end.y) {
            return new Point(x, y);
        } else if (findPath(x, y + 1, end)) {
            return new Point(x, y + 1);
        } else if (findPath(x + 1, y, end)) {
            return new Point(x + 1, y);
        } else if (findPath(x - 1, y, end)) {
            return new Point(x - 1, y);
        } else if (findPath(x, y - 1, end)) {
            return new Point(x, y - 1);
        } else {
            throw new RuntimeException("Sackgasse (" + x + "|" + y + ")");
        }
    }
    
    private void resetVisitedFields() {
        for (int i = 0; i < visitedFields.length; i++) {
            for (int j = 0; j < visitedFields[i].length; j++) {
                visitedFields[i][j] = false;
            }
        }
    }

    private boolean[][] visitedFields;

    private boolean findPath(int x, int y, Point end) {
        if (!main.getMap().getFieldStatus(y, x).equals("way")
                || visitedFields[x][y]) {
            return false;
        } else {
            visitedFields[x][y] = true;
            return ((x == end.x && y == end.y)
                    || findPath(x, y + 1, end)
                    || findPath(x + 1, y, end)
                    || findPath(x - 1, y, end) || findPath(x,
                        y - 1, end));
        }
    }

    public List<Creep> getActiveCreeps() {
        return activeCreeps;
    }

    public Creep getCreep(String name) {
        return creepPrototypes.get(name);
    }

    /**
     * Gibt den Turm zur&uuml;ck, der an der angegebenen Stelle steht.
     * 
     * @param pos
     *            Die Position des zur&uuml;ckzugebenden Turmes.
     * @return Den Turm an der angegebenen Stelle.
     */
    public Tower getTower(Point pos) {
        return activeTowers[pos.x][pos.y];
    }

    /**
     * Gibt den Turm zur&uuml;ck, der dem angegebenen Namen entspricht.
     * 
     * @param name
     *            Der Namen des zur&uuml;ckzugebenden Turmes.
     * @return Den entsprechenden Turm.
     */
    public Tower getTower(String name) {
        return towerPrototypes.get(name);
    }

    /**
     * Gibt eine Liste mit den Namen der verf&uuml;gbaren T&uuml;rme
     * zur&uuml;ck.
     * 
     * @return Eine Liste der Turmnamen.
     */
    public Set<String> getTowerNames() {
        return towerPrototypes.keySet();
    }

    /**
     * Gibt an, ob der Turm an der angegebenen Stelle verbessert werden kann.
     * 
     * @param pos
     *            Die Position des zu testenden Turmes.
     * @return Ob der Turm verbessert werden kann.
     */
    public boolean isUpgradable(Point pos) {
        Tower selectedTower = activeTowers[pos.x][pos.y];
        int capital = main.getCapital();
        return selectedTower.hasMoreUpgrades()
                && selectedTower.getNextUpgrade().getPrice() <= capital;
    }

    private void move() {
        for (int i = 0; i < moves.size(); i++) {
            Creep creep = activeCreeps.get(i);
            Point oldPos = creep.getPixelPos();
            Point move = moves.get(i);
            int newX = oldPos.x + move.x;
            int newY = oldPos.y + move.y;
            creep.setPixelPos(new Point (newX, newY));
            main.actCreep(i, newX, newY);
        }
    }

    /**
     * Liest die verf&uuml;gbaren Gegner ein.
     * 
     * @return Eine Map mit den nach Namen sortierten Gegnern.
     */
    private Map<String, Creep> readCreeps() {
        try {
            return new CreepParser().parseCreeps();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Fehler beim Einlesen der Gegner");
        }
    }

    /**
     * Liest die verf&uuml;gbaren T&uuml;rme ein.
     * 
     * @return Eine Map mit den nach Namen sortierten T&uuml;rmen.
     */
    private Map<String, Tower> readTowers() {
        try {
            return new TowerParser().parseTowers();
        } catch (Exception e) {
            throw new RuntimeException("Fehler beim Einlesen der Tuerme.");
        }
    }

    private void spawnCreep(String name) {
        Point pixelPos = computePixelPos(main.getMap().getStart());
        int x = pixelPos.x;
        int y = pixelPos.y;
        activeCreeps.add(new Creep(creepPrototypes.get(name), new Point (x, y)));
        main.spawnCreep(name, pixelPos);
    }

    private void spawnNewCreep() {
        Set<String> creepNames = creepPrototypes.keySet();
        int rnd = (int) (Math.random() * creepNames.size());
        spawnCreep(creepNames.toArray(new String[0])[rnd]);
    }

    /**
     * Aktualisiert die Spiellogik.
     */
    public void update() {
        updateTowers();
        updateCreeps();
        if (creepCooldown == 0) {
            spawnNewCreep();
            creepCooldown = timePerCreep;
        } else {
            creepCooldown--;
        }
        if (moveCooldown == 0) {
            computeMoves();
            moveCooldown = timePerMove;
        } else {
            move();
            moveCooldown--;
        }

    }

    /**
     * Aktualisiert die Gegner.
     */
    private void updateCreeps() {
        Iterator<Creep> i = activeCreeps.iterator();
        int currentIndex = 0;
        while (i.hasNext()) {
            Creep creep = i.next();
            if (creep.isDead()) {
                main.setCapital(main.getCapital() + creep.getGain());
                getCreep(creep.getName()).strengthen();
                i.remove();
                main.increaseDeathCount();
                if (!moves.isEmpty()) {
                    moves.remove(currentIndex);
                }
                main.removeCreep(currentIndex);
            } else if (creep.isSecure(main)) {
                main.setLives(main.getLives() - 1);
                i.remove();
                main.removeCreep(currentIndex);
                moves.remove(currentIndex);
            } else {
                main.updateCreepInGraphics(currentIndex,
                        computeHPPercentage(creep));
                currentIndex++;
            }
        }
    }
    
    

    /**
     * Aktualisiert die T&uuml;rme.
     */
    private void updateTowers() {
        for (int i = 0; i < activeTowers.length; i++) {
            for (int j = 0; j < activeTowers[i].length; j++) {
                if (activeTowers[i][j] != null) {
                    activeTowers[i][j].update(this);
                }
            }
        }
    }

    /**
     * Verbessert den Turm an der angegebenen Stelle.
     * 
     * @param pos
     *            Die Position des zu verbessernden Turmes.
     */
    public void upgradeTower(Point pos) {
        activeTowers[pos.x][pos.y].upgrade(main);
    }
}
