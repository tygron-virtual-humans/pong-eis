package eishub.pong;

/**
 * Pong in JAVA
 * Base source: http://codereview.stackexchange.com/questions/27197/pong-game-in-java
 * Modified for example eis project.
 */

import java.awt.Color;
import java.awt.Graphics;
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
        g.drawString(game.getScore(1) + " : " + game.getScore(2), game.getWidth() / 2, 10);
        game.getBall().paint(g);
        game.getPlayer(1).paint(g);
        game.getPlayer(2).paint(g);
    }
}