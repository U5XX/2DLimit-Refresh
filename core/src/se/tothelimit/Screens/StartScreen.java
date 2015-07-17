package se.tothelimit.Screens;

import se.tothelimit.ToTheLimit;
import se.tothelimit.Logic.StartScreenWorld;
import se.tothelimit.Render.StartScreenRenderer;

import com.badlogic.gdx.Screen;

/**
 * An implementation of Screen that handles the logic and rendering of the start
 * screeny.
 * 
 * @author Fredrik Andersson
 * @author Simon Cedergren
 * 
 */
public class StartScreen implements Screen {

	private ToTheLimit game;
	private StartScreenWorld world;
	private StartScreenRenderer renderer;

	/**
	 * Basic constructor
	 * 
	 * @param game
	 *            A reference to the current implementation of the game.
	 */
	public StartScreen(ToTheLimit game) {
		this.game = game;
		this.world = new StartScreenWorld(game);
		this.renderer = new StartScreenRenderer(world);
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
		// TODO Auto-generated method stub

	}

}
