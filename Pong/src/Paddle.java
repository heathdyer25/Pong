import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

/**
 * 
 */

/**
 * Creates a new paddle to interact with ball.
 * @author Heath Dyer
 *
 */
public class Paddle extends Rectangle {

	/** Serial ID of rectangle */
	private static final long serialVersionUID = 1L;
	
	/** id of the paddle */
	public int id;
	/** velocity of the paddle */
	public int yVelocity;
	/** speed of the paddle */
	int speed = 10;
	
	/**
	 * Creates a new paddle object to interact with ball.
	 * @param x x position of paddle
	 * @param y y position of paddle
	 * @param PADDLE_WIDTH width of the paddle
	 * @param PADDLE_HEIGHT height of the paddle
	 * @param id id of the paddle
	 */
	public Paddle(int x, int y, int PADDLE_WIDTH, int PADDLE_HEIGHT, int id) {
		super(x, y, PADDLE_WIDTH, PADDLE_HEIGHT);
		this.id = id;
	}
	
	/**
	 * Moves the paddles when the keys are pressed. Player 1 is controlled with W and S, player to is controlled with UP and DOWN
	 * @param e
	 */
	public void keyPressed(KeyEvent e) {
		switch(id) {
		case 1:
			if (e.getKeyCode() == KeyEvent.VK_W) {
				setYDirection(-speed);
				move();
			}
			if (e.getKeyCode() == KeyEvent.VK_S) {
				setYDirection(speed);
				move();
			}
			break;
		case 2:
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				setYDirection(-speed);
				move();
			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				setYDirection(speed);
				move();
			}
			break;
		}
	}
	
	/**
	 * Controls what happens when the key is released
	 * @param e
	 */
	public void keyReleased(KeyEvent e) {
		switch(id) {
		case 1:
			if (e.getKeyCode() == KeyEvent.VK_W) {
				setYDirection(0);
				move();
			}
			if (e.getKeyCode() == KeyEvent.VK_S) {
				setYDirection(0);
				move();
			}
			break;
		case 2:
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				setYDirection(0);
				move();
			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				setYDirection(0);
				move();
			}
			break;
		}
	}
	
	/**
	 * Sets the YDirection of the paddle
	 * @param yDirection y direction to set
	 */
	public void setYDirection(int yDirection) {
		yVelocity = yDirection;
	}
	
	/**
	 * Moves the paddle in the specified direction
	 */
	public void move() {
		y = y + yVelocity;
	}
	
	/**
	 * Draws the paddle on the screen
	 * @param g graphics to draw to.
	 */
	public void draw(Graphics g) {
		if(id == 1) {
			g.setColor(Color.BLUE);
		}
		if(id == 2) {
			g.setColor(Color.RED);
		}
		g.fillRect(x, y, width, height);
	}
	
}
