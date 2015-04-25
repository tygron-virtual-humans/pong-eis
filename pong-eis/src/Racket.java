/**
 * Pong in JAVA
 * Base source: http://codereview.stackexchange.com/questions/27197/pong-game-in-java
 * Modified for example eis project.
 */


import java.awt.Graphics;
import java.awt.Rectangle;

public class Racket {
    private static final int WIDTH = 10, HEIGHT = 60;
    private Pong game;
    private int up, down;
    private int x;
    private int y, ya;

    public Racket(Pong game, int up, int down, int x) {
        this.game = game;
        this.x = x;
        y = game.getHeight() / 2;
        this.up = up;
        this.down = down;
    }

    public void update() {
        if (y > 0 && y < game.getHeight() - HEIGHT - 29)
            y += ya;
        else if (y <= 0)
            y++;
        else if (y >= game.getHeight() - HEIGHT - 29)
            y--;
    }

    public void pressed(int keyCode) {
        if (keyCode == up)
            ya = -2;
        else if (keyCode == down)
            ya = 2;
    }

    public void released(int keyCode) {
        if (keyCode == up || keyCode == down)
            ya = 0;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, WIDTH, HEIGHT);
    }

    public void paint(Graphics g) {
        g.fillRect(x, y, WIDTH, HEIGHT);
    }
}