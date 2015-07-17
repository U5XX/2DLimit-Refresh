package se.tothelimit.Entities;

import com.badlogic.gdx.graphics.Texture;

/**
 * Entity class representing a parallax scrolling background.
 * 
 * @author Fredrik Andersson
 * 
 */
public class ParallaxBackground extends MovingEntity {

	/**
	 * Basic constructor.
	 * 
	 * @param x
	 *            X-coordinate of the position
	 * @param y
	 *            Y-coordinate of the position
	 * @param width
	 *            Width of the background
	 * @param height
	 *            Height of the background
	 * @param texture
	 *            The image to display
	 */
	public ParallaxBackground(float x, float y, float width, float height,
			Texture texture) {
		super(x, y, height, width, texture);
	}

	/**
	 * Method that updates the position of the background.
	 * 
	 * @param dx
	 *            dx-variable that the background should scroll relative to.
	 */
	public void update(float dx) {
		super.update();

		x = x + dx;
	}

}
