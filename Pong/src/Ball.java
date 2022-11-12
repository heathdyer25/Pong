import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

/**
 * 
 */

/**
 * Creates a new ball to interact with paddles.
 * @author Heath Dyer
 *
 */
public class Ball extends Rectangle {

	/** Serial of the ball */
	private static final long serialVersionUID = 1L;
	/** Instance of random */
	Random random;
	/** x velocity of the ball */
	int xVelocity;
	/** y velocity of ball */
	int yVelocity;
	/** initiial speed of the ball */
	int initialSpeed = 2;
	
	/**
	 * Creates a new ball object with location and size.
	 * @param x x position of ball.
	 * @param y y position of ball.
	 * @param width width of the ball.
	 * @param height height of the ball.
	 */
	public Ball(int x, int y, int width, int height) {
		super(x, y, width, height);
		random = new Random();
		int randomXDirection = random.nextInt(2);
		if (randomXDirection == 0) {
			randomXDirection--;
		}
		int randomYDirection = random.nextInt(2);
		setXDirection(randomXDirection*initialSpeed);
		if (randomYDirection == 0) {
			randomYDirection--;
		}
		setYDirection(randomXDirection*initialSpeed);
		
	}
	
	/**
	 * Sets the velocity of the ball in the x direction
	 * @param randomXDirection direction to set to.
	 */
	public void setXDirection(int randomXDirection) {
		xVelocity = randomXDirection;
	}
	
	/**
	 * Sets the velocity of the ball in the y direction.
	 * @param randomYDirection y direction to set
	 */
	public void setYDirection(int randomYDirection) {
		yVelocity = randomYDirection;
	}
	
	/**
	 * Moves the ball in the direction based on its velocity.
	 */
	public void move() {
		x += xVelocity;
		y += yVelocity;
	}
	
	/**
	 * Draws the ball on the screen using Graphics instance.
	 * @param g instance of graphics to draw to.
	 */
	public void draw(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillOval(x, y, height, width);
	}
}
