import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public final class SierpinskiKeyListener extends KeyAdapter {

    private final SierpinskiLogic logic;
    private final SierpinskiPanel panel;

    public SierpinskiKeyListener(final SierpinskiPanel panel) {
        logic = new SierpinskiLogic();
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
        }
    }
}
