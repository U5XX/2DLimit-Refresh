package se.tothelimit.Render;

import java.util.ArrayList;

import se.tothelimit.ToTheLimit;
import se.tothelimit.Entities.Bridge;
import se.tothelimit.Entities.Obstacle;
import se.tothelimit.Entities.ParallaxBackground;
import se.tothelimit.Entities.Player;
import se.tothelimit.Entities.Star;
import se.tothelimit.Logic.GameScreenWorld;
import se.tothelimit.Resources.TTLImg;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

/**
 * This class handles the rendering of the game screen.
 * 
 * @author Fredrik Andersson
 * 
 */
public class GameScreenRenderer {

	private GameScreenWorld world;
	private SpriteBatch batch;
	public static OrthographicCamera cam;

	private Player player1;
	private Player player2;
	private Obstacle box;
	private ArrayList<Bridge> bridges;
	private ArrayList<Star> stars;
	private ArrayList<ParallaxBackground> houseFront;
	private ArrayList<ParallaxBackground> houseBack;

	private Rectangle view;

	private Texture countdownMsg;

	/**
	 * Basic constructor
	 * 
	 * @param world
	 *            The associated GameScreenWorld-object.
	 */
	public GameScreenRenderer(GameScreenWorld world) {

		this.world = world;
		this.view = new Rectangle();

		cam = new OrthographicCamera();
		cam.setToOrtho(true, ToTheLimit.WIDTH, ToTheLimit.HEIGHT);
		cam.update();

		batch = new SpriteBatch();
		batch.setProjectionMatrix(cam.combined);

	}

	/**
	 * Updates the position and dimensions of the view port.
	 */
	private void updateViewport() {
		float x = (cam.position.x - (cam.viewportWidth / 2));
		float y = (cam.position.y - (cam.viewportHeight / 2));
		float width = cam.viewportWidth;
		float height = cam.viewportHeight;

		view.set(x, y, width, height);
	}

	/**
	 * Draws the graphical elements of the game on the screen.
	 */
	public void render() {

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		this.player1 = world.getPlayer1();
		this.player2 = world.getPlayer2();
		this.bridges = world.getBridges();
		this.stars = world.getStars();
		this.houseFront = world.getHouseFront();
		this.houseBack = world.getHouseBack();
		this.countdownMsg = world.getCountdownMsg();
		this.box = world.getObstacle();

		// Centers the camera on the player currently in the lead.
		if (player1.getX() >= player2.getX()) {
			cam.position.set(player1.getX() + (player1.getWidth() / 2),
					ToTheLimit.HEIGHT / 2, 0);
		} else {
			cam.position.set(player2.getX() + (player2.getWidth() / 2),
					ToTheLimit.HEIGHT / 2, 0);
		}

		updateViewport();

		// Setup camera
		cam.update();
		batch.setProjectionMatrix(cam.combined);

		// Drawing begins
		batch.begin();

		// Draws the background
		batch.draw(TTLImg.background, view.x, view.y, view.width, view.height,
				0, 0, TTLImg.background.getWidth(),
				TTLImg.background.getHeight(), false, true);

		// Draws the houses in the back.
		for (ParallaxBackground housesBack : houseBack) {
			housesBack.render(batch);
		}

		// Draws the houses in the front.
		for (ParallaxBackground housesFront : houseFront) {
			housesFront.render(batch);
		}

		// Draws the stars.
		for (Star star : stars) {
			star.render(batch, view.x);
		}

		// Draws the bridge.
		for (Bridge bridge : bridges) {
			bridge.render(batch);
		}

		// Draws the players
		player1.render(batch);
		player2.render(batch);

		// Draws the countdown message (if any).
		if (countdownMsg != null) {
			batch.draw(countdownMsg, cam.position.x
					- (countdownMsg.getWidth() / 2), 30,
					countdownMsg.getWidth(), countdownMsg.getHeight(), 0, 0,
					countdownMsg.getWidth(), countdownMsg.getHeight(), false,
					true);
		}
		
		box.render(batch);

		// Ends the drawing.
		batch.end();
	}

}
