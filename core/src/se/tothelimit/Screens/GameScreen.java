package se.tothelimit.Screens;

import se.tothelimit.ToTheLimit;
import se.tothelimit.Logic.GameScreenWorld;
import se.tothelimit.Render.GameScreenRenderer;

import com.badlogic.gdx.Screen;

/**
 * An implementation of Screen that handles the logic and rendering of the
 * actual gameplay.
 * 
 * @author Fredrik Andersson
 * 
 */
public class GameScreen implements Screen {

	private ToTheLimit game;
	private GameScreenWorld world;
	private GameScreenRenderer renderer;

	/**
	 * Basic constructor
	 * 
	 * @param game
	 *            A reference to the current implementation of Game.
	 */
	public GameScreen(ToTheLimit game) {
		this.game = game;
		this.world = new GameScreenWorld(game);
		this.renderer = new GameScreenRenderer(world);
	}

	@Override
	public void render(float delta) {
		world.update();
		renderer.render();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		this.dispose();
	}
}
