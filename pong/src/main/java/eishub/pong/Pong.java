package eishub.pong;

/**
 * Pong in JAVA
 * Base source: http://codereview.stackexchange.com/questions/27197/pong-game-in-java
 * Modified for example eis project.
 */

import java.awt.Color;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

public class Pong extends JFrame {
	private static final long serialVersionUID = 3504739896597314995L;
	private final static int GAME_WIDTH = 800, GAME_HEIGHT = 600;
    private PongPanel panel;
    
	
    private Ball ball;
    private Racket player1, player2;
    private int score1, score2;    

    public Pong() {
        setSize(GAME_WIDTH, GAME_HEIGHT);
        setTitle("Pong");
        setBackground(Color.WHITE);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        ball = new Ball(this);
        player1 = new Racket(this, KeyEvent.VK_UP, KeyEvent.VK_DOWN, this.getWidth() - 36);
        player2 = new Racket(this, KeyEvent.VK_W, KeyEvent.VK_S, 20);
         
        panel = new PongPanel(this);
        add(panel);
    }

    
    public Racket getPlayer(int playerNo) {
        if (playerNo == 1)
            return player1;
        else
            return player2;
    }
    
    public Ball getBall(){
    	return ball;
    }

    public void increaseScore(int playerNo) {
        if (playerNo == 1)
            score1++;
        else
            score2++;
    }

    public int getScore(int playerNo) {
        if (playerNo == 1)
            return score1;
        else
            return score2;
    }
    
    public PongPanel getPanel() {
        return panel;
    }

    public static void main(String[] args) {
        new Pong();
    }
}