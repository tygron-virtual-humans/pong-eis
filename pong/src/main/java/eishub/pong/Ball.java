package eishub.pong;

/**
 * Pong in JAVA
 * Base source: http://codereview.stackexchange.com/questions/27197/pong-game-in-java
 * Modified for example eis project.
 */


import java.awt.Graphics;
import java.awt.Rectangle;

public class Ball {
    private static final int WIDTH = 15, HEIGHT = 15;
    private Pong game;
    private int x, y, xa = 2, ya = 2;

    /**
     * Ball constructor, ball starts in the center.
     * @param game
     */
    public Ball(Pong game) {
        this.game = game;
        x = game.getWidth() / 2;
        y = game.getHeight() / 2;
    }
    
    /**
     * Return the horizontal position
     * @return
     */
    public int getPositionX(){
    	return x;
    }
    
    /**
     * Return the vertical position
     * @return
     */
    public int getPositionY(){
    	return y;
    }

    /**
     * Ball tick frame
     */
    public void update() {
        x += xa;
        y += ya;
        if (x < 0) {
            game.increaseScore(2);
            x = game.getWidth() / 2;
            xa = -xa;
        }else if (x > game.getWidth() - WIDTH - 7) {
            game.increaseScore(1);
            x = game.getWidth() / 2;
            xa = -xa;
        }else if (y < 0 || y > game.getHeight() - HEIGHT - 29){
            ya = -ya;
        }     
        
        if (game.getScore(1) == 2 || game.getScore(2) == 2){
        	game.setGameState(false);
        }
        checkCollision();
    }

    /**
     * Check for collisions with a player, if this happens, invert horizontal speed.
     */
    public void checkCollision() {
        if (game.getPlayer(1).getBounds().intersects(getBounds()) || game.getPlayer(2).getBounds().intersects(getBounds()))
            xa = -xa;
    }

    /**
     * Return the bounds / rect of the ball object.
     * @return
     */
    public Rectangle getBounds() {
        return new Rectangle(x, y, WIDTH, HEIGHT);
    }

    /**
     * Paint function of the ball.
     * @param g
     */
    public void paint(Graphics g) {
        g.fillOval(x, y, WIDTH, HEIGHT);
    }
}