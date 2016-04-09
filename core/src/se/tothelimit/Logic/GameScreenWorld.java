package se.tothelimit.Logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Logger;

import se.tothelimit.Input.GameScreenTouchInput;
import se.tothelimit.ToTheLimit;
import se.tothelimit.Input.Buffer;
import se.tothelimit.Entities.Bridge;
import se.tothelimit.Entities.Obstacle;
import se.tothelimit.Entities.ParallaxBackground;
import se.tothelimit.Entities.Player;
import se.tothelimit.Entities.Star;
import se.tothelimit.Render.GameScreenRenderer;
import se.tothelimit.Resources.TTLImg;
import se.tothelimit.Resources.TTLSnd;
import se.tothelimit.Screens.WinnerScreen;
import se.tothelimit.Tools.InputHandler;
import se.tothelimit.Tools.InputListener;
import se.tothelimit.Tools.KeyboardInput;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;

/**
 * This class contains the logic and game loop for the GameScreen.
 * 
 * @author Fredrik Andersson
 * 
 */
public class GameScreenWorld {
	private ToTheLimit game;

	private Player player1;
	private Player player2;
	private Player currentLeader;
	private Obstacle box;

	private ArrayList<Bridge> bridges;
	private ArrayList<Star> stars;
	private ArrayList<ParallaxBackground> houseFront;
	private ArrayList<ParallaxBackground> houseBack;

	private ArrayList<Integer> buffer;
	private OrthographicCamera cam;
	private long lastUpdate = System.currentTimeMillis();
	private int interval = 50;

	private Texture countdownMsg;
	private long countdownTimer = 0;

	/**
	 * Basic constructor
	 * 
	 * @param game
	 *            A reference to the Game-class.
	 */
	public GameScreenWorld(ToTheLimit game) {
		this.game = game;
		buffer = new ArrayList<Integer>();
		initLevelComponents();
		Gdx.input.setInputProcessor(new InputHandler(new GameScreenTouchInput()));
	}

	/**
	 * Initializes the graphical components for the game.
	 */
	private void initLevelComponents() {
		// Initializes Player 1
		this.player1 = new Player((ToTheLimit.WIDTH / 2)
				- (TTLImg.player1.getWidth() / 2), (ToTheLimit.HEIGHT / 2)
				- TTLImg.player1.getHeight() + 13, 24, 24,
				TTLImg.player1Running, TTLImg.player1Idle, TTLImg.player1Win);

		// Initializes Player 2
		this.player2 = new Player((ToTheLimit.WIDTH / 2)
				- (TTLImg.player2.getWidth() / 2), (ToTheLimit.HEIGHT / 2)
				- TTLImg.player1.getHeight() + 13, 24, 24,
				TTLImg.player2Running, TTLImg.player2Idle, TTLImg.player2Win);

		// Initializes the bridges
		this.bridges = new ArrayList<Bridge>(2);

		for (int i = 0; i < 2; i++) {
			float x = (i * ToTheLimit.WIDTH);
			float y = 0;
			float width = ToTheLimit.WIDTH;
			float height = TTLImg.bridge.getHeight();

			Bridge newBridge = new Bridge(x, y, width, height, TTLImg.bridge);
			bridges.add(newBridge);
		}

		// Initializes the stars
		this.stars = new ArrayList<Star>();

		Star star1 = new Star(36, 51, 5, 5, TTLImg.stars);
		Star star2 = new Star(90, 53, 5, 5, TTLImg.stars);
		Star star3 = new Star(130, 24, 5, 5, TTLImg.stars);
		Star star4 = new Star(190, 56, 5, 5, TTLImg.stars);
		stars.add(star1);
		stars.add(star2);
		stars.add(star3);
		stars.add(star4);

		// Initializes the houses in the front.
		this.houseFront = new ArrayList<ParallaxBackground>(2);

		for (int i = 0; i < 2; i++) {
			float x = (i * ToTheLimit.WIDTH);
			float y = 0;
			float width = ToTheLimit.WIDTH;
			float height = ToTheLimit.HEIGHT;

			ParallaxBackground frontSeg = new ParallaxBackground(x, y, width,
					height, TTLImg.houseFront);
			houseFront.add(frontSeg);
		}

		// // Initializes the houses in the back
		this.houseBack = new ArrayList<ParallaxBackground>(2);

		for (int i = 0; i < 2; i++) {
			float x = (i * ToTheLimit.WIDTH);
			float y = 0;
			float width = ToTheLimit.WIDTH;
			float height = ToTheLimit.HEIGHT;

			ParallaxBackground backSeg = new ParallaxBackground(x, y, width,
					height, TTLImg.houseBack);
			houseBack.add(backSeg);
		}

		// Initialize the box
		box = new Obstacle(0, ToTheLimit.BRIDGE - 12, 12, 12, TTLImg.box);
		box.move();

		countdownTimer = System.currentTimeMillis();
	}

	/**
	 * The game loop. This method gets called ~60 times/sec. and handles the
	 * logic for the game.
	 */
	public void update() {

		this.cam = GameScreenRenderer.cam;

		// Check if either player has lost by leaving the viewport on the left
		// side.
		if (player1.getX() + player1.getWidth() < (cam.position.x - (cam.viewportWidth / 2))) {
			 game.setScreen(new WinnerScreen(game, player2));
		} else if (player2.getX() + player2.getWidth() < (cam.position.x - (cam.viewportWidth / 2))) {
			 game.setScreen(new WinnerScreen(game, player1));
		}

		/*
		 * This block Handles the countdown at the beginning of the round. If
		 * less than 3 seconds have passed since the start of the match; perform
		 * countdown. This is admittedly an awful, awful solution.
		 */
		if (System.currentTimeMillis() - countdownTimer <= 3000) {
			long diff = System.currentTimeMillis() - countdownTimer;

			// If less than one sec has passed
			if (diff <= 1000) {
				countdownMsg = TTLImg.msg3;

				// If less than two sec have passed
			} else if (diff <= 2000) {
				countdownMsg = TTLImg.msg2;

				// If less than three sec have passed
			} else if (diff <= 3000) {
				countdownMsg = TTLImg.msg1;
			}

			// Clear the buffer, to avoid building up "head starts" during the
			// countdown.
			Buffer.clear();

			// If countdown is finished: Let the players start racing.
		} else {

			// Makes the "Go!"-message show up for one second after the
			// countdown.
			if (System.currentTimeMillis() - countdownTimer <= 4000) {
				countdownMsg = TTLImg.msgGo;

				// Starts the music
				 if(!TTLSnd.bgm.isPlaying())
				 TTLSnd.bgm.play();

			} else {
				countdownMsg = null;
			}

			// Reads the BT-buffer, and increments the speed of the players.
			this.buffer = Buffer.read();

			for (int nbr : buffer) {
				switch (nbr) {
				case ToTheLimit.PLAYER1_JUMP:
					player1.jump();
					break;
				case ToTheLimit.PLAYER1_RUN:
					player1.speedUp();
					break;
				case ToTheLimit.PLAYER2_JUMP:
					player2.jump();
					break;
				case ToTheLimit.PLAYER2_RUN:
					player2.speedUp();
					break;
				}
			}
		}

		player1.update();
		player2.update();
		
		player1.checkForCollision(box);
		player2.checkForCollision(box);
		
		

		// Checks which player is currently in the lead.
		if (player1.getX() >= player2.getX())
			currentLeader = player1;
		else
			currentLeader = player2;

		for (int i = 0; i < houseFront.size(); i++) {
			// Sets the speed of movement for the houses in front.
			houseFront.get(i).update(currentLeader.getDx() * 0.98f);

			// If the houses disappear on the left side, move them to the right.
			if (houseFront.get(i).getX() + houseFront.get(i).getWidth() <= cam.position.x
					- (cam.viewportWidth / 2)) {
				houseFront.get(i)
						.setX(cam.position.x + (cam.viewportWidth / 2));
				houseFront.get(i + 1).setX(
						cam.position.x - (cam.viewportWidth / 2));
				Collections.reverse(houseFront);
				break;
			}
		}

		for (int i = 0; i < houseBack.size(); i++) {
			// Sets the speed of movement for the houses in the back.
			houseBack.get(i).update(currentLeader.getDx() * 1f);

			// If the houses disappear on the left side, move them to the right.
			if (houseBack.get(i).getX() + houseBack.get(i).getWidth() <= cam.position.x
					- (cam.viewportWidth / 2)) {
				houseBack.get(i).setX(cam.position.x + (cam.viewportWidth / 2));
				houseBack.get(i + 1).setX(
						cam.position.x - (cam.viewportWidth / 2));
				Collections.reverse(houseBack);
				break;
			}
		}

		for (int i = 0; i < bridges.size(); i++) {
			// If the bridge disappears on the left side, move it to the right.
			if (bridges.get(i).getX() + bridges.get(i).getWidth() <= cam.position.x
					- (cam.viewportWidth / 2)) {
				bridges.get(i).setX(cam.position.x + (cam.viewportWidth / 2));
				bridges.get(i + 1).setX(
						cam.position.x - (cam.viewportWidth / 2));
				Collections.reverse(bridges);
				break;
			}
		}

		// Moves the box to the right if it has left the screen
		if (box.getX() + box.getWidth() < cam.position.x
				- (ToTheLimit.WIDTH / 2))
			box.move();
	}

	/**
	 * Returns a reference to Player 1
	 * 
	 * @return A reference to Player 1
	 */
	public Player getPlayer1() {
		return player1;
	}

	/**
	 * Returns a reference to Player 2
	 * 
	 * @return A reference to Player 2
	 */
	public Player getPlayer2() {
		return player2;
	}

	/**
	 * Returns a reference to the list containing the Bridge-entities
	 * 
	 * @return The list containing the Bridge-entities.
	 */
	public ArrayList<Bridge> getBridges() {
		return bridges;
	}

	/**
	 * Returns a reference to the list containing the Star-entities
	 * 
	 * @return The list containing the Star-entities.
	 */
	public ArrayList<Star> getStars() {
		return stars;
	}

	/**
	 * Returns a reference to the list containing the backgrounds with the
	 * houses in front.
	 * 
	 * @return The list containing the backgrounds with the houses in front.
	 */
	public ArrayList<ParallaxBackground> getHouseFront() {
		return houseFront;
	}

	/**
	 * Returns a reference to the list containing the backgrounds with the
	 * houses in the back.
	 * 
	 * @return The list containing the backgrounds with the houses in the back.
	 */
	public ArrayList<ParallaxBackground> getHouseBack() {
		return houseBack;
	}

	/**
	 * Returns the currently set count down message.
	 * 
	 * @return The currently set count down message.
	 */
	public Texture getCountdownMsg() {
		return countdownMsg;
	}
	
	/**
	 * Returns the obstacle (box)
	 * @return The obstacle (box)
	 */
	public Obstacle getObstacle(){
		return box;
	}
}
