package se.tothelimit.Render;

import java.util.ArrayList;

import se.tothelimit.ToTheLimit;
import se.tothelimit.Entities.Star;
import se.tothelimit.Logic.StartScreenWorld;
import se.tothelimit.Resources.TTLImg;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * This class handles the rendering of the start screen.
 * @author Fredrik Andersson
 *
 */
public class StartScreenRenderer {

	private StartScreenWorld world;
	private ArrayList<Star> stars;
	
	private SpriteBatch batch;
	public static OrthographicCamera cam;
	private Texture p1Message;
	private Texture p2Message;
	

	/**
	 * Basic constructor
	 * @param world The associated StartScreenWorld-object.
	 */
	public StartScreenRenderer(StartScreenWorld world) {
		this.world = world;

		cam = new OrthographicCamera();
		cam.setToOrtho(true, ToTheLimit.WIDTH, ToTheLimit.HEIGHT);
		cam.update();

		batch = new SpriteBatch();
		batch.setProjectionMatrix(cam.combined);
	}

	/**
	 * Draws the graphical elements of the start screen.
	 */
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		cam.update();
		batch.setProjectionMatrix(cam.combined);
		
		stars = world.getStars();
		
		// Checks whether or not the players have pressed Start
		// and displays the correct message.
		if(!world.p1PressedStart())
			p1Message = TTLImg.player1Start;
		else
			p1Message = TTLImg.player1Ready;
		
		if(!world.p2PressedStart())
			p2Message = TTLImg.player2Start;
		else
			p2Message = TTLImg.player2Ready;

		batch.begin();

		// Draws the background
		batch.draw(TTLImg.background, 0, 0, ToTheLimit.WIDTH,
				ToTheLimit.HEIGHT, 0, 0, TTLImg.background.getWidth(),
				TTLImg.background.getHeight(), false, true);

		// Draws the title
		batch.draw(TTLImg.gameTitle,
				(ToTheLimit.WIDTH / 2 - TTLImg.gameTitle.getWidth() / 2),
				ToTheLimit.HEIGHT / 2 - TTLImg.gameTitle.getHeight() / 2,
				TTLImg.gameTitle.getWidth(), TTLImg.gameTitle.getHeight(), 0,
				0, TTLImg.gameTitle.getWidth(), TTLImg.gameTitle.getHeight(),
				false, true);
		
		// Draws the message for Player 1
		batch.draw(p1Message, 8, 122, p1Message.getWidth(),
				p1Message.getHeight(), 0, 0, p1Message.getWidth(),
				p1Message.getHeight(), false, true);
		
		// Draws the message for Player 2
		batch.draw(p2Message, 173, 122, p2Message.getWidth(),
				p2Message.getHeight(), 0, 0, p2Message.getWidth(),
				p2Message.getHeight(), false, true);
		
		for(Star star:stars){
			star.render(batch, 0);
		}
		
		batch.end();

	}
}
