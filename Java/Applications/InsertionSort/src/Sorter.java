/**
 * Diese Klasse beinhaltet die eigentliche Implementierung des Sortierverfahrens
 * InsertionSort. Es ist moeglich, Schritt fuer Schritt durch das Programm zu
 * laufen und so das Verfahren besser zu verstehen. Um dies zu erreichen
 * beinhaltet die Klasse eine Variable, die den naechsten auszufuehrenden
 * Schritt repraesentiert. Abhaengig vom Zustand dieser Variablen wird dann im
 * Algorithmus fortgefahren.
 * 
 * @author Lars Reimann
 * @version 30. Mai 2011
 */
public final class Sorter {

    /**
     * Die Zahlenliste, die sortiert werden soll.
     */
    private final Entry[] entries;

    /**
     * Die Position, auf der der Einsetzungszeiger steht.
     */
    private int insertPos;

    /**
     * Der gerade abgespeicherte Schluessel.
     */
    private int memory;

    /**
     * Die Position, auf der der Elementzeiger steht.
     */
    private int selectedPos;

    /**
     * Der naechste auszufuehrende Schritt.
     */
    private int step;

    /**
     * Konstruiert einen neuen Sortierer, der die uebergebene Zahlenliste
     * sortieren und als naechstes den 0. Schritt ausfuehren soll.
     * 
     * @param entries
     *            Die zu sortierende Zahlenliste.
     */
    public Sorter(final Entry[] entries) {
        this.entries = entries;
        step = 0;
    }

    /**
     * Gibt den naechsten auszufuehrenden Schritt zurueck.
     * 
     * @return Den als naechstes auszufuehrenden Schritt.
     */
    public int getStep() {
        return step;
    }

    /**
     * Fuehrt den 0. Schritt aus.<br>
     * Der Elementzeiger wird auf die Position 1 (zweites Element) gesetzt. Dann
     * wird dem Eintrag an dieser Stelle mitgeteilt, dass momentan dieser Zeiger
     * auf ihm steht, was im Diagramm durch den roten Strich unter diesem
     * Eintrag gezeigt wird. Im Anschluss soll der 1. Schritt ausgefuehrt
     * werden.<br>
     * Im urspruenglichen Algorithmus entspricht dies dem Initialisieren des
     * Elementzeigers auf das zweite Element.
     */
    private void performStep0() {
        selectedPos = 1;
        entries[selectedPos].setIsSelectedPos(true);
        step = 1;
    }

    /**
     * Fuehrt den 1. Schritt aus.<br>
     * Wenn der Elementzeiger auf ein existierendes Element deutet (Wert kleiner
     * als 8), so soll mit dem zweiten Schritt fortgefahren werden. Sonst wird
     * dem momentan durch den Elementzeiger referenzierten Element mitgeteilt,
     * dass dieser Zeiger nicht mehr auf ihm steht. Dementsprechen wird im
     * Diagramm auch kein solcher Zeiger mehr gezeichnet. Anschliessend wird die
     * Variable step auf -1 gesetzt, was anzeigt dass das Verfahren beendet ist.<br>
     * Im urspruenglichen Algorithmus entspricht dies der Abbruchbedingung der
     * aeusseren Schleife.
     */
    private void performStep1() {
        if (selectedPos >= 8) {
            entries[selectedPos].setIsSelectedPos(false);
            step = -1;
        } else {
            step = 2;
        }
    }

    /**
     * Fuehrt den 2. Schritt aus.<br>
     * Setzt den Einsetzungszeiger auf die Position des Elementzeigers.
     * Anschliessend wird dies dem entsprechenden Element mitgeteilt, sodass im
     * Diagramm unter dem Balken ein gruener Strich dargestellt wird. Dann wird
     * mit Schritt 3 fortgefahren.<br>
     * Im urspruenglichen Algorithmus entspricht dies dem Initialisieren des
     * Einsetzungszeigers.
     */
    private void performStep2() {
        insertPos = selectedPos;
        entries[selectedPos].setIsInsertPos(true);
        step = 3;
    }

    /**
     * Fuehrt den 3. Schritt aus.<br>
     * Speichert den Schluessel des Elementes an der Stelle des Elementzeigers
     * ab und bewirkt, dass an dieser Stelle im Diagramm kein Balken mehr
     * angezeigt wird. Stattdessen wird der gemerkte Schluessel auf der ganz
     * rechten Position im Diagramm abgelegt. Danach wird Schritt 4 ausgefuehrt.<br>
     * Im urspruenglichen Algorithmus entspricht dies dem Abspeichern des
     * referenzierten Elementes.
     */
    private void performStep3() {
        memory = entries[selectedPos].getKey();
        entries[selectedPos].setKey(0);
        entries[9].setKey(memory);
        step = 4;
    }

    /**
     * Fuehrt den 4. Schritt aus.<br>
     * Wenn sich links des Einsetzungszeiger noch ein weiterer Eintrag befindet
     * und der Schluessel dieses Eintrages groesser als der gemerkte ist, so
     * soll mit Schritt 5 fortgefahren werden. Sonst ist mit dem 7. Schritt
     * fortzufahren.<br>
     * Im urspruenglichen Algorithmus entspricht dies der Abbruchbedingung der
     * inneren Schleife.
     */
    private void performStep4() {
        if (insertPos <= 0 || entries[insertPos - 1].getKey() <= memory) {
            step = 7;
        } else {
            step = 5;
        }
    }

    /**
     * Fuehrt den 5. Schritt aus.<br>
     * Bewirkt, dass das Element links des Einsetzungszeigers eine Position nach
     * rechts verschoben wird und der Schluessel der urspruenglichen Position
     * auf 0 gesetzt wird, so dass sich dort nun kein Balken im Diagramm mehr
     * befindet. Im Anschluss soll Schritt 6 ausgefuehrt werden.<br>
     * Im urspruenglichen Algorithmus entspricht dies dem Verschieben des
     * Elementes links des Einsetzungszeigers um eine Position nach rechts.
     */
    private void performStep5() {
        entries[insertPos].setKey(entries[insertPos - 1].getKey());
        entries[insertPos - 1].setKey(0);
        step = 6;
    }

    /**
     * Fuehrt den 6. Schritt aus.<br>
     * Hier wird der Einsetzungszeiger sowohl innerhalb des Sortierers als auch
     * in der Grafik um eine Position nach links verschoben. Dann soll mit dem
     * 4. Schritt fortgefahren werden.<br>
     * Im urspruenglichen Algorithmus entspricht dies dem Verschieben des
     * Einsetzungszeigers um eins nach links.
     */
    private void performStep6() {
        entries[insertPos].setIsInsertPos(false);
        entries[--insertPos].setIsInsertPos(true);
        step = 4;
    }

    /**
     * Fuehrt den 7. Schritt aus.<br>
     * Bewirkt das Einsetzen des gemerkten Schluessels an die Position des
     * Einsetzungszeigers. Dem Speicherplatz ganz rechts wird dann ein
     * Schluessel von 0 zugewiesen, so dass dort nichts mehr angezeigt wird.
     * Danach wird Schritt 8 ausgefuehrt.<br>
     * Im urspruenglichen Algorithmus entspricht dies dem Einsetzen des
     * gemerkten Elementes an die durch den Einsetzungszeiger referenzierte
     * Stelle.
     */
    private void performStep7() {
        entries[insertPos].setKey(memory);
        entries[9].setKey(0);
        step = 8;
    }

    /**
     * Fuehrt den 8. Schritt aus.<br>
     * Die bisherigen grafischen Darstellungen der beiden Zeiger werden
     * geloescht und danach wird der Elementzeiger um eine Position nach rechts
     * verschoben (sowohl innerhalb dieser Klasse als auch im Diagramm). Dann
     * wird der 1. Schritt ausgefuehrt.<br>
     * Im urspruenglichen Algorithmus entspricht dies der Verschiebung der
     * Elementzeigers um eine Position nach rechts.
     */
    private void performStep8() {
        entries[insertPos].setIsInsertPos(false);
        entries[selectedPos].setIsSelectedPos(false);
        entries[++selectedPos].setIsSelectedPos(true);
        step = 1;
    }

    /**
     * Setzt die Variable step (naechster auszufuehrender Schritt) auf den
     * uebergebenen Wert. So kann zum Beispiel das Zuruecksetzen des Algorithmus
     * bewirkt werden.
     * 
     * @param step
     *            Der als naechstes auszufuehrende Schritt.
     * @throws IllegalArgumentException
     */
    public void setStep(final int step) throws IllegalArgumentException {
        if (step < -1 || step > 8) {
            throw new IllegalArgumentException();
        } else {
            this.step = step;
        }
    }

    /**
     * Diese Methode fuehrt abhaengig vom Zustand der Variablen step den
     * naechsten Schritt im Algorithmus aus.
     */
    public void sort() {
        switch (step) {
            case 0:
                performStep0();
                break;
            case 1:
                performStep1();
                break;
            case 2:
                performStep2();
                break;
            case 3:
                performStep3();
                break;
            case 4:
                performStep4();
                break;
            case 5:
                performStep5();
                break;
            case 6:
                performStep6();
                break;
            case 7:
                performStep7();
                break;
            case 8:
                performStep8();
                break;
        }
    }
}

