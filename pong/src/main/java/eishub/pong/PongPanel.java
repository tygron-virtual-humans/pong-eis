package eishub.pong;

/**
 * Pong in JAVA
 * Base source: http://codereview.stackexchange.com/questions/27197/pong-game-in-java
 * Modified for example eis project.
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class PongPanel extends JPanel{
	private static final long serialVersionUID = 6647966072683911536L;
    private final Font scoreFont = new Font("Tahoma", Font.PLAIN, 35);
    private final Font vsFont = new Font("Tahoma", Font.PLAIN, 20);
	private Pong game;
	
    public PongPanel(Pong game) {
        setBackground(Color.WHITE);
        this.game = game;            
        initializeKeyBindings();
        setFocusable(true);
    }

    
    /**
     * Initialize 2 Player Bindings
     */
	private void initializeKeyBindings() { 
		
		InputMap inpMap = this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		
		/**
		 * General Functionality
		 */
	    Action reset = new AbstractAction() {
	        private static final long serialVersionUID = 6535;
	        public void actionPerformed(ActionEvent e) {
	        	game.resetGame();
	        }
	    };
	    inpMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_R,0,false), "Reset");
	    this.getActionMap().put("Reset", reset);
	    
		/**
		 * PLAYER 1
		 */
	    Action up = new AbstractAction() {
	        private static final long serialVersionUID = 6535;
	        public void actionPerformed(ActionEvent e) {
	           game.getPlayer(1).moveUp();
	        }
	    };
	    Action down = new AbstractAction() {
	        private static final long serialVersionUID = 12342L;
	        public void actionPerformed(ActionEvent e) {
	           game.getPlayer(1).moveDown();
	        }
	    };
	    Action stop = new AbstractAction() {
	        private static final long serialVersionUID = 4256;
	        public void actionPerformed(ActionEvent e) {
	           game.getPlayer(1).moveStop();
	        }
	    };
	    inpMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP,0,false), "UP");
	    this.getActionMap().put("UP", up);
	    inpMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN,0,false), "DOWN");
	    this.getActionMap().put("DOWN", down);
	    
	    inpMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP,0,true), "UPS");
	    this.getActionMap().put("UPS", stop);
	    inpMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN,0,true), "DOWNS");
	    this.getActionMap().put("DOWNS", stop);
	    
		/**
		 * PLAYER 2
		*/
	    Action up2 = new AbstractAction() {
	        private static final long serialVersionUID = 46737;
	        public void actionPerformed(ActionEvent e) {
	           game.getPlayer(2).moveUp();
	        }
	    };
	    Action down2 = new AbstractAction() {
	        private static final long serialVersionUID = 784876;
	        public void actionPerformed(ActionEvent e) {
	           game.getPlayer(2).moveDown();
	        }
	    }; 
	    Action stop2 = new AbstractAction() {
	        private static final long serialVersionUID = 566985;
	        public void actionPerformed(ActionEvent e) {
	           game.getPlayer(2).moveStop();
	        }
	    };
	    
	    inpMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_W,0,false), "UP2");
	    this.getActionMap().put("UP2", up2);
	    inpMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_S,0,false), "DOWN2");
	    this.getActionMap().put("DOWN2", down2);
	    
	    inpMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_W,0,true), "UPS2");
	    this.getActionMap().put("UPS2", stop2);
	    inpMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_S,0,true), "DOWNS2");
	    this.getActionMap().put("DOWNS2", stop2);
	    
	}

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        // Draw scorebox
        g.setColor(new Color(236,236,236));
        g.fillRect(game.getWidth()/2 - Pong.BORDER_CORRECTION, 0, 100, 50);
        
        // Enable anti aliasing
        ((Graphics2D) g).setRenderingHint(
                RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);
        
        // Draw score
        g.setFont(scoreFont);
        g.setColor(new Color(0,145,206));
        g.drawString(game.getScore(1)+ "", game.getWidth()/2 - Pong.BORDER_CORRECTION + 10, 37);
        g.drawString(game.getScore(2)+ "", game.getWidth()/2 - Pong.BORDER_CORRECTION + 71, 37);
        
        // Draw VS   
        g.setFont(vsFont);
        g.setColor(new Color(206,24,0));
        g.drawString("vs", game.getWidth()/2 - Pong.BORDER_CORRECTION + 41, 37);
       
        // Draw players
        game.getPlayer(1).paint(g);
        game.getPlayer(2).paint(g);
        
        // Draw state
        if(!game.getGameState()){
        	g.setFont(scoreFont);
        	g.setColor(new Color(0,145,206));
        	int won;
        	if(game.getScore(1) > game.getScore(2)){
        		won = 1;
        	}else{
        		won = 2;
        	}
        	g.drawString("Player "+won+" WON!",game.getWidth()/2 - Pong.BORDER_CORRECTION - 80, game.getHeight()/2);
        	g.setFont(vsFont);
        	g.drawString("press 'r' to reset", game.getWidth()/2 - Pong.BORDER_CORRECTION - 36 , game.getHeight()/2 + 25);
        }else{
            // Draw ball
            g.setColor(Color.BLACK);
            game.getBall().paint(g);
        }
    }
}