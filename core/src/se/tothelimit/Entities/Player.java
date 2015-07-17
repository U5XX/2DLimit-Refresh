package se.tothelimit.Entities;

import se.tothelimit.ToTheLimit;
import se.tothelimit.Resources.TTLSnd;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * This class represents the players, and handles the animations and logic
 * associated with them
 * 
 * @author Fredrik Andersson
 * @author Simon Cedergren
 * 
 */
public class Player extends MovingEntity {
	private final static String tag = "2DLimit | Player";

	private static final int RUN_FRAME_COLS = 4;
	private static final int RUN_FRAME_ROWS = 1;

	private static final int WIN_FRAME_COLS = 2;
	private static final int WIN_FRAME_ROWS = 1;

	private Texture moving;
	private Texture idle;
	private Texture win;
	private TextureRegion[] movingAnimation;
	private TextureRegion[] winningAnimation;
	private TextureRegion[] staticAnimation;
	private TextureRegion currentFrame;
	private float stateTime = 0;

	private static float IDLE = 0.1f;
	private static float RUNNING = 2f;

	private boolean jumping = false;
	private boolean hasWon = false;
	protected float gravity = 200;

	private Animation currentAnimation;
	private Animation idleAnimation;
	private Animation runningAnimation;
	private Animation winAnimation;

	/**
	 * Basic constructor
	 * 
	 * @param x
	 *            Coordinate on the x-axis
	 * @param y
	 *            Coordinate on the y-axis
	 * @param height
	 *            The height of the player
	 * @param width
	 *            The width of the player
	 * @param moving
	 *            The sprite sheet containing the running animation.
	 * @param idle
	 *            The sprite sheet containing the idle animation.
	 * @param win
	 *            The sprite sheet containing the winning animation.
	 */
	public Player(float x, float y, float height, float width, Texture moving,
			Texture idle, Texture win) {
		super(x, y, height, width, moving);
		this.idle = idle;
		this.moving = moving;
		this.win = win;

		createAnimation();
	}

	@Override
	public void update() {
		super.update();

		// Applies "gravity" to the player when jumping.
		if (jumping) {
			float dt = Gdx.graphics.getDeltaTime();
			dy += gravity * dt;
			y += dy * dt + 20 * gravity * dt * dt;

			/*
			 * If a player is about to fall through the bridge, set the
			 * jumping-flag to false, and place the player on the bridge.
			 */
			if (this.y + getHeight() >= ToTheLimit.BRIDGE) {
				setJumping(false);
				dy = 0;
				this.y = ToTheLimit.BRIDGE - getHeight();
			}
		}

		// Makes the player decelerate. Provides a form of "friction".
		dx *= 0.9f;
		if (dx < 0.1)
			dx = 0;

		/*
		 * This block decides what animation should be shown based on the
		 * current value of dx.
		 */
		if (dx < IDLE) {

			if (hasWon)
				currentAnimation = winAnimation;
			else
				currentAnimation = idleAnimation;

		} else if (dx >= IDLE && dx < RUNNING) {
			currentAnimation = runningAnimation;
		} else if (dx >= RUNNING) {
			currentAnimation = runningAnimation;
		}

	}

	/**
	 * Increases the movement speed (forward) of the player.
	 */
	public void speedUp() {
		dx = dx + 2f;
	}
	
	/**
	 * Makes the player jump and plays the jumping sound.
	 */
	public void jump() {
		if (!this.jumping) {
			setJumping(true);
			y -= 1;
			dy = -150;
			TTLSnd.jump.play();
		}
	}
	
	/**
	 * Sets the jumping-state of the player.
	 * @param jumping - set to true if jumping.
	 */
	public void setJumping(boolean jumping) {
		this.jumping = jumping;
	}
	
	/**
	 * Checks for collision for the current box.
	 * @param box - the currently rendered box.
	 */
	public void checkForCollision(Obstacle box){
		// Collision from the right
		if(y + height > box.getY() && y < box.getY() + box.getHeight()){
			if(x + width - 2 >= box.getX() && box.getX() + box.getWidth() > x + width){
				x = x - 2;
				dx = 0;
				TTLSnd.boxHit.play();
			}
		}
		
		// Collision from above
		if(x + width - 2 >= box.getX() && x < box.getX() + box.getWidth()){
			if(y + height >= box.getY() && dy != 0){
				y = box.getY() - height;
				
				// Temp. solution to avoid implementing a real state system.
				// Fixes the glitch where players would SOMETIMES fall through obstacles.
				dy -= dy; 
			}
		}
	}

	/**
	 * Creates/initializes the various animations for the player.
	 */
	private void createAnimation() {
		// Creates the animation for when the player is running
		TextureRegion[][] tmp = TextureRegion.split(moving, moving.getWidth()
				/ RUN_FRAME_COLS, moving.getHeight() / RUN_FRAME_ROWS);
		movingAnimation = new TextureRegion[RUN_FRAME_COLS * RUN_FRAME_ROWS];

		int index = 0;
		for (int i = 0; i < RUN_FRAME_ROWS; i++)
			for (int j = 0; j < RUN_FRAME_COLS; j++)
				movingAnimation[index++] = tmp[i][j];

		// Creates the "animation" for when the player is idle
		TextureRegion[][] tmp2 = TextureRegion.split(idle, idle.getWidth(),
				idle.getHeight());
		staticAnimation = new TextureRegion[1];
		staticAnimation[0] = tmp2[0][0];

		// Creates the animation for when the player has won.
		TextureRegion[][] tmp3 = TextureRegion.split(win, win.getWidth()
				/ WIN_FRAME_COLS, win.getHeight() / WIN_FRAME_ROWS);
		winningAnimation = new TextureRegion[WIN_FRAME_COLS * WIN_FRAME_ROWS];

		index = 0;
		for (int i = 0; i < WIN_FRAME_ROWS; i++)
			for (int j = 0; j < WIN_FRAME_COLS; j++)
				winningAnimation[index++] = tmp3[i][j];

		// Stores the animations in the proper variables.
		idleAnimation = new Animation(0.1f, staticAnimation);
		runningAnimation = new Animation(0.15f, movingAnimation);
		winAnimation = new Animation(0.4f, winningAnimation);

		// Sets the starting animation
		currentAnimation = idleAnimation;
	}

	/**
	 * Sets a flag indicating whether or not a player has won.
	 * 
	 * @param winning
	 *            'true' if the player has won, otherwise 'false'.
	 */
	public void setHasWon(boolean hasWon) {
		this.hasWon = hasWon;
	}
	
	/**
	 * Renders the player.
	 */
	public void render(SpriteBatch batch) {
		stateTime += Gdx.graphics.getDeltaTime();
		currentFrame = currentAnimation.getKeyFrame(stateTime, true);
		batch.draw(currentFrame, x, y);
	}
}