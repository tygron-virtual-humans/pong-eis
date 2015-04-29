package eishub.pong;

/**
 * Pong in JAVA
 * Base source: http://codereview.stackexchange.com/questions/27197/pong-game-in-java
 * Modified for example eis project.
 */

import java.awt.Color;

import javax.swing.JFrame;

public class Pong extends JFrame {
	private static final long serialVersionUID = 3504739896597314995L;
	private final static int GAME_WIDTH = 800, GAME_HEIGHT = 600;
    private PongPanel panel;

    public Pong() {
        setSize(GAME_WIDTH, GAME_HEIGHT);
        setTitle("Pong");
        setBackground(Color.WHITE);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        panel = new PongPanel(this);
        add(panel);
    }

    public PongPanel getPanel() {
        return panel;
    }

    public static void main(String[] args) {
        new Pong();
    }
}