package se.tothelimit.Entities;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * A class that represents a twinkling star.
 * https://github.com/libgdx/libgdx/wiki/2D-Animation
 * 
 * @author Simon Cedergren <simon@tuxflux.se>
 */
public class Star extends Entity {

	// nbr of cols and rows in the "atlas"
	private static final int FRAME_COLS = 6;
	private static final int FRAME_ROWS = 1;
	
	private float fixedX;

	private Texture textureSheet;
	private TextureRegion[] textureFrame;
	private Animation twinkleAnimation;
	private TextureRegion currentFrame;
	private float stateTime;

	/**
	 * Default constructor for Star.
	 * @param x - the x coordinate for where the star should be drawn.
	 * @param y - the y coordinate for where the star should be drawn.
	 * @param height - the height of the star.
	 * @param width - the width of the star.
	 * @param texture - the texture, eg. "atlas-file".
	 * @param offset - an offset that is getting added to the stateTime-variable.
	 */
	public Star(float x, float y, float width, float height, Texture texture) {
		super(x, y, height, width, texture);
		this.fixedX = x;
		this.textureSheet = texture;
		createAnimation();
	}

	/**
	 * Creates an animation from the atlas-file.
	 */
	public void createAnimation() {
		TextureRegion[][] tmp = TextureRegion.split(textureSheet,
				textureSheet.getWidth() / FRAME_COLS, textureSheet.getHeight()
						/ FRAME_ROWS);
		textureFrame = new TextureRegion[FRAME_COLS * FRAME_ROWS];
		int index = 0;
		for (int i = 0; i < FRAME_ROWS; i++)
			for (int j = 0; j < FRAME_COLS; j++)
				textureFrame[index++] = tmp[i][j];
		
		TextureRegion[] tmpRegion = new TextureRegion[textureFrame.length];
		int offset = new Random().nextInt(textureFrame.length);
		int k = 0;
		for (int i = offset; i < textureFrame.length; i++)
			tmpRegion[i] = textureFrame[k++];
		for (int i = 0; i < offset; i++)
			tmpRegion[i] = textureFrame[k++];
			
		twinkleAnimation = new Animation(0.4f, tmpRegion);
		stateTime = 0f;
	}
	
	/**
	 * Returns the position of the star.
	 * @return the position of the star.
	 */
	public float getFixedPos(){
		return fixedX;
	}

	/**
	 * Renders the animation with the given offset.
	 */
	public void render(SpriteBatch batch, float camOffset) {
		stateTime += ( Gdx.graphics.getDeltaTime());
		currentFrame = twinkleAnimation.getKeyFrame(stateTime, true);
		batch.draw(currentFrame, x + camOffset, y);
	}
}
