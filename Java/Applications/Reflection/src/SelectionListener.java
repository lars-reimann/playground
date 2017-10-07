import java.lang.reflect.InvocationTargetException;

import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class SelectionListener implements ListSelectionListener {

    public void valueChanged(final ListSelectionEvent event) {
        if (event.getValueIsAdjusting()) {
            JList list = (JList) event.getSource();
            try {
                Utilities.class.getMethod((String) list.getSelectedValue(), new Class[0]).invoke(Utilities.getInstance());
            } catch (SecurityException e) {
                // ignoriere
            } catch (NoSuchMethodException e) {
                // ignoriere
            } catch (IllegalArgumentException e) {
                // ignoriere
            } catch (IllegalAccessException e) {
                // ignoriere
            } catch (InvocationTargetException e) {
                // ignoriere
            } catch (NullPointerException e) {
                // ignoriere
            }
            list.clearSelection();
        }
    }
}
