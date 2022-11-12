import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.JPanel;


/**
 * 
 * @author Heath Dyer
 *
 */
public class GamePanel extends JPanel implements Runnable {
	/** Serial UID of the game. */
	private static final long serialVersionUID = 1L;
	/** Width of game panel to construct */
	public static final int GAME_WIDTH = 1000;
	/** Height of game panel to construct*/
	public static final int GAME_HEIGHT = (int) (GAME_WIDTH * (0.5555));
	/** Dimension of the screen for graphics. */
	static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
	/** Diameter of the ball */
	static final int BALL_DIAMETER = 20;
	/** Width of the paddle */
	static final int PADDLE_WIDTH = 25;
	/** Height of the paddle to construct */
	static final int PADDLE_HEIGHT = 100;
	
	/** */
	Thread gameThread;
	/** */
	Image image;
	/** */
	Graphics graphics;
	/** Instance of random class. */
	Random random;
	/** Player1 paddle */
	Paddle paddle1;
	/** Player2 paddle */
	Paddle paddle2;
	/** Game ball */
	Ball ball;
	/** Instance of score object. */
	Score score;
	
	/**
	 * Constructor for GamePanel.
	 */
	public GamePanel() {
		newPaddles();
		newBall();
		score = new Score(GAME_WIDTH, GAME_HEIGHT);
		this.setFocusable(true);
		this.addKeyListener(new AL());
		this.setPreferredSize(SCREEN_SIZE);
		
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	/**
	 * Creates a new ball in the middle of the screen at a random height.
	 */
	public void newBall() {
		random = new Random();
		ball = new Ball((GAME_WIDTH/2) - (BALL_DIAMETER/2),random.nextInt(GAME_HEIGHT-BALL_DIAMETER), BALL_DIAMETER, BALL_DIAMETER);
	}
	
	/**
	 * Creates new paddles on the screen at specified elements.
	 */
	public void newPaddles() {
		paddle1 = new Paddle(0, (GAME_HEIGHT / 2) - (PADDLE_HEIGHT / 2), PADDLE_WIDTH, PADDLE_HEIGHT, 1);
		paddle2 = new Paddle(GAME_WIDTH - PADDLE_WIDTH, (GAME_HEIGHT / 2) - (PADDLE_HEIGHT / 2), PADDLE_WIDTH, PADDLE_HEIGHT, 2);
	}
	
	/**
	 * Pains the graphics to the GamePanel with instance of graphics.
	 */
	public void paint(Graphics g) {
		image = createImage(getWidth(), getHeight());
		graphics = image.getGraphics();
		draw(graphics);
		g.drawImage(image, 0, 0, this);
	}
	
	/**
	 * Draws elements of the game on the screen.
	 * @param g graphics to draw on.
	 */
	public void draw(Graphics g) {
		paddle1.draw(g);
		paddle2.draw(g);
		ball.draw(g);
		score.draw(g);
	}
	
	/**
	 * All moving elements in the GamePanel.
	 */
	public void move() {
		paddle1.move();
		paddle2.move();
		ball.move();
	}
	
	/**
	 * Checks for collisions in our game. This includes when the ball bounces off the top and bottom edges, when the ball collides with the paddle, when the ball
	 * moves off the screen, prevents paddles from moving off screen.
	 */
	public void checkCollision() {
		//bounce ball off top & bottom window edges
		if (ball.y <= 0 ) {
			ball.setYDirection(-ball.yVelocity);
		}
		if (ball.y >= GAME_HEIGHT - BALL_DIAMETER) {
			ball.setYDirection(-ball.yVelocity);
		}
		
		//bounce ball off paddles
		if (ball.intersects(paddle1)) {
			ball.xVelocity = Math.abs(ball.xVelocity);
			ball.xVelocity++; //optional
			if (ball.yVelocity > 0) {
				ball.yVelocity++;
			}
			else {
				ball.yVelocity--;
			}
			ball.setXDirection(ball.xVelocity);
			ball.setYDirection(ball.yVelocity);
		}
		
		//bounce ball off paddles
		if (ball.intersects(paddle2)) {
			ball.xVelocity = Math.abs(ball.xVelocity);
			ball.xVelocity++; //optional
			if (ball.yVelocity > 0) {
				ball.yVelocity++;
			}
			else {
				ball.yVelocity--;
			}
			ball.setXDirection(-ball.xVelocity);
			ball.setYDirection(ball.yVelocity);
		}
		
		//stops paddles at window edges
		if (paddle1.y <= 0) {
			paddle1.y = 0;
		}
		if (paddle1.y >= (GAME_HEIGHT - PADDLE_HEIGHT)) {
			paddle1.y = GAME_HEIGHT - PADDLE_HEIGHT;
		}
		if (paddle2.y <= 0) {
			paddle2.y = 0;
		}
		if (paddle2.y >= (GAME_HEIGHT - PADDLE_HEIGHT)) {
			paddle2.y = GAME_HEIGHT - PADDLE_HEIGHT;
		}
		
		//give  player 1 point and creates new paddles & ball.
		if (ball.x <= 0) {
			score.player2++;
			newPaddles();
			newBall();
		}
		if (ball.x >= GAME_WIDTH) {
			score.player1++; 
			newPaddles();
			newBall();
		}
	}
	
	/**
	 * Run loop, creates steady FPS. Calls move and checkCollision each loop and repaints our panel.
	 */
	public void run() {
		//game loop
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		while(true) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if (delta >= 1) {
				move();
				checkCollision();
				repaint();
				delta--;
			}
		}
	}
	
	/**
	 * Key adapter to control the paddles in our Pong game.
	 * @author Heath Dyer
	 *
	 */
	public class AL extends KeyAdapter {
		
		/**
		 * Events for when a key is pressed for the paddles.
		 */
		public void keyPressed(KeyEvent e) {
			paddle1.keyPressed(e);
			paddle2.keyPressed(e);
		}
		
		/**
		 * Events for when a key is released for the paddles.
		 */
		public void keyReleased(KeyEvent e) {
			paddle1.keyReleased(e);
			paddle2.keyReleased(e);
		}
	}
}
