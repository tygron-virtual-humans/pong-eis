package eishub.pong;

/**
 * Pong in JAVA
 * Base source: http://codereview.stackexchange.com/questions/27197/pong-game-in-java
 * Modified for example eis project.
 */

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
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
    private final float dash1[] = {10.0f};
    private final BasicStroke dashed =  new BasicStroke(5.0f,BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER,10.0f, dash1, 0.0f);
	private Pong game;
	
	/**
	 * Pong Panel constructor.
	 * @param game
	 */
    public PongPanel(Pong game) {
        setBackground(Color.WHITE);
        this.game = game;            
        initializeKeyBindings();
        setFocusable(true);
    }

    
    /**
     * Initializer for all key bindings
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

	/**
	 * General Paint function - called by JAVA
	 */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        // Paint all layered elements
        paintBackground(g);
        paintUI(g);
        paintGameElements(g);
               
    }
    
    /**
     * Paint the background
     * @param g Graphics Object
     */
    public void paintBackground(Graphics g) {
    	g.setColor(Color.BLACK);
    	g.fillRect(0, 0, game.getWidth(), 3); // Top
    	g.fillRect(0, game.getHeight() - Pong.BORDER_CORRECTION, game.getWidth(), 3); // Bottom
    	g.fillRect(0, 0, 3, game.getHeight()); // Left
    	g.fillRect(game.getWidth() - 9, 0, 3, game.getHeight()); // Right
    	
    	if(game.getGameState()){
	    	Stroke original = ((Graphics2D) g).getStroke();
	    	((Graphics2D) g).setStroke(dashed);
	    	g.drawLine(game.getWidth()/2 - 4, 0, game.getWidth()/2 - 4, game.getHeight());  
	    	((Graphics2D) g).setStroke(original); 
    	}
    }
    
    /**
     * Paint the UI
     * @param g Graphics Object
     */
    public void paintUI(Graphics g) {
        // Draw scorebox
        g.setColor(new Color(236,236,236));
        g.fillRect(game.getWidth()/2 - Pong.BORDER_CORRECTION - 23, 3, 100, 47);
        g.setColor(Color.BLACK);
        g.drawRect(game.getWidth()/2 - Pong.BORDER_CORRECTION - 23, 2, 100, 48);
        
        // Enable anti aliasing
        ((Graphics2D) g).setRenderingHint(
                RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);
        
        // Draw score
        g.setFont(scoreFont);
        g.setColor(new Color(0,145,206));
        g.drawString(game.getScore(1)+ "", game.getWidth()/2 - Pong.BORDER_CORRECTION - 13, 39);
        g.drawString(game.getScore(2)+ "", game.getWidth()/2 - Pong.BORDER_CORRECTION + 48, 39);
        
        // Draw VS   
        g.setFont(vsFont);
        g.setColor(new Color(206,24,0));
        g.drawString("vs", game.getWidth()/2 - Pong.BORDER_CORRECTION + 18, 39);  	
    }  
    
    /**
     * Draw game elements.
     * @param g Graphics Object
     */
    public void paintGameElements(Graphics g) {
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