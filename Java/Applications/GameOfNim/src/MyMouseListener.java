import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MyMouseListener extends MouseAdapter {

    public void mousePressed(final MouseEvent event) {
        int row = (int) (event.getY() / 40);
        int col = (int) (event.getX() / 40);
        int rowNo = 5;
        if (row < rowNo) {
            int colNo = GameOfNim.ROWS[row];
            if (col < colNo) {
                GameOfNim.ROWS[row] = col;
                GameOfNim.changedRow = row;
                Component component = (Component) event.getSource();
                component.repaint();
            }
        }
        if (GameOfNim.isWon()) {
            if (GameOfNim.player == 0) {
                System.out.println("Der Computer gewinnt");
            } else {
                System.out.println("Sie haben gewonnen");
            }
        } else {
            GameOfNim.player = (GameOfNim.player + 1) % 2;
        }
    }
}
