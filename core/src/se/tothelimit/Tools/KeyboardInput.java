package se.tothelimit.Tools;

import se.tothelimit.ToTheLimit;
import se.tothelimit.Input.Buffer;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;

/**
 * An implementation of InputProcessor, used exclusively by the desktop version (in debug purpose)
 * 
 * @author Fredrik Andersson
 * 
 */
public class KeyboardInput implements InputProcessor {

	@Override
	public boolean keyDown(int keycode) {
		switch (keycode) {

		// Makes Player 1 move
		case Keys.LEFT:
			Buffer.write(ToTheLimit.PLAYER1_JUMP);
			break;
		case Keys.RIGHT:
			Buffer.write(ToTheLimit.PLAYER1_RUN);
			break;
		case Keys.UP:
			Buffer.write(ToTheLimit.PLAYER1_START);
			break;

		// Makes Player 2 move
		case Keys.A:
			Buffer.write(ToTheLimit.PLAYER2_JUMP);
			break;
		case Keys.D:
			Buffer.write(ToTheLimit.PLAYER2_RUN);
			break;
		case Keys.W:
			Buffer.write(ToTheLimit.PLAYER2_START);
			break;
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
