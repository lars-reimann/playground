import java.awt.Graphics;
import java.awt.Image;
import java.util.Stack;

import javax.swing.JPanel;

/**
 *
 * @author Lars Reimann
 * @version 29. Januar 2011
 */
public final class Hanoi extends JPanel implements Runnable {

    /**
     * Der Graphikkontext des Bildes, das zur Doppelpufferung benutzt wird.
     */
    private Graphics             dbGraphics;

    /**
     * Das Bild, das zur Doppelpufferung genutzt wird.
     */
    private Image                dbImage;

    /**
     * Hier werden die Scheiben gespeichert, die auf dem ersten Platz liegen.
     */
    private final Stack<Integer> firstStack  = new Stack<Integer>();

    /**
     * Die Flagge, die anzeigt ob bereits ein Thread am Laufen ist oder nicht.
     */
    private boolean              isStopped   = true;

    /**
     * Hier werden die Scheiben gespeichert, die auf dem zweiten Platz liegen.
     */
    private final Stack<Integer> secondStack = new Stack<Integer>();

    /**
     * Die Dauer, die nach einem Zug gewartet werden soll.
     */
    private long                 sleepTime;

    /**
     * Hier werden die Scheiben gespeichert, die auf dem dritten Platz liegen.
     */
    private final Stack<Integer> thirdStack  = new Stack<Integer>();

    /**
     * Konstruiert eine neue Instanz dieser Klasse, die von JPanel abgeleitet
     * ist. Es wird ein 750x100 grosses Feld erzeugt.
     */
    public Hanoi() {
        super();
        setSize(750, 100);
    }

    /**
     * Gibt an, ob bereits ein Thread am Laufen ist oder nicht.
     *
     * @return Die Flagge, die dies anzeigt.
     */
    public boolean isStopped() {
        return isStopped;
    }

    /**
     * Bewegt eine Scheibe von einem zum anderen Stapel.
     *
     * @param start Der Stapel, von dem die oberste Scheibe entfernt werden
     *              soll.
     * @param end   Der Stapel, auf den diese Scheibe gelegt werden soll.
     */
    public void moveDisk(final int start, final int end) {
        if (!isStopped) {
            int disk;
            if (start == 1) {
                disk = firstStack.pop();
            } else if (start == 2) {
                disk = secondStack.pop();
            } else {
                disk = thirdStack.pop();
            }
            if (end == 1) {
                firstStack.push(disk);
            } else if (end == 2) {
                secondStack.push(disk);
            } else {
                thirdStack.push(disk);
            }
            update(getGraphics());
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException exception) {
                // ignoriere
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public void paint(final Graphics graphics) {
        for (int i = 0; i < firstStack.size(); i++) {
            graphics.fillRect(
                getSize().width / 6 - (firstStack.get(i) * 5),
                getSize().height - (i + 1) * 5,
                firstStack.get(i) * 10, 5);
        }
        for (int i = 0; i < secondStack.size(); i++) {
            graphics.fillRect(
                getSize().width / 2 - (secondStack.get(i) * 5),
                getSize().height - (i + 1) * 5,
                secondStack.get(i) * 10, 5);
        }
        for (int i = 0; i < thirdStack.size(); i++) {
            graphics.fillRect(
                5 * getSize().width / 6 - (thirdStack.get(i) * 5),
                getSize().height - (i + 1) * 5,
                thirdStack.get(i) * 10, 5);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void run() {
        isStopped = false;
        solve(firstStack.size(), 1, 3, 2);
        isStopped = true;
    }

    /**
     * Setzt die einzelen Stapel zurueck, sodass alle Scheiben wieder auf dem
     * ersten Stapel liegen. Ausserdem wird die Wartezeit zwischen den
     * einzelnen Zuegen so eingestellt, dass die insgesamt benoetigte Zeit bis
     * zu einer gewissen Anzahl an Scheiben moeglichst konstant bleibt.
     *
     * @param disks Die Anzahl an Scheiben auf dem ersten Stapel.
     */
    public void setDisks(final int disks) {
        firstStack.clear();
        secondStack.clear();
        thirdStack.clear();
        sleepTime = 5000 / Math.round(Math.pow(2, disks) - 1);
        for (int i = disks; i > 0; i--) {
            firstStack.push(i);
        }
        update(getGraphics());
    }

    /**
     * Setzt die Flagge, ob der laufende Thread gestoppt werden soll.
     *
     * @param isStopped Der neue Wert der Flagge.
     */
    public void setStopped(final boolean isStopped) {
        this.isStopped = isStopped;
    }

    /**
     * Diese Methode wird rekursiv aufgerufen, um alle Scheiben von einem zum
     * anderen Stapel zu bewegen.
     *
     * @param disks Die Anzahl der Scheiben, die zu verschieben sind.
     * @param start Der Stapel, von dem die Scheiben genommen werden sollen.
     * @param end   Der Stapel, auf den die Scheiben gelegt werden sollen.
     * @param other Der andere Stapel.
     */
    public void solve(final int disks, final int start, final int end, final int other) {
        if (disks > 0 && !isStopped) {
            solve(disks - 1, start, other, end);
            moveDisk(start, end);
            solve(disks - 1, other, end, start);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void update(final Graphics graphics) {
        if (dbImage == null) {
            dbImage = createImage(getSize().width, getSize().height);
            dbGraphics = dbImage.getGraphics();
        }
        dbGraphics.clearRect(0, 0, getSize().width, getSize().height);
        paint(dbGraphics);
        graphics.drawImage(dbImage, 0, 0, this);
    }
}
