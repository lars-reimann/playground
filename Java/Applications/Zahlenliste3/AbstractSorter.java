public abstract class AbstractSorter implements Runnable{

    // -----Instanzvariablen---------------------------------------------------

    /**
     * Die Instanz der Klasse {@code IntArrayGUI}.
     */
    protected final IntArrayGUI intArrayGUI = IntArrayGUI.getInstance();

    /**
     * Die Liste der Zufallszahlen.
     */
    protected final int[]       zahlenliste = intArrayGUI.getZahlenliste();


    // -----Instanzmethoden----------------------------------------------------

    /**
     * Das Programm haelt fuer ein bestimmte vorgegebene Zeit an, um das
     * Vertauschen der verschiedenen Zufallszahlen beim Sortieren besser
     * sichtbar zu machen.
     *
     * @param millis Zeit in Millisekunden, die gewartet werden soll
     */
    protected final void sleep(final long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException exception) {
            // ignoriere Ausnahme
        }
    }

    /**
     * Tausche zwei Elemente in der Zahlenliste!
     *
     * @param index1 Index des ersten Elementes.
     * @param index2 Index des zweiten Elementes.
     */
    protected final void swap(final int index1, final int index2) {
        final int temp = zahlenliste[index1];
        zahlenliste[index1] = zahlenliste[index2];
        zahlenliste[index2] = temp;
        intArrayGUI.refresh(index1, index2);
    }
}
