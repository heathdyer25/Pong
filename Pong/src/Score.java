import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 * 
 */

/**
 * Score of the game. Keeps track of players score and paints it to the screen.
 * @author Heath Dyer
 *
 */
public class Score {

	/** Game width */
	static int GAME_WIDTH;
	/** Game width */
	static int GAME_HEIGHT;
	/** Player 1 score */
	int player1;
	/**Palyer 2 score  */
	int player2;
	
	/**
	 * Creates a new instances of score.
	 * @param GAME_WIDTH
	 * @param GAME_HEIGHT
	 */
	public Score(int GAME_WIDTH, int GAME_HEIGHT) {
		Score.GAME_WIDTH = GAME_WIDTH;
		Score.GAME_HEIGHT = GAME_HEIGHT;
	}
	
	/**
	 * Draws the score to the screen with graphics.
	 * @param g graphics to draw on.
	 */
	public void draw(Graphics g) {
		g.setColor(Color.WHITE);
		g.setFont(new Font("Consolas", Font.PLAIN, 60));
		g.drawLine(GAME_WIDTH/2, 0, GAME_WIDTH/2, GAME_HEIGHT);
		
		g.drawString(String.valueOf(player1), (GAME_WIDTH/2) - 85, 50);
		g.drawString(String.valueOf(player2), (GAME_WIDTH/2) + 85, 50);
	}
}
