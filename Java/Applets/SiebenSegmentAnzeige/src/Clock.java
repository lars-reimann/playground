import java.applet.Applet;
import java.util.GregorianCalendar;

/**
 * A simple clock that shows the current hour, minute and second using a
 * seven segment display.
 *
 * @author Lars Reimann
 * @version 20. August 2010
 */
public final class Clock extends Applet
implements Runnable {


    // -----Class variables----------------------------------------------------

    /**
     * Serial version id.
     */
    private static final long serialVersionUID = -315940609723608756L;


    // -----Instance variables-------------------------------------------------

    /**
     * The current hour.
     */
    private transient int          hour;

    /**
     * Used to stop the while-loop in the run() method.
     */
    private transient boolean      isStopped = false;

    /**
     * The current minute.
     */
    private transient int          minute;

    /**
     * The current second.
     */
    private transient int          second;

    /**
     * This array contains references to the six {@code SegDisplay} objects.
     * Each one represents one part of the clock.
     */
    private transient SegDisplay[] segDisplays = new SegDisplay[6];


    // -----Instance methods---------------------------------------------------

    /**
     * Called when the program is loaded for the first time. It places the
     * different components and sets the time that should be displayed.
     */
    public void init() {
        setTime();

        // Hour
        add(segDisplays[0]);
        add(segDisplays[1]);
        add(new Colon());

        // Minute
        add(segDisplays[2]);
        add(segDisplays[3]);
        add(new Colon());

        // Second
        add(segDisplays[4]);
        add(segDisplays[5]);
        setVisible(true);
    }

    /**
     * Contains the algorithm of the animation.
     */
    public void run() {
        while (!isStopped) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException exception) {
                // ignore
            }
            second = (second + 1) % 60;
            if (second == 0) {
                minute = (minute + 1) % 60;
                if (minute == 0) {
                    hour = (hour + 1) % 24;
                }
            }
            segDisplays[5].setDigit(second % 10);
            segDisplays[4].setDigit((int) (second / 10));
            segDisplays[3].setDigit(minute % 10);
            segDisplays[2].setDigit((int) (minute / 10));
            segDisplays[1].setDigit(hour % 10);
            segDisplays[0].setDigit((int) (hour / 10));
        }
    }

    /**
     * If the parameter isStopped is true the while-loop in the run()-method
     * will stop. Else it will continue to operate.
     *
     * @param isStopped indicates whether the program is stopped.
     */
    public void setStopped(final boolean isStopped) {
        this.isStopped = isStopped;
    }

    /**
     * Sets the time that should be displayed to the current time. This is not
     * 100% accurate, but the divergence should be within a second.
     */
    public void setTime() {
        final GregorianCalendar calendar = new GregorianCalendar();
        second = calendar.get(GregorianCalendar.SECOND);
        minute = calendar.get(GregorianCalendar.MINUTE);
        hour   = calendar.get(GregorianCalendar.HOUR_OF_DAY);

        segDisplays[5] = new SegDisplay(second % 10);
        segDisplays[4] = new SegDisplay((int) (second / 10));
        segDisplays[3] = new SegDisplay(minute % 10);
        segDisplays[2] = new SegDisplay((int) (minute / 10));
        segDisplays[1] = new SegDisplay(hour % 10);
        segDisplays[0] = new SegDisplay((int) (hour / 10));
    }

    /**
     * Starts the animation of the display.
     */
    public void start() {
        final Thread thread = new Thread(this);
        thread.start();
    }

    /**
     * Stops the animation of the display.
     */
    public void stop() {
        setStopped(true);
    }
}
