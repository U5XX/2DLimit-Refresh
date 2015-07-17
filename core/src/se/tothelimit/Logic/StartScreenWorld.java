package se.tothelimit.Logic;

import java.util.ArrayList;

import se.tothelimit.ToTheLimit;
import se.tothelimit.Input.Buffer;
import se.tothelimit.Entities.Star;
import se.tothelimit.Resources.TTLImg;
import se.tothelimit.Resources.TTLSnd;
import se.tothelimit.Screens.GameScreen;
import se.tothelimit.Tools.KeyboardInput;

import com.badlogic.gdx.Gdx;

/**
 * This class contains the logic and game loop for the StartScreen.
 * 
 * @author Fredrik Andersson
 * 
 */
public class StartScreenWorld {

	private ToTheLimit game;
	private boolean p1Start = false;
	private boolean p2Start = false;
	private ArrayList<Integer> buffer;
	private ArrayList<Star> stars;

	/**
	 * Basic constructor.
	 * 
	 * @param game
	 *            A reference to the main Game class.
	 */
	public StartScreenWorld(ToTheLimit game) {

		this.game = game;
		initLevelComponents();

		// Only relevant for the desktop version.
		Gdx.input.setInputProcessor(new KeyboardInput());
		
	}

	private void initLevelComponents() {
		this.stars = new ArrayList<Star>();

		Star star1 = new Star(36, 51, 5, 5, TTLImg.stars);
		Star star2 = new Star(90, 53, 5, 5, TTLImg.stars);
		Star star3 = new Star(130, 24, 5, 5, TTLImg.stars);
		Star star4 = new Star(190, 56, 5, 5, TTLImg.stars);
		stars.add(star1);
		stars.add(star2);
		stars.add(star3);
		stars.add(star4);
	}

	/**
	 * This method handles the updating of the logic associated with the start
	 * screen.
	 */
	public void update() {

		// Reads the values from the buffer and checks if the
		// players have pressed start.
		this.buffer = Buffer.read();

		for (int value : buffer) {
			switch (value) {
			case ToTheLimit.PLAYER1_START:
				if (!p1Start) {
					p1Start = true;
					TTLSnd.pressStart.play();
				}
				break;
			case ToTheLimit.PLAYER2_START:
				if (!p2Start) {
					p2Start = true;
					TTLSnd.pressStart.play();
				}
				break;
			}
		}

		// Switches to the game screen if both players have
		// pressed start.
		if (p1Start && p2Start) {
			game.setScreen(new GameScreen(game));
		}
	}

	/**
	 * Returns a boolean indicating whether or not P1 has pressed start.
	 * 
	 * @return 'true' if P1 has pressed start, otherwise 'false'.
	 */
	public boolean p1PressedStart() {
		return p1Start;
	}

	/**
	 * Returns a boolean indicating whether or not P2 has pressed start.
	 * 
	 * @return 'true' if P2 has pressed start, otherwise 'false'.
	 */
	public boolean p2PressedStart() {
		return p2Start;
	}

	/**
	 * Returns the current stars.
	 * 
	 * @return A list of the current stars.
	 */
	public ArrayList<Star> getStars() {
		return stars;
	}

}
