package eishub.pong;

/**
 * Pong in JAVA
 * Base source: http://codereview.stackexchange.com/questions/27197/pong-game-in-java
 * Modified for example eis project.
 */


import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JOptionPane;

public class Ball {
    private static final int WIDTH = 30, HEIGHT = 30;
    private Pong game;
    private int x, y, xa = 2, ya = 2;

    public Ball(Pong game) {
        this.game = game;
        x = game.getWidth() / 2;
        y = game.getHeight() / 2;
    }

    public void update() {
        x += xa;
        y += ya;
        if (x < 0) {
            game.increaseScore(1);
            x = game.getWidth() / 2;
            xa = -xa;
        }
        else if (x > game.getWidth() - WIDTH - 7) {
            game.increaseScore(2);
            x = game.getWidth() / 2;
            xa = -xa;
        }
        else if (y < 0 || y > game.getHeight() - HEIGHT - 29)
            ya = -ya;
        if (game.getScore(1) == 10)
            JOptionPane.showMessageDialog(null, "Player 1 wins", "Pong", JOptionPane.PLAIN_MESSAGE);
        else if (game.getScore(2) == 10)
            JOptionPane.showMessageDialog(null, "Player 2 wins", "Pong", JOptionPane.PLAIN_MESSAGE);
        checkCollision();
    }

    public void checkCollision() {
        if (game.getPlayer(1).getBounds().intersects(getBounds()) || game.getPlayer(2).getBounds().intersects(getBounds()))
            xa = -xa;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, WIDTH, HEIGHT);
    }

    public void paint(Graphics g) {
        g.fillRect(x, y, WIDTH, HEIGHT);
    }
}