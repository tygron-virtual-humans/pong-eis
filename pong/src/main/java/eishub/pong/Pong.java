package eishub.pong;

/**
 * Pong in JAVA
 * Base source: http://codereview.stackexchange.com/questions/27197/pong-game-in-java
 * Modified for example eis project.
 */

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.Timer;

public class Pong extends JFrame implements ActionListener {
	private static final long serialVersionUID = 3504739896597314995L;
	private final static int GAME_WIDTH = 800, GAME_HEIGHT = 600;
	public final static int BORDER_CORRECTION = 16;
	private boolean playingState = false;
    private PongPanel panel;
    private Timer timer;
    
	
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
        
        panel = new PongPanel(this);
        add(panel);
        
        resetGame();
    }

    /**
     * Reset the game completely
     */
    public void resetGame(){
    	
    	// Create game elements
        ball = new Ball(this);
        player1 = new Racket(this, this.getWidth() - BORDER_CORRECTION - 5);
        player2 = new Racket(this, 5);
    	
        // Set playing state + start timer/game
        if(timer != null){
        	timer.stop();
        }
        score1 = 0;
        score2 = 0;
        playingState = true;
        timer = new Timer(5, this);
        timer.start();
    }
    
    /**
     * Returns a player instance.
     * @param playerNo The player number.
     * @return
     */
    public Racket getPlayer(int playerNo) {
        if (playerNo == 1){
            return player1;
        }else{
            return player2;
        }
    }
    
    /**
     * Returns the ball instance.
     * @return
     */
    public Ball getBall(){
    	return ball;
    }

    /**
     * Increase the score for a given player number.
     * @param playerNo The player number.
     */
    public void increaseScore(int playerNo) {
        if (playerNo == 1){
            score1++;
        }else{
            score2++;
        }
    }

    /**
     * Returns the score for a given player number.
     * @param playerNo The player number.
     * @return
     */
    public int getScore(int playerNo) {
        if (playerNo == 1){
            return score1;
        }else{
            return score2;
        }
    }
    
    /**
     * Set the current game state.
     * @param newState
     */
    public void setGameState(boolean newState){
    	playingState = newState;
    	
    	if(newState == false){
    		timer.stop();
    	}
    }
    
    /**
     * Return the current game state
     * @return
     */
    public boolean getGameState(){
    	return playingState;
    }

    /**
     * Gets called to update game mechanisms by timer
     */
    private void update() {
        getBall().update();
        getPlayer(1).update();
        getPlayer(2).update();
    }

    /**
     * Callback for java on an action (timer)
     */
	public void actionPerformed(ActionEvent arg0) {
		update();
		panel.repaint();
	}
	
	/**
	 * Main Program Launcher
	 * @param args
	 */
    public static void main(String[] args) {
        new Pong();
    }
}