import java.awt.Color;

import javax.swing.JFrame;

/**
 * Creates the Frame for the game and adds the GamePanel with all content of game.
 * @author Heath Dyer
 *
 */
public class GameFrame extends JFrame {
	/** Serial UID*/
	private static final long serialVersionUID = 1L;
	/** Panel with game content*/
	GamePanel panel;
	
	/**
	 * Constructor for GameFrame. Creates a new instance of our Pong GameFrame.
	 */
	public GameFrame() {
		panel = new GamePanel();
		this.add(panel);
		this.setTitle("Pong");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBackground(Color.black);
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}
	
	
	
}
