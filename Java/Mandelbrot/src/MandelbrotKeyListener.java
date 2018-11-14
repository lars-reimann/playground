import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public final class MandelbrotKeyListener extends KeyAdapter {

    private final MandelbrotLogic logic;
    private final MandelbrotPanel panel;

    public MandelbrotKeyListener(final MandelbrotPanel panel) {
        logic = new MandelbrotLogic();
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
