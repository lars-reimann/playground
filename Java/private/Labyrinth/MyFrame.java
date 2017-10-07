import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * Erstellt ein Fenster, welches zwei Buttons und ein Gitter mit zwei gegebenen Punkten
 * beinhaltet. Nach dem Druecken auf den "Start"-Button wird rekursiv eine Verbindung
 * zwischen den beiden Startpukten bestimmt und diese in rot eingezeichnet.
 *
 * @author Lars Reimann
 * @version 09.12.2010
 */
public class MyFrame extends JFrame {


    // ----Instanzvariablen-----------------------------------------------------

    /**
     * Die x-Positionen der Verbindungsstücke.
     */
    private ArrayList<Integer> xPos = new ArrayList<Integer>();

    /**
     * Die x-Position des ersten Startpunktes.
     */
    private int xPos1 = 0;

    /**
     * Die x-Position des zweiten Startpunktes.
     */
    private int xPos2 = 0;

    /**
     * Die y-Positionen der Verbindungsstücke.
     */
    private ArrayList<Integer> yPos = new ArrayList<Integer>();

    /**
     * Die y-Position des ersten Startpunktes.
     */
    private int yPos1 = 0;

    /**
     * Die y-Position des zweiten Startpunktes.
     */
    private int yPos2 = 0;


    // ----Konstruktoren-------------------------------------------------------

    /**
     * Dieser Konstruktor erstellt ein 500*600 Pixel großes Fenster, welches
     * ein 10*10 Felder großes Gitter mit zwei zufälligen Startpunkten enthält.
     * Unter diesem Gitter befinden sich zwei Button, einer zu Starten der
     * Wegfindung und ein anderer um neue Startpunkte zu bestimmen.
     */
    private MyFrame() {
        getContentPane().setLayout(null);
        createRandomPoints();

        // "Start"-Button
        final JButton start = new JButton("Start");
        start.setBounds(125, 500, 125, 50);
        start.addActionListener(
            new ActionListener() {
                public void actionPerformed(final ActionEvent event) {
                    findConnection(xPos1, yPos1, xPos2, yPos2);
                    repaint();
                }
            }
        );
        start.setActionCommand("Start");
        getContentPane().add(start);

        // "Neue Punkte"-Button
        final JButton newPoints = new JButton("Neue Punkte");
        newPoints.setBounds(250, 500, 125, 50);
        newPoints.addActionListener(
            new ActionListener() {
                public void actionPerformed(final ActionEvent event) {
                    createRandomPoints();
                    repaint();
                }
            }
        );
        start.setActionCommand("Neue Punkte");
        getContentPane().add(newPoints);

        setSize(500, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }


    // ----Klassenmethoden-----------------------------------------------------

    /**
     * Erstellt eine neue Instanz der Klasse MyFrame und erzeugt damit das
     * Fenster.
     *
     * @param args Standardparameter
     */
    public static void main(final String[] args) {
        new MyFrame();
    }


    // -----Instanzmethoden----------------------------------------------------

    /**
     * Zeichnet das Gitter, die beiden Startpunkte und die Verbindung dieser
     * Punkte.
     *
     * @param graphics Grafikkontext des Fensters
     */
    public void paint(final Graphics graphics) {

        //Gitter
        for (int i = 0; i <= 10; i++) {
            graphics.drawLine(50 + i * 40, 50, 50 + i * 40, 450);
            graphics.drawLine(50, 50 + i * 40, 450, 50 + i * 40);
        }

        // Startpunkte
        graphics.fillRect(51 + xPos1 * 40, 51 + yPos1 * 40, 39, 39);
        graphics.fillRect(51 + xPos2 * 40, 51 + yPos2 * 40, 39, 39);

        // Verbindung
        graphics.setColor(Color.red);
        for (int i = 0; i < xPos.size(); i++) {
            graphics.fillRect(51 + xPos.get(i) * 40,
                              51 + yPos.get(i) * 40, 39, 39);
        }
    }

    /**
     * Löscht das Bild und zeichnet es neu.
     */
    public void repaint() {
        getGraphics().clearRect(0, 0, 500, 450);
        paint(getGraphics());
    }

    /**
     * Erzeugt zwei neue Startpunkte.
     */
    private void createRandomPoints() {

        // Zurücksetzen aller Positionen
        xPos1 = 0;
        yPos1 = 0;
        xPos2 = 0;
        yPos2 = 0;
        xPos.clear();
        yPos.clear();

        // Erzeugen neuer Startpunkte
        final Random random = new Random();
        while (xPos1 == xPos2 && yPos1 == yPos2) {
            xPos1 = random.nextInt(10);
            yPos1 = random.nextInt(10);
            xPos2 = random.nextInt(10);
            yPos2 = random.nextInt(10);
        }
    }

    /**
     * Findet die Verbindung zwischen den beiden Startpunkten durch ein
     * rekursives Verfahren.
     *
     * @param xPos1 x-Position des ersten Punktes.
     * @param yPos1 y-Position des zweiten Punktes.
     * @param xPos2 x-Position des ersten Punktes.
     * @param yPos2 y-Position des zweiten Punktes.
     */
    private void findConnection(final int xPos1, final int yPos1,
                                final int xPos2, final int yPos2) {

        if (Math.abs(xPos1 - xPos2) > 1 || Math.abs(yPos1 - yPos2) > 1) {

            // Punkt zwischen den beiden anderen
            final int xPos12 = (xPos1 + xPos2) / 2;
            final int yPos12 = (yPos1 + yPos2) / 2;

            // Speichern dieses Punktes
            xPos.add(xPos12);
            yPos.add(yPos12);

            // Rekursives Aufrufen dieser Methode, um die Verbindung zwischen
            // dem erzeugten Punkt und dem ersten und zweiten Startpunkt auf
            // die gleiche Art zu finden.
            findConnection(xPos1, yPos1, xPos12, yPos12);
            findConnection(xPos12, yPos12, xPos2, yPos2);
        }
    }
}
