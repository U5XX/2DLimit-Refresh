package se.tothelimit.Logic;

import com.badlogic.gdx.Gdx;

import java.util.ArrayList;

import se.tothelimit.Input.WinnerScreenTouchInput;
import se.tothelimit.ToTheLimit;
import se.tothelimit.Input.Buffer;
import se.tothelimit.Entities.Bridge;
import se.tothelimit.Entities.Player;
import se.tothelimit.Resources.TTLImg;
import se.tothelimit.Resources.TTLSnd;
import se.tothelimit.Screens.StartScreen;
import se.tothelimit.Tools.InputHandler;
import se.tothelimit.Tools.InputListener;

/**
 * A screen that gets triggered when a player wins. This
 * screen is mainly listening for the "reset" button, and also
 * renders a nice looking winning screen.
 * 
 * @author Simon Cedergren <simon@tuxflux.se> and Fredrik Andersson <cfredrikandersson@hotmail.com>
 */
public class WinnerScreenWorld {

	private ToTheLimit game;
	private Player player;
	private ArrayList<Bridge> bridges;

	private ArrayList<Integer> buffer;

	public WinnerScreenWorld(ToTheLimit game, Player player) {
		this.game = game;
		this.player = player;
		initPlayer();
		initBridge();
		Gdx.input.setInputProcessor(new InputHandler(new WinnerScreenTouchInput()));
		Buffer.clear();
	}

	/**
	 * Moves player back to mid-screen
	 */
	private void initPlayer() {
		player.setX((ToTheLimit.WIDTH / 2) - (player.getWidth() / 2));
		player.setHasWon(true);
	}

	/**
	 * Initiates the the two bridges.
	 */
	private void initBridge() {
		this.bridges = new ArrayList<Bridge>(2);

		for (int i = 0; i < 2; i++) {
			float x = (i * ToTheLimit.WIDTH);
			float y = 0;
			float width = ToTheLimit.WIDTH;
			float height = TTLImg.bridge.getHeight();

			Bridge newBridge = new Bridge(x, y, width, height, TTLImg.bridge);
			bridges.add(newBridge);
		}
	}

	/**
	 * Listening for players to press the start button and
	 * renders the player animation.
	 */
	public void update() {
		// Reads the BT-buffer, and goes to start screen if a button has been
		// pressed.

		buffer = Buffer.read();
		for (int nbr : buffer) {
			if (nbr == ToTheLimit.PLAYER1_START
					|| nbr == ToTheLimit.PLAYER2_START){
				TTLSnd.bgm.stop();
				game.setScreen(new StartScreen(game));
			}
		}

		player.update();

	}

	/**
	 * Returns the winning player.
	 * @return the winning player.
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * Returns the bridges.
	 * @return an ArrayList<Bridge> with all (all two actually...) bridges.
	 */
	public ArrayList<Bridge> getBridge() {
		return bridges;
	}
}
