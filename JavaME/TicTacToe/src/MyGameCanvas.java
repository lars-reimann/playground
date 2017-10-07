import java.util.Random;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.game.GameCanvas;

public class MyGameCanvas extends GameCanvas implements Runnable {

    private static final int AI               = 10;
    private static final int AI_WINS          = 1;
    private static final int DRAW             = 3;
    private static final int EMPTY            = 0;
    private static final int PLAYER           = 1;
    private static final int PLAYER_WINS      = 0;
    private static final int STILL_GOING      = 2;

    private int              AIMoveX;
    private int              AIMoveY;
    private int[][]          field            = new int[3][3];
    private boolean          playerShouldMove = true;

    public MyGameCanvas() {
        super(false);
    }

    public void paint(final Graphics graphics) {
        graphics.fillRect(0, 0, getWidth(), getHeight());
        graphics.setColor(255, 255, 255);
        for (int i = 1; i < 3; i++) {
            for (int j = 1; j < 3; j++) {
                graphics.drawLine(0, i * getHeight() / 3, getWidth(),
                                  i * getHeight() / 3);
                graphics.drawLine(i * getWidth() / 3, 0, i * getWidth() / 3,
                                  getHeight());
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (field[i][j] == PLAYER) {
                    graphics.setColor(100, 100, 100);
                    graphics.fillArc(i * getWidth() / 3 + 1, j * getHeight() /
                                                             3 + 1,
                                     getWidth() / 3 - 2, getHeight() / 3 - 2,
                                     0, 360);
                    graphics.setColor(150, 150, 150);
                    graphics.drawArc(i * getWidth() / 3 + 1, j * getHeight() /
                                                             3 + 1,
                                     getWidth() / 3 - 2, getHeight() / 3 - 2,
                                     0, 360);
                } else if (field[i][j] == AI) {
                    graphics.setColor(200, 200, 200);
                    graphics.fillArc(i * getWidth() / 3 + 1, j * getHeight() /
                                                             3 + 1,
                                     getWidth() / 3 - 2, getHeight() / 3 - 2,
                                     0, 360);
                    graphics.setColor(150, 150, 150);
                    graphics.drawArc(i * getWidth() / 3 + 1, j * getHeight() /
                                                             3 + 1,
                                     getWidth() / 3 - 2, getHeight() / 3 - 2,
                                     0, 360);
                }
            }
        }

    }

    public void run() {
        maxMove(0, field);
        field[AIMoveX][AIMoveY] = AI;
        repaint();
        playerShouldMove = true;
    }

    protected void keyPressed(int keyCode) {
        if (playerShouldMove && getFinished() == STILL_GOING) {
            if (keyCode == KEY_NUM1 && field[0][0] == EMPTY) {
                processPlayerMove(0, 0);
            } else if (keyCode == KEY_NUM2 && field[1][0] == EMPTY) {
                processPlayerMove(1, 0);
            } else if (keyCode == KEY_NUM3 && field[2][0] == EMPTY) {
                processPlayerMove(2, 0);
            } else if (keyCode == KEY_NUM4 && field[0][1] == EMPTY) {
                processPlayerMove(0, 1);
            } else if (keyCode == KEY_NUM5 && field[1][1] == EMPTY) {
                processPlayerMove(1, 1);
            } else if (keyCode == KEY_NUM6 && field[2][1] == EMPTY) {
                processPlayerMove(2, 1);
            } else if (keyCode == KEY_NUM7 && field[0][2] == EMPTY) {
                processPlayerMove(0, 2);
            } else if (keyCode == KEY_NUM8 && field[1][2] == EMPTY) {
                processPlayerMove(1, 2);
            } else if (keyCode == KEY_NUM9 && field[2][2] == EMPTY) {
                processPlayerMove(2, 2);
            }
        } else if (getFinished() != STILL_GOING) {
            reset();
        }
    }

    private void getAIMove() {
        if (getFinished() == STILL_GOING) {
            Thread thread = new Thread(this);
            thread.start();
        }
    }

    private int getFinished() {
        if (isWon(PLAYER)) {
            return PLAYER_WINS;
        } else if (isWon(AI)) {
            return AI_WINS;
        } else if (isFinished()) {
            return DRAW;
        } else {
            return STILL_GOING;
        }
    }

    private boolean isFinished() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (field[i][j] == EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isWon(final int value) {
        for (int i = 0; i < 3; i++) {
            if (field[i][0] + field[i][1] + field[i][2] == value * 3 ||
                field[0][i] + field[1][i] + field[2][i] == value * 3) {
                return true;
            }
        }
        if (field[0][0] + field[1][1] + field[2][2] == value * 3 ||
            field[2][0] + field[1][1] + field[0][2] == value * 3) {
            return true;
        }
        return false;
    }

    private int maxMove(final int depth, final int[][] field) {
        if (isWon(AI)) {
            return 1;
        } else if (isWon(PLAYER)) {
            return -1;
        } else if (isFinished()) {
            return 0;
        } else {
            int best = -2;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (field[i][j] == EMPTY) {
                        field[i][j] = AI;
                        int value = minMove(depth + 1, field);
                        field[i][j] = EMPTY;
                        if (value > best) {
                            best = value;
                            if (depth == 0) {
                                AIMoveX = i;
                                AIMoveY = j;
                            }
                            if (best == 1) {
                                return 1;
                            }
                        }
                    }
                }
            }
            return best;
        }
    }

    private int minMove(final int depth, final int[][] field) {
        if (isWon(AI)) {
            return 1;
        } else if (isWon(PLAYER)) {
            return -1;
        } else if (isFinished()) {
            return 0;
        } else {
            int best = 2;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (field[i][j] == EMPTY) {
                        field[i][j] = PLAYER;
                        int value = maxMove(depth + 1, field);
                        field[i][j] = EMPTY;
                        if (value < best) {
                            best = value;
                            if (best == -1) {
                                return -1;
                            }
                        }
                    }
                }
            }
            return best;
        }
    }

    private void processPlayerMove(final int i, final int j) {
        field[i][j] = PLAYER;
        repaint();
        playerShouldMove = false;
        getAIMove();
    }

    private void reset() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = EMPTY;
            }
        }
        Random random = new Random();
        final int value = random.nextInt(2);
        if (value == 0) {
            playerShouldMove = true;
        } else {
            playerShouldMove = false;
            getAIMove();
        }
        repaint();
    }
}