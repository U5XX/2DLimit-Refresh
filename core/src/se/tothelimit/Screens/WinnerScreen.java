package se.tothelimit.Screens;

import se.tothelimit.ToTheLimit;
import se.tothelimit.Entities.Player;
import se.tothelimit.Logic.WinnerScreenWorld;
import se.tothelimit.Render.WinnerScreenRenderer;

import com.badlogic.gdx.Screen;

/**
 * An implementation of Screen that handles the logic and rendering of the
 * winner screen.
 * 
 * @author Fredrik Andersson
 * 
 */
public class WinnerScreen implements Screen {
	
	private ToTheLimit game;
	private WinnerScreenWorld world;
	private WinnerScreenRenderer renderer;
	
	/**
	 * Basic constructor
	 * @param game A reference to the current implementation of Game
	 * @param player The winning player.
	 */
	public WinnerScreen(ToTheLimit game, Player player) {
		this.game = game;
		this.world = new WinnerScreenWorld(game,player);
		this.renderer = new WinnerScreenRenderer(world);
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
