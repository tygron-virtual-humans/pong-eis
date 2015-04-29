package eishub.pong;
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
    private int x;
    private int y, ya;

    public Racket(Pong game, int x) {
        this.game = game;
        this.x = x;
        y = game.getHeight() / 2 - HEIGHT/2;
    }

    /**
     * Update Tick
     */
    public void update() {
        if (y > 0 && y < game.getHeight() - HEIGHT - 29){
            y += ya;
        }else if (y <= 0){
            y++;
        }else if (y >= game.getHeight() - HEIGHT - 29){
            y--;
        }
    }
    
    /**
     * Move player 1 unit up.
     * @param keyCode
     */
    public void moveUp(){
    	ya = -2;
    }
    
    /**
     * Move player 1 unit down.
     * @param keyCode
     */
    public void moveDown(){
    	ya = 2;
    }
    
    /**
     * Stop player movement
     * @param keyCode
     */
    public void moveStop(){
    	ya = 0;
    }    
   
    /**
     * This returns the vertical position of the player
     * @return
     */
    public int getPosition(){
    	return y;
    }
    
    /**
     * Get Racket bounds
     * @return
     */
    public Rectangle getBounds() {
        return new Rectangle(x, y, WIDTH, HEIGHT);
    }

    /**
     * Paint Racket
     * @param g
     */
    public void paint(Graphics g) {
        g.fillRect(x, y, WIDTH, HEIGHT);
    }
}