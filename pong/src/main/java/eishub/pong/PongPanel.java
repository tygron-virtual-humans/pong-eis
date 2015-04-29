package eishub.pong;

/**
 * Pong in JAVA
 * Base source: http://codereview.stackexchange.com/questions/27197/pong-game-in-java
 * Modified for example eis project.
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class PongPanel extends JPanel implements KeyListener {
	private static final long serialVersionUID = 6647966072683911536L;
       
	private Pong game;
	
    public PongPanel(Pong game) {
        setBackground(Color.WHITE);
        this.game = game;            

        addKeyListener(this);
        setFocusable(true);
    }

    public void keyPressed(KeyEvent e) {
    	System.out.println(e.getKeyCode());
    	
    	game.getPlayer(1).pressed(e.getKeyCode());
    	game.getPlayer(2).pressed(e.getKeyCode());
    }

    public void keyReleased(KeyEvent e) {
    	 System.out.println("keyReleased");
    	 game.getPlayer(1).released(e.getKeyCode());
    	 game.getPlayer(2).released(e.getKeyCode());
    }

    public void keyTyped(KeyEvent e) {
        System.out.println("keyTyped");
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawString(game.getScore(1) + " : " + game.getScore(2), game.getWidth() / 2, 10);
        game.getBall().paint(g);
        game.getPlayer(1).paint(g);
        game.getPlayer(2).paint(g);
    }
}