import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JOptionPane;

public final class JuliaKeyListener extends KeyAdapter {

    private final JuliaFrame frame;
    private final JuliaLogic logic;
    private final JuliaPanel panel;

    public JuliaKeyListener(final JuliaFrame frame, final JuliaPanel panel) {
        logic = new JuliaLogic();
        this.frame = frame;
        this.panel = panel;
    }

    @Override
    public void keyPressed(final KeyEvent event) {
        final int key = event.getKeyCode();
        if (key == KeyEvent.VK_PLUS) {
            final double[] positions = { panel.getMinX(), panel.getMaxX(),
                                        panel.getMinY(), panel.getMaxY() };
            final double[] newPositions = logic.zoomIn(positions);
            panel.setMinX(newPositions[0]);
            panel.setMaxX(newPositions[1]);
            panel.setMinY(newPositions[2]);
            panel.setMaxY(newPositions[3]);
            panel.repaint();
        } else if (key == KeyEvent.VK_MINUS) {
            final double[] positions = { panel.getMinX(), panel.getMaxX(),
                                        panel.getMinY(), panel.getMaxY() };
            final double[] newPositions = logic.zoomOut(positions);
            panel.setMinX(newPositions[0]);
            panel.setMaxX(newPositions[1]);
            panel.setMinY(newPositions[2]);
            panel.setMaxY(newPositions[3]);
            panel.repaint();
        } else if (key == KeyEvent.VK_UP) {
            final double[] positions = { panel.getMinY(), panel.getMaxY() };
            final double[] newPositions = logic.move(positions, 1);
            panel.setMinY(newPositions[0]);
            panel.setMaxY(newPositions[1]);
            panel.repaint();
        } else if (key == KeyEvent.VK_DOWN) {
            final double[] positions = { panel.getMinY(), panel.getMaxY() };
            final double[] newPositions = logic.move(positions, -1);
            panel.setMinY(newPositions[0]);
            panel.setMaxY(newPositions[1]);
            panel.repaint();
        } else if (key == KeyEvent.VK_LEFT) {
            final double[] positions = { panel.getMinX(), panel.getMaxX() };
            final double[] newPositions = logic.move(positions, 1);
            panel.setMinX(newPositions[0]);
            panel.setMaxX(newPositions[1]);
            panel.repaint();
        } else if (key == KeyEvent.VK_RIGHT) {
            final double[] positions = { panel.getMinX(), panel.getMaxX() };
            final double[] newPositions = logic.move(positions, -1);
            panel.setMinX(newPositions[0]);
            panel.setMaxX(newPositions[1]);
            panel.repaint();
        } else if (key == KeyEvent.VK_COMMA) {
            final int maxIterations = panel.getMaxIterations();
            final int newMaxIterations = logic
                    .changeMaxIterations(maxIterations, 1);
            panel.setMaxIterations(newMaxIterations);
            panel.repaint();
        } else if (key == KeyEvent.VK_PERIOD) {
            final int maxIterations = panel.getMaxIterations();
            final int newMaxIterations = logic
                    .changeMaxIterations(maxIterations, -1);
            panel.setMaxIterations(newMaxIterations);
            panel.repaint();
        } else if (key == KeyEvent.VK_ENTER) {
            try {
                final double newCX = Double.parseDouble(JOptionPane
                        .showInputDialog(frame, "Neues cx (bisher " +
                                                panel.getCx() + "): "));
                final double newCY = Double.parseDouble(JOptionPane
                        .showInputDialog(frame, "Neues cy (bisher " +
                                                panel.getCy() + "): "));
                panel.setCx(newCX);
                panel.setCy(newCY);
                panel.repaint();
            } catch (NumberFormatException e) {
                JOptionPane
                        .showMessageDialog(frame,
                                           "Konnte nicht ausgefuehrt werden.");
            } catch (NullPointerException e) {

            }
        } else if (key == KeyEvent.VK_1) {
            panel.setColorstyle(1);
            panel.repaint();
        } else if (key == KeyEvent.VK_2) {
            panel.setColorstyle(2);
            panel.repaint();
        } else if (key == KeyEvent.VK_3) {
            panel.setColorstyle(3);
            panel.repaint();
        }
    }
}
