package se.tothelimit;

import se.tothelimit.Input.Buffer;
import se.tothelimit.Resources.TTLImg;
import se.tothelimit.Resources.TTLSnd;
import se.tothelimit.Screens.StartScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.FPSLogger;

/**
 * 2DLimit - the best side scrolling adventure you'll ever experience.
 * 
 * @author Fredrik Andersson <cfredrikandersson@hotmail.com> and Simon Cedergren <simon@tuxflux.se> 
 */

public class ToTheLimit extends Game {

	private FPSLogger log;

	public final static int WIDTH = 256;
	public final static int HEIGHT = 144;

	public final static int PLAYER1_JUMP = 7;
	public final static int PLAYER1_RUN = 8;
	public final static int PLAYER1_START = 9;

	public final static int PLAYER2_JUMP = 10;
	public final static int PLAYER2_RUN = 11;
	public final static int PLAYER2_START = 12;
	
	public final static int BRIDGE = 85;

	@Override
	public void create() {
		TTLImg.init();
		TTLSnd.init();
		Buffer.init();
		setScreen(new StartScreen(this));
	}

	@Override
	public void dispose() {
		super.dispose();
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void resume() {
		super.resume();
	}
}
